package print_bd.view;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import print_bd.entity.User;
import print_bd.repository.UserRepository;
import print_bd.repository.UserRoleRepository;
import print_bd.service.UserService;

/**
 * Created by sereo_000 on 16.09.2016.
 */
@SpringView(name =LoginScreenView.VIEW_NAME)
@SpringComponent
@UIScope
public class LoginScreenView extends VerticalLayout implements View{
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private User user;
    private UserService userService;
    public final static String SESSION_USER_KEY = "SES_UKEY";
    public final static String VIEW_NAME = "";
    TextField email =new TextField("Email");
    PasswordField password =new PasswordField("Пароль");
    PasswordField newPassword =new PasswordField("Новый пароль (не менее 5 символов)");
    PasswordField confirmPassword =new PasswordField("Подтвердить пароль");
    Label askToChangePass = new Label("Необходимо сменить пароль");
    Label wrongPassOrLogin = new Label("Неверный пароль или логин");
    Label newPasswordIsTooSmall = new Label("Новый пароль слишком короткий");
    Button okButton = new Button("Логин");
    Button changeButton = new Button("Сохранить изменения");
    // Button cancel = new Button("Cancel");
    CssLayout actions = new CssLayout(okButton, changeButton);
    // CssLayout test = new CssLayout(email,password);
    @Autowired
    public LoginScreenView(UserRepository userRepository,UserRoleRepository userRoleRepository){
        this.userRepository = userRepository;
        this.roleRepository=userRoleRepository;
        userService = new UserService(userRepository,roleRepository);
        VerticalLayout loginScreenLayout = new VerticalLayout(wrongPassOrLogin,email,password,askToChangePass,newPasswordIsTooSmall,newPassword,confirmPassword, okButton, changeButton);
        addComponent(loginScreenLayout);
        setSpacing(true);
        loginScreenLayout.setSpacing(true);
        loginScreenLayout.setMargin(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        okButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        changeButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        okButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        okButton.addClickListener(e -> loginUser());
        //changeButton.addClickListener(e -> userService.changePassword(email.getValue(),password.getValue(),newPassword.getValue()));
        //cancel.addClickListener(e -> editCustomer(customer));
        setVisible(true);
        newPassword.setVisible(false);
        confirmPassword.setVisible(false);
        newPassword.setImmediate(true);
        newPassword.setMaxLength(15);
        changeButton.setVisible(false);
        askToChangePass.setVisible(false);
        wrongPassOrLogin.setVisible(false);
        newPasswordIsTooSmall.setVisible(false);
        setSizeFull();
    }
   @Autowired
    public void init(){
        VerticalLayout loginScreenLayout = new VerticalLayout(email,password,newPassword,confirmPassword, okButton, changeButton);
        loginScreenLayout.setSpacing(true);
        loginScreenLayout.setMargin(true);
        removeAllComponents();
        addComponent(loginScreenLayout);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        this.user = (User) getUI().getSession().getAttribute(SESSION_USER_KEY);
        if(user!=null){
            getUI().getNavigator().navigateTo(AdminWindowView.VIEW_NAME);
        }
    }

    public interface ChangeHandler {

        void onChange();
    }
    public final void loginUser(){
        Notification.show("Внимание!",
                "Если у вас включен AdBlock сервисы могут работать некорректно!",
                Notification.Type.TRAY_NOTIFICATION.WARNING_MESSAGE);
        wrongPassOrLogin.setVisible(false);
        if(!userService.haveUser(email.getValue())) {
            wrongPassOrLogin.setVisible(true);
            return;
        }
        {
            if(userService.checkPassword(email.getValue(),password.getValue())){
                if(userService.ifNeedToChangePassword(email.getValue())){
                    newPassword.setVisible(true);
                    confirmPassword.setVisible(true);
                    changeButton.setVisible(true);
                    askToChangePass.setVisible(true);
                    okButton.setVisible(false);
                    changeButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
                    newPasswordIsTooSmall.setVisible(false);
                    changeButton.addClickListener(e->{
                        //String checkPasswordLength;
                        newPassword.getValue().length();
                        // checkPasswordLength=newPassword.toString();
                        if(newPassword.getValue().length()<=4) {
                            Notification.show("Внимание!",
                                    "пароль должен быть не менее 5 символов!",
                                    Notification.Type.TRAY_NOTIFICATION.WARNING_MESSAGE);
                            // newPasswordIsTooSmall.setVisible(true);
                            // newPassword.setVisible(false);
                            // confirmPassword.setVisible(false);
                            //changeButton.setVisible(false);
                            askToChangePass.setVisible(false);
                            //okButton.setVisible(true);
                            // password.clear();
                            newPassword.clear();
                            confirmPassword.clear();
                            return;
                        }
                        if(password.getValue().equals(newPassword.getValue())){
                            Notification.show("Внимание!",
                                    "Новый пароль не должен совпадать со старым!",
                                    Notification.Type.TRAY_NOTIFICATION.WARNING_MESSAGE);
                            newPassword.clear();
                            confirmPassword.clear();
                            return;
                        }
                        userService.changePassword(email.getValue(),password.getValue(),newPassword.getValue(),confirmPassword.getValue());
                        newPassword.setVisible(false);
                        confirmPassword.setVisible(false);
                        changeButton.setVisible(false);
                        askToChangePass.setVisible(false);
                        okButton.setVisible(true);
                        newPasswordIsTooSmall.setVisible(false);
                        password.clear();
                    });

                    newPassword.clear();
                    confirmPassword.clear();
                }
                else {
                    //  getUI().getPage().setLocation("http://google.com");
                    user = userService.getValidUser(email.getValue(),password.getValue());
                    getUI().getSession().setAttribute(SESSION_USER_KEY, user);
                    //getUI().getNavigator().navigateTo(UserSettingsView.VIEW_NAME);
                    getUI().getNavigator().navigateTo(LoginScreenView.VIEW_NAME);
                }
            }
            else
                wrongPassOrLogin.setVisible(true);
        }
        //BeanFieldGroup.bindFieldsUnbuffered(userRepository,this);
        setVisible(true);

    }
    public void setChangeHandler(ChangeHandler h){
        // ChangeHandler is notified when either save or delete
        // is clicked
        okButton.addClickListener(e -> h.onChange());
        changeButton.addClickListener(e->h.onChange());
    }
}
