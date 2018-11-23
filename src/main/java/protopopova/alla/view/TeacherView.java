package protopopova.alla.view;

import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RoutePrefix;
import protopopova.alla.MyUI;

@Route(value = "teacher", layout = MyUI.class)
public class TeacherView extends FlexLayout {

    public static final String VIEW_NAME = "Teacher";
}
