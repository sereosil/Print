package print_bd.entity;

/**
 * Created by sereo_000 on 16.09.2016.
 */

import javax.persistence.*;
import java.util.List;

@Entity@Table(name="user_table")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String contact;
    @ManyToOne(fetch = FetchType.EAGER)
    private UserRole userRole;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<PrintCount> printPermissions;
    private String firstName;
    private String lastName;
    private String email;
    private String passportNumber;
    private String passwordHash;
    private String jobOfUser;
    private boolean needToChangePassword=true;
    protected User(){
    }

    public User(String contact, UserRole userRole, String firstName, String lastName, String email, String passwordHash, String jobOfUser, String passportNumber) {
        this.contact = contact;
        this.userRole = userRole;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passportNumber = passportNumber;
        this.jobOfUser = jobOfUser;
    }

    public User(String firstName, String lastName, String email, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getJobOfUser() {
        return jobOfUser;
    }

    public void setJobOfUser(String jobOfUser) {
        this.jobOfUser = jobOfUser;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNeedToChangePassword() {
        return needToChangePassword;
    }

    public void setNeedToChangePassword(boolean needToChangePassword) {
        this.needToChangePassword = needToChangePassword;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return
                "Имя: '" + firstName + '\'' +
                ", Фамилия: '" + lastName + '\'' +
                ", Телефон: '" + contact + '\'' +
                '}';
    }
}
