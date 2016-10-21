package print_bd.entity;

import javax.persistence.*;

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
    private boolean viewPrintHistory = false;
    private boolean addSerialNumber = false;
    private boolean printDocs = false;
    private boolean acceptReprintRequest = false;
    /*@ManyToMany(fetch = FetchType.EAGER)
    private List<PrintCount> printCounts;*/
    protected UserRole() {
    }

    public UserRole(boolean admin, boolean viewPrintHistory, boolean addSerialNumber, boolean printDocs,boolean acceptReprintRequest, String roleName) {
        this.roleName = roleName;
        this.admin = admin;
        this.viewPrintHistory = viewPrintHistory;
        this.addSerialNumber = addSerialNumber;
        this.printDocs = printDocs;
        this.acceptReprintRequest=acceptReprintRequest;
    }

    /*public List<PrintCount> getPrintCounts() {
        return printCounts;
    }*/

    /*public void setPrintCounts(List<PrintCount> printCounts) {
        this.printCounts = printCounts;
    }*/

    public boolean isAcceptReprintRequest() {
        return acceptReprintRequest;
    }

    public void setAcceptReprintRequest(boolean acceptReprintRequest) {
        this.acceptReprintRequest = acceptReprintRequest;
    }

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

    public boolean isViewPrintHistory() {
        return viewPrintHistory;
    }

    public void setViewPrintHistory(boolean viewPrintHistory) {
        this.viewPrintHistory = viewPrintHistory;
    }

    public boolean isAddSerialNumber() {
        return addSerialNumber;
    }

    public void setAddSerialNumber(boolean addSerialNumber) {
        this.addSerialNumber = addSerialNumber;
    }

    public boolean isPrintDocs() {
        return printDocs;
    }

    public void setPrintDocs(boolean printDocs) {
        this.printDocs = printDocs;
    }

    @Override
    public String toString() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (admin != userRole.admin) return false;
        if (viewPrintHistory != userRole.viewPrintHistory) return false;
        if (addSerialNumber != userRole.addSerialNumber) return false;
        if (printDocs != userRole.printDocs) return false;
        if (id != null ? !id.equals(userRole.id) : userRole.id != null) return false;
        return roleName != null ? roleName.equals(userRole.roleName) : userRole.roleName == null;

    }

    @Override
    public int hashCode() {
        return 0;
    }
}
