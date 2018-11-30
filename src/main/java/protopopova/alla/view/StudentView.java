package protopopova.alla.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RoutePrefix;
import protopopova.alla.MyUI;
import protopopova.alla.model.Collocation;
import protopopova.alla.repository.CollocationRepository;
import protopopova.alla.repository.CollocationRepositoryImpl;

import java.util.*;

@Route(value = "student", layout = MyUI.class)
public class StudentView extends HorizontalLayout {

    public static final String VIEW_NAME = "Student";
    private CollocationRepository repository = new CollocationRepositoryImpl();
    private Button leftChecked;
    private Button rightChecked;
//    private Map<Collocation, Button> leftMap = new LinkedHashMap<>();/

    public StudentView() {



        List<Collocation> allCollocations = repository.getAll();

        Collections.shuffle(allCollocations);
        List<Collocation> lList = new ArrayList<>(allCollocations);
        ListBox<Collocation> leftList = new ListBox<>();
        Collections.shuffle(allCollocations);
        List<Collocation> rList = new ArrayList<>(allCollocations);
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
                            lList.remove(leftCollValue);
                            leftList.getDataProvider().refreshAll();
                            Button btn = (Button) leftList.getItemRenderer().createComponent(leftCollValue);
                            btn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                            leftList.addComponents(lList.get(lList.size()-1), btn);
                            rightChecked.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                            leftChecked = null;
                            rightChecked = null;
                        } else {
                            left.addThemeVariants(ButtonVariant.LUMO_ERROR);
                            rightChecked.addThemeVariants(ButtonVariant.LUMO_ERROR);



                        }
                    }
                }
            );
            return left;
        }));
        leftList.setDataProvider(new ListDataProvider<Collocation>(lList));


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
        rightList.setItems(rList);


        add(leftList);
        add(rightList);


    }

}
