package protopopova.alla.view.widgets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import protopopova.alla.model.Collocation;
import protopopova.alla.model.WordGroup;
import protopopova.alla.service.CollocationService;

import java.util.Collections;


/**
 * A form for editing a single product.
 */
public class CollocationsListForm extends Div {

    private VerticalLayout content;
    private int  groupId;
    private final Grid<Collocation> grid;

    private CollocationService collocationService;


    @Autowired
    public CollocationsListForm(CollocationService collService) {
       this.collocationService = collService;
        content = new VerticalLayout();
        content.setSizeUndefined();
        add(content);

        HorizontalLayout editForm = new HorizontalLayout();
        TextField mainWord = new com.vaadin.flow.component.textfield.TextField();
        TextField pairWord = new TextField();

        editForm.add(mainWord);
        editForm.add(pairWord);

        Button saveBtn = new Button("Save");
        editForm.add(saveBtn);

        content.add(editForm);

        grid = new Grid<>();
        grid.addColumn(Collocation::getMainWord).setHeader("word");
        grid.addColumn(Collocation::getPairWord).setHeader("translate");

        grid.addColumn(new ComponentRenderer<Button, Collocation>(col -> {
            Button button = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
            button.addThemeVariants(ButtonVariant.LUMO_SMALL);
            button.addClickListener(click->{
                grid.getSelectionModel().deselectAll();
                collService.delete(col.getId());
                grid.setItems(collService.getAll(groupId));

            });

            return button;
        }));
        grid.addColumn(new ComponentRenderer<Button, Collocation>(col -> {
            Button button = new Button(new Icon(VaadinIcon.EDIT));
            button.addThemeVariants(ButtonVariant.LUMO_SMALL);
            button.addClickListener(click->{
                Dialog editGroupDialog = new Dialog();
                TextField mWord =  new TextField("New word");
                mWord.setValue(col.getMainWord());
                TextField pWord =  new TextField("New translate");
                pWord.setValue(col.getPairWord());
                editGroupDialog.add(mWord);
                editGroupDialog.add(pWord);
                Button updateGroup = new Button("Save", c-> {
                    grid.getSelectionModel().deselectAll();
                    collService.save(new Collocation(col.getId(), mWord.getValue(), pWord.getValue()), groupId);
                    grid.setItems(collService.getAll(groupId));
                    editGroupDialog.close();
                });
                editGroupDialog.add(updateGroup);
                editGroupDialog.open();

            });

            return button;
        }));
        content.add(grid);
        saveBtn.addClickListener(click -> {
            if (mainWord.getValue()!="" && pairWord.getValue()!="") {
                collService.save(new Collocation(mainWord.getValue(), pairWord.getValue()), groupId);
                grid.setItems(collService.getAll(groupId));
                grid.getDataProvider().refreshAll();
                mainWord.setValue("");
                pairWord.setValue("");
            }
        });










    }

    public void setCurrentWordGroup(int groupId) {
        this.groupId = groupId;
        grid.setItems(collocationService.getAll(groupId));

    }

    public void clear() {
        this.groupId = 0;
        grid.setItems(Collections.EMPTY_LIST);
    }
}
