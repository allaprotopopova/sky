package protopopova.alla.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RoutePrefix;
import protopopova.alla.MyUI;

@Route(value = "teacher", layout = MyUI.class)
@RouteAlias(value = "")
public class TeacherView extends HorizontalLayout {

    public static final String VIEW_NAME = "Teacher";

    public TeacherView() {
        ListBox<String> listBox = new ListBox<>();
        listBox.setItems("Bread", "Butter", "Milk");

// Adding components to the end:
        listBox.add(new H3("After all the items"));

// Adding components after a specific item:
        listBox.addComponents("Butter", new H3("After butter"));

// Adding components before a specific item:
        listBox.prependComponents("Bread", new H3("Before bread"));
        add(listBox);
    }
}
