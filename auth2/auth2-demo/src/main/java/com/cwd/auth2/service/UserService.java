package com.cwd.auth2.service;

import com.cwd.auth2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/11.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    /**
     * springSecurity需要
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUser(username);
    }
}
