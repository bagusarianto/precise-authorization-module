package com.programato.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_roles", schema = "security")
public class UserRole extends PanacheEntityBase implements Serializable {

    @Id
    @JsonProperty("user_id")
    @Column(name = "user_id", nullable = false)
    public Long userId;

    @Id
    @JsonProperty("role_id")
    @Column(name = "role_id", nullable = false)
    public Long roleId;


    @JsonProperty("created_at")
    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(userId, userRole.userId) &&
                Objects.equals(roleId, userRole.roleId) &&
                Objects.equals(createdAt, userRole.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId, createdAt);
    }
}
