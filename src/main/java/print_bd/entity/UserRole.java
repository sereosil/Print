package print_bd.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sereo_000 on 16.09.2016.
 */
@Entity
public class UserRole {
    @Id
    @GeneratedValue
    private Integer id;
    private String roleName;
    private boolean admin = false;
    private boolean view = false;
    private boolean add = false;
    private boolean print = false;
    /*@ManyToMany(fetch = FetchType.EAGER)
    private List<PrintCount> printCounts;*/
    protected UserRole() {
    }

    public UserRole(boolean admin, boolean view, boolean add, boolean print,String roleName) {
        this.roleName = roleName;
        this.admin = admin;
        this.view = view;
        this.add = add;
        this.print = print;
    }

    /*public List<PrintCount> getPrintCounts() {
        return printCounts;
    }*/

    /*public void setPrintCounts(List<PrintCount> printCounts) {
        this.printCounts = printCounts;
    }*/

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }
}
