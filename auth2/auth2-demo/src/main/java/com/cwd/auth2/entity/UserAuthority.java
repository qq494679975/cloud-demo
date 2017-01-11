package com.cwd.auth2.entity;

import javax.persistence.*;

/**
 * @author linaz
 * @created 2016.08.24 15:25
 *
 * Spring Security logout handler
 */
@Entity
@Table(name = "user_authority")
@Access(AccessType.FIELD)
public class UserAuthority {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "authority", nullable = false)
    private String authority;


//    @ManyToMany
//    @JoinTable(
//            name = "user_authority",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "authority"))
//    private Set<Authority> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }


//    public Set<Authority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<Authority> authorities) {
//        this.authorities = authorities;
//    }
}
