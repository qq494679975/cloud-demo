package com.cwd.auth2.service;

import com.cwd.auth2.config.auth2.authorization.EHRJdbcClientDetailsService;
import com.cwd.auth2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/11.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private EHRJdbcClientDetailsService clientDetailsService;
    private String emptyPassword = "";

    /**
     * @param passwordEncoder the password encoder to set
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.emptyPassword = passwordEncoder.encode("");
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDetails clientDetails;
        try {
            clientDetails = clientDetailsService.loadClientByClientId(username);
        } catch (NoSuchClientException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
        String clientSecret = clientDetails.getClientSecret();
        if (clientSecret == null || clientSecret.trim().length() == 0) {
            clientSecret = emptyPassword;
        }
        return new org.springframework.security.core.userdetails.User(username, clientSecret, clientDetails.getAuthorities());
    }
}
