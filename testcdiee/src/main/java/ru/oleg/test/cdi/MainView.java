package ru.oleg.test.cdi;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.shared.Registration;

import javax.inject.Inject;

/**
 * The main view contains a simple label element and a template element.
 */
@Push
@Route("")
@PWA(name = "Project Base for Vaadin Flow with CDI", shortName = "Project Base")
public class MainView extends VerticalLayout {

    @Inject
    private MessageBean messageBean;
    Registration broadcasterRegistration;

    public MainView() {
        Button button = new Button("Click me",
                event -> Notification.show(messageBean.getMessage()));
        add(button);
        TextField message = new TextField();
        Button send = new Button("Send", e -> {
            Broadcaster.broadcast(message.getValue());
            message.setValue("");
        });
        
        add(message);
        add(send);
        

        // Creating the UI shown separately        

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI ui = attachEvent.getUI();
        broadcasterRegistration = Broadcaster.register(newMessage -> {
            ui.access(() -> this.add(new Span(newMessage)));
            ui.access(() -> Notification.show(newMessage));
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        broadcasterRegistration.remove();
        broadcasterRegistration = null;
    }        
}
