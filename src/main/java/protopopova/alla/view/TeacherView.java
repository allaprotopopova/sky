package protopopova.alla.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import protopopova.alla.MyUI;

@Route(value = "teacher", layout = MyUI.class)
@RouteAlias(value = "", layout = MyUI.class)
public class TeacherView extends HorizontalLayout
        {

    public static final String VIEW_NAME = "Teacher";

    public TeacherView() {
        Button clickMe = new Button("ClickMe");
        this.add(clickMe);
    }


}
