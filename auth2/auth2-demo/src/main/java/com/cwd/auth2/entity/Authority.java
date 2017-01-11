package com.cwd.auth2.entity;

import javax.persistence.*;

/**
 * @author linaz
 * @created 2016.08.24 15:25
 *
 * Spring Security logout handler
 */
@Entity
@Table(name = "oauth_authority")
@Access(AccessType.FIELD)
public class Authority {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "name", nullable = true)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}