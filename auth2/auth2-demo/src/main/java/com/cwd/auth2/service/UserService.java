package com.cwd.auth2.service;

import com.cwd.auth2.config.auth2.authorization.EHRJdbcClientDetailsService;
import com.cwd.auth2.dao.UserDao;
import com.cwd.auth2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/11.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private EHRJdbcClientDetailsService clientDetailsService;
    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDetails clientDetails;
        try {
            User user = userDao.findByUsername(username);
            clientDetails = clientDetailsService.loadClientByClientId(user.getClientId());
        } catch (NoSuchClientException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
        String clientSecret = clientDetails.getClientSecret();
        return new org.springframework.security.core.userdetails.User(username, clientSecret, clientDetails.getAuthorities());
    }
}
