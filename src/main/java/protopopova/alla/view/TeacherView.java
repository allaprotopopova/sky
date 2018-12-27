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
import protopopova.alla.repository.CollocationRepository;
import protopopova.alla.service.CollocationService;

import java.util.List;


@Route(value = "teacher", layout = MyUI.class)
@RouteAlias(value = "", layout = MyUI.class)
public class TeacherView extends VerticalLayout {

    CollocationService service;

    public static final String VIEW_NAME = "Teacher";

    @Autowired
    public TeacherView(CollocationService serv) {
        this.service =serv;
        List<Collocation> all = service.getAll();
        Grid<Collocation> grid = new Grid<>();
        grid.setItems(all);
        grid.addColumn(Collocation::getMainWord).setHeader("Word");
        grid.addColumn(Collocation::getPairWord).setHeader("Translate");


        HorizontalLayout editForm = new HorizontalLayout();
        TextField mainWord = new TextField();
        TextField pairWord = new TextField();

        editForm.add(mainWord);
        editForm.add(pairWord);

        Button saveBtn = new Button("Save");
        saveBtn.addClickListener(click -> {
            if (mainWord.getValue()!="" && pairWord.getValue()!="") {
                service.save(new Collocation(mainWord.getValue(), pairWord.getValue()));
                grid.setItems(service.getAll());
                grid.getDataProvider().refreshAll();
            }
        });
        editForm.add(saveBtn);

        add(editForm);
        add(grid);

    }
}
