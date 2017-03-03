package com.cwd.auth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/2/25.
 */
@Service
public class EHRAccessTokenService {
    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OAuth2AccessToken getAccessTokenByClientId(String client_id) {
        OAuth2AccessToken accessToken = null;
        String sql="select token_id, token,max(create_time)  from oauth_access_token where client_id = ?";
        try {
            accessToken = jdbcTemplate.queryForObject(sql,
                    new RowMapper<OAuth2AccessToken>() {
                        public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return deserializeAccessToken(rs.getBytes(2));
                        }
                    }, client_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
        return SerializationUtils.deserialize(token);
    }
}
