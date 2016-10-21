package print_bd.view;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import print_bd.entity.User;
import print_bd.entity.UserRole;
import print_bd.repository.UserRepository;
import print_bd.repository.UserRoleRepository;
import print_bd.service.UserService;

import static print_bd.view.LoginScreenView.SESSION_USER_KEY;

/**
 * Created by sereo_000 on 17.10.2016.
 */
@SpringView(name = RoleAddView.VIEW_NAME)//ВЕЗДЕ!!!!!!!
@SpringComponent
@UIScope
public class RoleAddView extends VerticalLayout implements View {
    public static final String VIEW_NAME ="ROLE_ADD" ;
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private UserService userService;
    private User user;
    private UserRole userRole;
    private final Grid grid = new Grid();
    private final Button addNewBtn;
    private final CheckBox viewCheck = new CheckBox("Просмотр истории печатей");
    private final CheckBox addCheck = new CheckBox("Добавление документов");
    private final CheckBox printCheck = new CheckBox("Печать");
    private final CheckBox adminCheck = new CheckBox("Администратор");
    private final CheckBox acceptRequestCheck = new CheckBox("Разрешение на перепечать");
    private final TextField roleName = new TextField("Название роли");
    Button save = new Button("Сохранить", FontAwesome.SAVE);
    Button delete = new Button("Удалить",FontAwesome.TRASH_O);
    CssLayout buttons = new CssLayout(save,delete);
    @Autowired
    public RoleAddView(UserRepository userRepository, UserRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addNewBtn = new Button("Добавить пользователя", FontAwesome.PLUS);
        userService=new UserService(userRepository,roleRepository);
        setVisible(true);

    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        this.user = (User) getUI().getSession().getAttribute(SESSION_USER_KEY);
        init();
    }
    public void init() {
        HorizontalLayout actions = new HorizontalLayout(addNewBtn);
        HorizontalLayout userRoleChange = new HorizontalLayout(buttons);
        VerticalLayout changeUser = new VerticalLayout(viewCheck, addCheck, printCheck, adminCheck,acceptRequestCheck,roleName, userRoleChange);
        HorizontalLayout forGrid = new HorizontalLayout(grid);
        addComponents(actions, forGrid, changeUser, userRoleChange);
        setSpacing(true);
        actions.setSpacing(true);
        actions.setMargin(true);
        changeUser.setMargin(true);
        userRoleChange.setMargin(true);
        changeUser.setSpacing(true);
        userRoleChange.setSpacing(true);
        changeUser.setVisible(false);
        userRoleChange.setVisible(false);
        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "roleName");
        grid.getColumn("id").setHeaderCaption("ID");
        grid.getColumn("roleName").setHeaderCaption("role");
        forGrid.setSpacing(true);
        forGrid.setMargin(true);
        buttons.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        save.addClickListener(e -> {
            final Window window = new Window("");
            window.setWidth(300.0f, Unit.PIXELS);
            window.setPosition(400, 150);
            Button ok = new Button("Да");
            Button no = new Button("Нет");
            HorizontalLayout buttons = new HorizontalLayout(ok, no);
            buttons.setSpacing(true);
            Label areSure = new Label("Сохранить?");
            final FormLayout content = new FormLayout(areSure, buttons);
            window.setContent(content);
            UI.getCurrent().addWindow(window);
            ok.addClickListener(u -> {
                userRole.setAddSerialNumber(addCheck.getValue());
                userRole.setAdmin(adminCheck.getValue());
                userRole.setViewPrintHistory(viewCheck.getValue());
                userRole.setPrintDocs(printCheck.getValue());
                userRole.setAcceptReprintRequest(acceptRequestCheck.getValue());
                userRole.setRoleName(roleName.getValue());
                userService.setRole(userRole);
                changeUser.setVisible(false);
                userRoleChange.setVisible(false);
                listRoles(null);
                window.close();
            });
            no.addClickListener(u -> {
                window.close();
            });

        });
        delete.addClickListener(e -> {
            final Window window = new Window("");
            window.setWidth(450.0f, Unit.PIXELS);
            window.setPosition(400, 150);
            Button ok = new Button("Да");
            Button no = new Button("Нет");
            HorizontalLayout buttons = new HorizontalLayout(ok, no);
            buttons.setSpacing(true);
            Label areSure = new Label("Вы уверены, что хотите удалить role?");
            final FormLayout content = new FormLayout(areSure, buttons);

            window.setContent(content);
            UI.getCurrent().addWindow(window);
            ok.addClickListener(u -> {
                userRole = (UserRole)grid.getSelectedRow();
                userService.deleteRole(userRole);
                listRoles(null);
                window.close();
            });
            no.addClickListener(u -> {
                window.close();
            });

        });
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                changeUser.setVisible(false);
                userRoleChange.setVisible(false);

            } else {
                changeUser.setVisible(true);
                userRole=(UserRole) grid.getSelectedRow();
                adminCheck.setValue(userRole.isAdmin());
                printCheck.setValue(userRole.isPrintDocs());
                addCheck.setValue(userRole.isAddSerialNumber());
                viewCheck.setValue(userRole.isViewPrintHistory());
                acceptRequestCheck.setValue(userRole.isAcceptReprintRequest());
                roleName.setValue(userRole.getRoleName());
                userRoleChange.setVisible(true);

                //changeUser.setVisible(true);
                this.editRole((UserRole) grid.getSelectedRow());

            }
        });
        addNewBtn.addClickListener(e -> {
            userRoleChange.setVisible(true);
            changeUser.setVisible(true);
            editRole(new UserRole(false,false,false,false,false,""));
            //userRoleChange.setVisible(false);
            //changeUser.setVisible(false);
        });

        listRoles(null);
    }
        public interface ChangeHandler {

            void onChange();
        }
    public final void editRole(UserRole role){
        final boolean persisted = role.getId() != null;

        if (persisted){
            userRole = roleRepository.findOne(role.getId());
            role.setAddSerialNumber(addCheck.getValue());
            role.setAdmin(adminCheck.getValue());
            role.setViewPrintHistory(viewCheck.getValue());
            role.setPrintDocs(printCheck.getValue());
            role.setAcceptReprintRequest(acceptRequestCheck.getValue());
            role.setRoleName(roleName.getValue());

        }
        else {
            addCheck.clear();
            adminCheck.clear();
            viewCheck.clear();
            printCheck.clear();
            acceptRequestCheck.clear();
            roleName.clear();
            userRole = role;
        }

        setVisible(true);

        save.focus();

    }
    public void setChangeHandler(RoleAddView.ChangeHandler h){
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }
    private void listRoles(String text) {
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(
                    new BeanItemContainer(UserRole.class, roleRepository.findAll()));

        }
    }
    }

