package com.cwd.auth2.config.auth2;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 2016/7/23
 *
 */
@Component
public class EHRAuthorizationCodeServices extends JdbcAuthorizationCodeServices {


    public EHRAuthorizationCodeServices(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        super.store(code, authentication);
    }

    @Override
    public OAuth2Authentication remove(String code) {
        return super.remove(code);
    }
}
