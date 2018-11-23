package protopopova.alla;


import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import protopopova.alla.view.StudentView;
import protopopova.alla.view.TeacherView;


@StyleSheet("css/shared-styles.css")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class MyUI extends FlexLayout implements RouterLayout {

    private Menu menu;

    public MyUI() {
        setSizeFull();
        setClassName("main-layout");

        menu = new Menu();
        menu.addView(TeacherView.class, TeacherView.VIEW_NAME);
        menu.addView(StudentView.class, StudentView.VIEW_NAME);
        add(menu);
    }

}
