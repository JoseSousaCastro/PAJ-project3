package aor.paj.project3.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Table(name = "user")
public class UserEntity implements Serializable {

    @Id
    @Column(name="email", nullable=false, unique = true, updatable = false)
    private String email;

    @Column(name="name", nullable=false, unique = false, updatable = true)
    private String name;

    @Column(name="token", nullable=true, unique = true, updatable = true)
    private String token;

    @Column(name="password", nullable=false, unique = false, updatable = true)
    private String password;

    @Column(name="username", nullable=false, unique = false, updatable = true)
    private String username;

    public UserEntity() {
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
