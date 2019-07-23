package ru.oleg.test.ui.components.detailsdrawer;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tabs;
import ru.oleg.test.ui.components.FlexBoxLayout;
import ru.oleg.test.ui.layout.size.Horizontal;
import ru.oleg.test.ui.layout.size.Right;
import ru.oleg.test.ui.layout.size.Vertical;
import ru.oleg.test.ui.util.BoxShadowBorders;
import ru.oleg.test.ui.util.UIUtils;
import ru.oleg.test.ui.util.css.FlexDirection;

public class DetailsDrawerHeader extends FlexBoxLayout {

    private Button close;
    private Label title;

    public DetailsDrawerHeader(String title) {
        addClassName(BoxShadowBorders.BOTTOM);
        setFlexDirection(FlexDirection.COLUMN);
        setWidth("100%");

        this.close = UIUtils.createTertiaryInlineButton(VaadinIcon.CLOSE);
        this.close.removeThemeVariants(ButtonVariant.LUMO_ICON);
        this.close.getElement().getStyle().set("line-height", "1");

        this.title = UIUtils.createH3Label(title);

        FlexBoxLayout wrapper = new FlexBoxLayout(this.close, this.title);
        wrapper.setAlignItems(Alignment.CENTER);
        wrapper.setPadding(Horizontal.RESPONSIVE_L, Vertical.M);
        wrapper.setSpacing(Right.L);
        add(wrapper);
    }

    public DetailsDrawerHeader(String title, Tabs tabs) {
        this(title);
        add(tabs);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void addCloseListener(ComponentEventListener<ClickEvent<Button>> listener) {
        this.close.addClickListener(listener);
    }

}
