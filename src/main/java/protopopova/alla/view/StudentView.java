package protopopova.alla.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RoutePrefix;
import protopopova.alla.MyUI;
import protopopova.alla.model.Collocation;
import protopopova.alla.repository.CollocationRepository;
import protopopova.alla.repository.CollocationRepositoryImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "student", layout = MyUI.class)
public class StudentView extends HorizontalLayout {

    public static final String VIEW_NAME = "Student";
    private CollocationRepository repository = new CollocationRepositoryImpl();
    private Button leftChecked;
    private Button rightChecked;

    public StudentView() {

//        Map<Collocation, Button> mainWordList = new HashMap<>();
//        Map<Collocation, Button> pairWordList = new HashMap<>();

        List<Collocation> allCollocations = repository.getAll();

        Collections.shuffle(allCollocations);
        ListBox<Collocation> leftList = new ListBox<>();
        Collections.shuffle(allCollocations);
        ListBox<Collocation> rightList = new ListBox<>();

        leftList.setRenderer(new ComponentRenderer<>(coll -> {
            Button left = new Button(coll.getMainWord());
            left.addClickListener(click -> {
                    if (leftChecked != null) {
                        leftChecked.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
                    }
                    leftChecked = left;
                    Collocation leftCollValue = leftList.getValue();
                    Collocation rightCollValue = rightList.getValue();
                    if (rightCollValue == null) {
                        left.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                    } else {
                        rightChecked.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
                        if (leftCollValue.getId() == rightCollValue.getId()) {
                            left.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                            rightChecked.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

                        } else {
                            left.addThemeVariants(ButtonVariant.LUMO_ERROR);
                            rightChecked.addThemeVariants(ButtonVariant.LUMO_ERROR);
                            left.setEnabled(false);
                            rightChecked.setEnabled(false);
                            leftChecked = null;
                            rightChecked = null;


                        }
                    }
                }
            );
            return left;
        }));
        leftList.setItems(allCollocations);


        rightList.setRenderer(new ComponentRenderer<>(coll -> {
            Button right = new Button(coll.getPairWord());
            right.addClickListener(click -> {
                if (rightChecked != null) {
                    rightChecked.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
                }
                rightChecked = right;

            });
            return right;
        }));
        rightList.setItems(allCollocations);


        add(leftList);
        add(rightList);


    }

}
