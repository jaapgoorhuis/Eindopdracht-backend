package com.example.Eindopdracht.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public String getRolename() {
        return rolename;
    }
}