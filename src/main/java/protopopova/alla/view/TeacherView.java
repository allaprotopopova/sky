package protopopova.alla.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import protopopova.alla.MyUI;
import protopopova.alla.model.Collocation;
import protopopova.alla.repository.CollocationRepository;
import protopopova.alla.repository.CollocationRepositoryImpl;

import java.util.List;


@SpringComponent
@Route(value = "teacher", layout = MyUI.class)
@RouteAlias(value = "", layout = MyUI.class)
public class TeacherView extends VerticalLayout {

    CollocationRepository repository;

    public static final String VIEW_NAME = "Teacher";

    @Autowired
    public TeacherView(CollocationRepository rep) {
        this.repository=rep;
        List<Collocation> all = repository.getAll();
        Grid<Collocation> grid = new Grid<>();
        grid.setItems(repository.getAll());
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
                repository.save(new Collocation(mainWord.getValue(), pairWord.getValue()));
                grid.setItems(repository.getAll());
                grid.getDataProvider().refreshAll();
            }
        });
        editForm.add(saveBtn);

        add(editForm);
        add(grid);

    }
}
