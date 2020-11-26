package com.programato.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "security")
public class User extends PanacheEntityBase {

 
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            schema = "security",
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    public Set<com.programato.models.Role> roles = new HashSet<>();

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "email")
    public String email;

    @Column(name = "email_verified_at")
    public LocalDateTime emailVerifiedAt;

    @Column(name = "password")
    public String password;

    @Column(name = "remember_token")
    public String rememberToken;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Column(name = "employee_id")
    public Long employeeId;

    @Column(name = "flag_active")
    public Boolean flagActive;
    @Column(name = "last_login")
    public LocalDateTime lastLogin;

    public static User findUser(String username) {
        return find("name=?1 ", username).firstResult();
    }
}
