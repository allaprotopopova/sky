package protopopova.alla.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ClickableRenderer;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import protopopova.alla.MyUI;
import protopopova.alla.model.Collocation;
import protopopova.alla.model.WordGroup;
import protopopova.alla.repository.CollocationRepository;
import protopopova.alla.service.CollocationService;
import protopopova.alla.service.WordGroupService;
import protopopova.alla.view.widgets.CollocationsListForm;

import java.util.List;


@Route(value = "teacher", layout = MyUI.class)
@RouteAlias(value = "", layout = MyUI.class)
public class TeacherView extends HorizontalLayout {

    WordGroupService service;
    CollocationService collocationService;

    public static final String VIEW_NAME = "Teacher";

    @Autowired
    public TeacherView(WordGroupService serv, CollocationService collocationServ) {
        this.service =serv;
        this.collocationService=collocationServ;
        List<WordGroup> all = service.getAll();
        Grid<WordGroup> grid = new Grid<>();
        grid.setItems(all);
        grid.addColumn(WordGroup::getName).setHeader("Name of the List");

        grid.addColumn(new ComponentRenderer<Button, WordGroup>(group -> {
            Button button = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
            button.addThemeVariants(ButtonVariant.LUMO_SMALL);
            button.addClickListener(click->{
                grid.getSelectionModel().deselectAll();
                service.delete(group.getId());
                grid.setItems(service.getAll());

            });

            return button;
        }));
        grid.addColumn(new ComponentRenderer<Button, WordGroup>(group -> {
            Button button = new Button(new Icon(VaadinIcon.EDIT));
            button.addThemeVariants(ButtonVariant.LUMO_SMALL);
            button.addClickListener(click->{
                Dialog editGroupDialog = new Dialog();
                TextField name =  new TextField("New name");
                name.setValue(group.getName());
                editGroupDialog.add(name);
                Button updateGroup = new Button("Save", c-> {
                    grid.getSelectionModel().deselectAll();
                    service.update(new WordGroup(group.getId(), name.getValue()));
                    grid.setItems(service.getAll());
                    editGroupDialog.close();
                });
                editGroupDialog.add(updateGroup);
                editGroupDialog.open();

            });

            return button;
        }));

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout editForm = new HorizontalLayout();
        TextField nameGroup = new TextField();

        editForm.add(nameGroup);

        Button saveBtn = new Button("Save");
        saveBtn.addClickListener(click -> {
            if (!nameGroup.getValue().isEmpty()) {
                service.create(new WordGroup(nameGroup.getValue()));
                grid.setItems(service.getAll());
            }
        });
        editForm.add(saveBtn);
        verticalLayout.add(editForm);
        verticalLayout.add(grid);

        CollocationsListForm form = new CollocationsListForm(collocationService);

        add(verticalLayout);
        add(form);
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue()!=null) {
                form.setCurrentWordGroup(event.getValue().getId());
            } else {
                form.clear();
            }
        });

    }
}
