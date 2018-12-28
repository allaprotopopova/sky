package protopopova.alla.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import protopopova.alla.MyUI;
import protopopova.alla.model.Collocation;
import protopopova.alla.service.CollocationService;

import java.util.*;
import java.util.List;

@Route(value = "student", layout = MyUI.class)
public class StudentView extends HorizontalLayout {

    public static final String VIEW_NAME = "Student";

    private int groupId = 1;

    private CollocationService service;
    private Button leftChecked;
    private Button rightChecked;
    private Set<Collocation> acceptedSet = new LinkedHashSet<>();
//    private Map<Collocation, Button> leftMap = new LinkedHashMap<>();/

    @Autowired
    public StudentView(CollocationService serv) {
        this.service =serv;

        List<Collocation> allCollocations = service.getAll(groupId);

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
                        doNullForFields(addClickLogic(lList, leftList, rList, rightList, left, rightChecked));
                    }
            );
            return left;
        }));
        leftList.setDataProvider(new ListDataProvider<Collocation>(lList));


        rightList.setRenderer(new ComponentRenderer<>(coll -> {
            Button right = new Button(coll.getPairWord());
            right.addClickListener(click -> {
                if ( rightChecked!= null) {
                    rightChecked.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
                }
                rightChecked = right;
                doNullForFields(addClickLogic(rList, rightList, lList, leftList, right, leftChecked));

            });
            return right;
        }));
        rightList.setItems(rList);


        add(leftList);
        add(rightList);


    }

    private void doNullForFields(boolean isDoNull) {
        if (isDoNull) {
            leftChecked=null;
            rightChecked=null;
        }
    }

    private boolean addClickLogic(List<Collocation> clickedList, ListBox<Collocation> clickedListBox, List<Collocation> pairList, ListBox<Collocation> pairListBox, Button clickedBtn, Button pairChecked) {
        Collocation clickedCollValue = clickedListBox.getValue();
        Collocation pairCollValue = pairListBox.getValue();
        if (pairCollValue == null) {
            clickedBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        } else {
            pairChecked.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
            if (clickedCollValue.getId() == pairCollValue.getId()) {
                clickedList.remove(clickedCollValue);
                pairList.remove(clickedCollValue);
                clickedListBox.getDataProvider().refreshAll();
                pairListBox.getDataProvider().refreshAll();
                acceptedSet.add(clickedCollValue);
                acceptedSet.forEach(col -> {
                    Button btnL = (Button) clickedListBox.getItemRenderer().createComponent(col);
                    btnL.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                    Button btnR = (Button) pairListBox.getItemRenderer().createComponent(col);
                    btnR.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

                    clickedListBox.add(btnL);
                    pairListBox.add(btnR);

                });
                return true;
            } else {
                clickedBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
                pairChecked.addThemeVariants(ButtonVariant.LUMO_ERROR);

            }
        }
        return false;
    }

}
