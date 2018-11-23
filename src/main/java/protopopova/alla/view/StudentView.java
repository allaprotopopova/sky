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
@RouteAlias(value = "")
public class StudentView extends HorizontalLayout {

    public static final String VIEW_NAME = "Student";
    private CollocationRepository repository = new CollocationRepositoryImpl();

    public StudentView() {

        Map<Collocation, Button> mainWordList = new HashMap<>();
        Map<Collocation, Button> pairWordList = new HashMap<>();

        List<Collocation> allCollocations = repository.getAll();

        Collections.shuffle(allCollocations);
        ListBox<Collocation> leftList = new ListBox<>();
        leftList.setRenderer(new ComponentRenderer<>(coll -> {
            Button left = new Button(coll.getMainWord());
            mainWordList.put(coll, left);
            return left;
        }));
        leftList.setItems(allCollocations);

        Collections.shuffle(allCollocations);
        ListBox<Collocation> rightList = new ListBox<>();
        rightList.setRenderer(new ComponentRenderer<>(coll -> {
            Button right = new Button(coll.getPairWord());
            pairWordList.put(coll, right);
            return right;
        }));
        rightList.setItems(allCollocations);

        leftList.addValueChangeListener(event -> {
            if (event.getOldValue()!=null) {
                Button oldButton = mainWordList.get(event.getOldValue());
                oldButton.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
            }
            Collocation pairWord = rightList.getValue();
            if (event.getValue()!=null) {
                Button valueButton = mainWordList.get(event.getValue());
                if (pairWord==null) {
                    valueButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                } else {
                    Button pairButton = pairWordList.get(pairWord);
                    if (event.getValue().getId()==pairWord.getId()) {
                        valueButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                        pairButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

                    } else {
                        valueButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
                        pairButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
                    }
                }
            }

        });

        rightList.addValueChangeListener(event -> {
            if (event.getOldValue()!=null) {
                Button oldButton = pairWordList.get(event.getOldValue());
                oldButton.removeThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_ERROR);
            }
        });

        add(leftList);
        add(rightList);



        }

}
