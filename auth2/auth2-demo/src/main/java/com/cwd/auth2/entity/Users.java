package com.cwd.auth2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Administrator on 2017/1/11.
 */
@Entity
@Table(name = "users")
public class Users extends IdEntity implements UserDetails {
    private static final long serialVersionUID = -5754320739206056347L;
    private String user;
    private String pw;
    private Integer status;

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Transient
    public String getPassword() {
        return pw;
    }

    @Transient
    public String getUsername() {
        return user;
    }

    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    public boolean isEnabled() {
        if (status == 1) {
            return true;
        } else {
            return false;
        }
    }
}
