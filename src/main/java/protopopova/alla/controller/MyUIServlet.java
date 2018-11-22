package protopopova.alla.controller;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import protopopova.alla.view.MyUI;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
public class MyUIServlet extends VaadinServlet {
}
