package print_bd.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import print_bd.view.LoginScreenView;

/**
 * Created by sereo_000 on 23.09.2016.
 */
@SpringUI
@Theme("valo")
public class VaadinUI extends UI{
    @Autowired
    private SpringViewProvider viewProvider;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(viewProvider);
        navigator.navigateTo(LoginScreenView.VIEW_NAME);
        //navigator.navigateTo(SpecialView.VIEW_NAME);
    }
}
