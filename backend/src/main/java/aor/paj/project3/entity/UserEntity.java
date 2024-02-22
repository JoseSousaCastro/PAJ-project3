package aor.paj.project3.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
@NamedQuery(name = "User.findUserByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username")
@NamedQuery(name = "User.findUserByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email")
@NamedQuery(name = "User.findUserByToken", query = "SELECT DISTINCT u FROM UserEntity u WHERE u.token = :token")
public class UserEntity implements Serializable{

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name="email", nullable=false, unique = true, updatable = true)
    private String email;

    @Column(name="firstName", nullable = false, unique = false, updatable = true)
    private String firstName;

    @Column(name="lastName", nullable = false, unique = false, updatable = true)
    private String lastName;

    @Column(name="phone", nullable = false, unique = false, updatable = true)
    private String phone;

    @Column(name="profilePhoto", nullable = false, unique = false, updatable = true)
    private String photoURL;


    @Column(name="token", nullable=true, unique = true, updatable = true)
    private String token;

    @Column(name="password", nullable=false, unique = false, updatable = true)
    private String password;

    @Column(name="username", nullable=false, unique = true, updatable = false)
    private String username;

    @OneToMany(mappedBy = "owner")
    private Set<TaskEntity> tasks;


    //default empty constructor
    public UserEntity() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public void setPhotoURL(String profilePhoto) {
        this.photoURL = profilePhoto;
    }
}

