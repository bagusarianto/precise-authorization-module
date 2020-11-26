package com.programato.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "roles", schema = "security")
public class Role extends PanacheEntityBase {

    @Id
    @GeneratedValue
    @JsonProperty("role_id")
    @Column(name = "role_id", nullable = false)
    public Long roleId;


    @JsonProperty("role_name")
    @Column(name = "role_name")
    public String roleName;


    @JsonProperty("role_description")
    @Column(name = "role_description")
    public String roleDescription;

}
