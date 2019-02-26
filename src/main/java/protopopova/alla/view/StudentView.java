package protopopova.alla.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import protopopova.alla.MyUI;
import protopopova.alla.model.Collocation;
import protopopova.alla.model.WordGroup;
import protopopova.alla.service.CollocationService;
import protopopova.alla.service.WordGroupService;
import protopopova.alla.util.FailPhrase;
import protopopova.alla.util.SuccessPhrase;

import java.util.*;

@Route(value = "student", layout = MyUI.class)
@RouteAlias(value = "", layout = MyUI.class)
public class StudentView extends SplitLayout {

    public static final String VIEW_NAME = "Student";
    private static final String GREETING = "Hi there! Let's start! Just pick out set!";
    private static final long serialVersionUID = 55652396674791393L;

    private CollocationService service;
    private WordGroupService groupService;
    private Button leftChecked;
    private Button rightChecked;
    private Set<Collocation> acceptedSet;
    private List<Collocation> allCollocations;
    private List<Collocation> lList;
    private List<Collocation> rList;
    private final ListBox<Collocation> leftList;
    private final ListBox<Collocation> rightList;
    private final Label cliffSays;


    @Autowired
    public StudentView(CollocationService serv, WordGroupService groupServ) {
        this.service =serv;
        this.groupService=groupServ;
        setSizeFull();

        leftList = new ListBox<>();
        rightList = new ListBox<>();

        leftList.setRenderer(new ComponentRenderer<>(coll -> {
            Button left = new Button(coll.getMainWord());
            left.addClickListener(click -> {
                        if (leftChecked != null) {
                            leftChecked.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
                        }
                        leftChecked = left;
                        boolean isSuccess = addClickLogic(lList, leftList, rList, rightList, left, rightChecked);
                        cliffSays(isSuccess);
                        doNullForFields(isSuccess);
                    }
            );
            return left;
        }));

        rightList.setRenderer(new ComponentRenderer<>(coll -> {
            Button right = new Button(coll.getPairWord());
            right.addClickListener(click -> {
                if ( rightChecked!= null) {
                    rightChecked.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
                }
                rightChecked = right;
                boolean isSuccess = addClickLogic(rList, rightList, lList, leftList, right, leftChecked);
                cliffSays(isSuccess);
                doNullForFields(isSuccess);

            });
            return right;
        }));

        cliffSays = new Label();
        cliffSays.setText(GREETING);
        cliffSays.setWidth("100%%");
        cliffSays.setEnabled(false);

        Grid<WordGroup> groupsGrid = new Grid<>();
        groupsGrid.setSizeFull();
        groupsGrid.addColumn(WordGroup::getName).setHeader("Name of set");
        groupsGrid.asSingleSelect().addValueChangeListener(event-> {
            if (event.getValue()!=null) {
                setGroupId(event.getValue().getId());
            }
           else {
                leftList.setItems(new ArrayList<>());
                rightList.setItems(new ArrayList<>());
            }
           cliffSays.removeClassName("Success");
           cliffSays.removeClassName("Fail");
           cliffSays.setText("Let's start!");
        });

        groupsGrid.setItems(groupService.getAll());
        VerticalLayout div = new VerticalLayout();
        div.setWidth("70%");

// Show the image in the application

        Image cliffphoto = new Image("frontend/img/cliff-photo.jpg", "cliff-photo.jpg");
        HorizontalLayout cliffTalki = new HorizontalLayout();
        cliffTalki.add(cliffphoto);
        cliffTalki.add(cliffSays);
        div.add(cliffTalki);
        HorizontalLayout hor = new HorizontalLayout();
        hor.setSizeFull();
        div.add(hor);
        hor.add(leftList);
        hor.add(rightList);
        groupsGrid.setWidth("30%");
        addToPrimary(div);
        addToSecondary(groupsGrid);
    }

    private void cliffSays(boolean isSuccess) {

        if (leftChecked!=null && rightChecked!=null) {
            int lower = 0;
            int upper;
            if (isSuccess) {
                upper = SuccessPhrase.values().length;
            } else {
                upper = FailPhrase.values().length;
            }

            int randomIndex = (int) (Math.random() * (upper - lower)) + lower;

            if (isSuccess) {
                cliffSays.setText(SuccessPhrase.values()[randomIndex].getText());
                cliffSays.removeClassName("Fail");
                cliffSays.setClassName("Success");

            } else {
                cliffSays.setText(FailPhrase.values()[randomIndex].getText());
                cliffSays.removeClassName("Success");
                cliffSays.setClassName("Fail");
            }
        }


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
            if (clickedCollValue.getId().equals(pairCollValue.getId())) {
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

    public void setGroupId(int groupId) {
        doNullForFields(true);
        acceptedSet = new LinkedHashSet<>();
        allCollocations = service.getAll(groupId);
        Collections.shuffle(allCollocations);
        lList = new ArrayList<>(allCollocations);
        Collections.shuffle(allCollocations);
        rList = new ArrayList<>(allCollocations);
        leftList.setItems(lList);
        rightList.setItems(rList);
    }
}
