package protopopova.alla.view.widgets;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import protopopova.alla.model.Collocation;
import protopopova.alla.model.WordGroup;
import protopopova.alla.service.CollocationService;


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
        content.add(grid);
        saveBtn.addClickListener(click -> {
            if (mainWord.getValue()!="" && pairWord.getValue()!="") {
                collService.save(new Collocation(mainWord.getValue(), pairWord.getValue()), groupId);
                grid.setItems(collService.getAll(groupId));
                grid.getDataProvider().refreshAll();
            }
        });










    }

    public void setCurrentWordGroup(int groupId) {
        this.groupId = groupId;
        grid.setItems(collocationService.getAll(groupId));

    }
}
