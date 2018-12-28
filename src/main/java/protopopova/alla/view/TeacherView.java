package protopopova.alla.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout editForm = new HorizontalLayout();
        TextField nameGroup = new TextField();

        editForm.add(nameGroup);

        Button saveBtn = new Button("Save");
        saveBtn.addClickListener(click -> {
            if (!nameGroup.getValue().isEmpty()) {
                service.create(new WordGroup(nameGroup.getValue()));
                grid.setItems(service.getAll());
                grid.getDataProvider().refreshAll();
            }
        });
        editForm.add(saveBtn);
        verticalLayout.add(editForm);
        verticalLayout.add(grid);

        CollocationsListForm form = new CollocationsListForm(collocationService);

        add(verticalLayout);
        add(form);
        grid.asSingleSelect().addValueChangeListener(event -> {
            form.setCurrentWordGroup(event.getValue().getId());
        });

    }
}
