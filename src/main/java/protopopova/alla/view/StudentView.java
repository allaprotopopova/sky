package protopopova.alla.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import protopopova.alla.MyUI;

@Route(value = "student", layout = MyUI.class)
public class StudentView extends HorizontalLayout
        {

    public static final String VIEW_NAME = "Student";

    public StudentView() {
        Button clickMe = new Button("Kick me");
        this.add(clickMe);
    }


}
