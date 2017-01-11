package com.cwd.auth2.config.auth2;

import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/1/11.
 */

public class ClientDetailsService extends JdbcClientDetailsService {


    public ClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }


    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        super.removeClientDetails(clientId);
    }
}
