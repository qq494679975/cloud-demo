package com.cwd.auth2.controller;/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */

import com.cwd.auth2.config.auth2.EHRAuthorizationCodeServices;
import com.cwd.auth2.config.auth2.authorization.EHRJdbcClientDetailsService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * 2016/3/8
 * <p>
 * Restful OAuth API
 * <p>
 * 扩展默认的OAuth 功能,  提供 Restful API,
 * 可用于在获取access_token时调用
 *
 * @see org.springframework.security.oauth2.provider.endpoint.TokenEndpoint
 */
@Api(description = "获取access_token时调用")
@RestController
@RequestMapping(value = "/oauth")
public class OAuthRestController implements InitializingBean, ApplicationContextAware {


    private static final Logger LOG = LoggerFactory.getLogger(OAuthRestController.class);

    @Autowired
    private EHRJdbcClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationServerTokenServices tokenServices;
    @Autowired
    private EHRAuthorizationCodeServices authorizationCodeServices;

    private AuthenticationManager authenticationManager;

    private OAuth2RequestFactory oAuth2RequestFactory;

    private OAuth2RequestValidator oAuth2RequestValidator = new DefaultOAuth2RequestValidator();
    private WebResponseExceptionTranslator providerExceptionHandler = new DefaultWebResponseExceptionTranslator();

    /**
     * 请求必须是post
     * 内容放在请求体(json格式)
     * 可以传参数
     * {
     *     client_id:"客户端id",
     *     grant_type:"请求模式-----------------------   authorization_code — 授权码模式(即先登录获取code,再获取token)
                                                         password  密码模式(将用户名,密码传过去,直接获取token)
                                                         client_credentials — 客户端模式(无用户,用户向客户端注册,然后客户端以自己的名义向’服务端’获取资源)
                                                         implicit — 简化模式(在redirect_uri 的Hash传递token; Auth客户端运行在浏览器中,如JS,Flash)
                                                         refresh_token — 刷新access_token

     * }
     *
     *
     * @param parameters
     * @return
     */
    @RequestMapping(value = "/rest_token", method = RequestMethod.POST)
    @ResponseBody
    public OAuth2AccessToken postAccessToken(@RequestBody Map<String, String> parameters) {


        String clientId = getClientId(parameters);
        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);

        TokenRequest tokenRequest = oAuth2RequestFactory.createTokenRequest(parameters, authenticatedClient);

        if (clientId != null && !"".equals(clientId)) {
            // Only validate the client details if a client authenticated during this
            // request.
            if (!clientId.equals(tokenRequest.getClientId())) {
                // double check to make sure that the client ID in the token request is the same as that in the
                // authenticated client
                throw new InvalidClientException("client ID对应的客户端不存在");
            }
        }

        if (authenticatedClient != null) {
            oAuth2RequestValidator.validateScope(tokenRequest, authenticatedClient);
        }

        final String grantType = tokenRequest.getGrantType();
        if (!StringUtils.hasText(grantType)) {
            throw new InvalidRequestException("grant type为空");
        }
        if ("implicit".equals(grantType)) {
            throw new InvalidGrantException("Implicit grant type not supported from token endpoint");
        }

        if (isAuthCodeRequest(parameters)) {
            // The scope was requested or determined during the authorization step
            if (!tokenRequest.getScope().isEmpty()) {
                tokenRequest.setScope(Collections.<String>emptySet());
            }
        }


        if (isRefreshTokenRequest(parameters)) {
            // A refresh token has its own default scopes, so we should ignore any added by the factory here.
            tokenRequest.setScope(OAuth2Utils.parseParameterList(parameters.get(OAuth2Utils.SCOPE)));
        }

        OAuth2AccessToken token = getTokenGranter(grantType).grant(grantType, tokenRequest);
        if (token == null) {
            throw new UnsupportedGrantTypeException("不支持的oauth类型: " + grantType);
        }


        return token;

    }

    /**
     *  authorization_code — 授权码模式(即先登录获取code,再获取token)
        password  密码模式(将用户名,密码传过去,直接获取token)
        client_credentials — 客户端模式(无用户,用户向客户端注册,然后客户端以自己的名义向’服务端’获取资源)
        implicit — 简化模式(在redirect_uri 的Hash传递token; Auth客户端运行在浏览器中,如JS,Flash)
        refresh_token — 刷新access_token
     * @param grantType
     * @return
     */
    protected TokenGranter getTokenGranter(String grantType) {
        if ("authorization_code".equals(grantType)) {
            //授权码模式
            return new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService, this.oAuth2RequestFactory);
        } else if ("password".equals(grantType)) {
            //密码模式
            return new ResourceOwnerPasswordTokenGranter(getAuthenticationManager(), tokenServices, clientDetailsService, this.oAuth2RequestFactory);
        } else if ("refresh_token".equals(grantType)) {
            //刷新access_token
            return new RefreshTokenGranter(tokenServices, clientDetailsService, this.oAuth2RequestFactory);
        } else if ("client_credentials".equals(grantType)) {
            //客户端模式
            return new ClientCredentialsTokenGranter(tokenServices, clientDetailsService, this.oAuth2RequestFactory);
        } else if ("implicit".equals(grantType)) {
            //简化模式
            return new ImplicitTokenGranter(tokenServices, clientDetailsService, this.oAuth2RequestFactory);
        } else {
            throw new UnsupportedGrantTypeException("不支持的类型grant_type: " + grantType);
        }
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<OAuth2Exception> handleException(Exception e) throws Exception {
        LOG.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        return getExceptionTranslator().translate(e);
    }

    @ExceptionHandler(ClientRegistrationException.class)
    public ResponseEntity<OAuth2Exception> handleClientRegistrationException(Exception e) throws Exception {
        LOG.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        return getExceptionTranslator().translate(new BadClientCredentialsException());
    }

    @ExceptionHandler(OAuth2Exception.class)
    public ResponseEntity<OAuth2Exception> handleException(OAuth2Exception e) throws Exception {
        LOG.info("Handling error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        return getExceptionTranslator().translate(e);
    }


    private boolean isRefreshTokenRequest(Map<String, String> parameters) {
        return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
    }

    private boolean isAuthCodeRequest(Map<String, String> parameters) {
        return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
    }


    protected String getClientId(Map<String, String> parameters) {
        return parameters.get("client_id");
    }

    private AuthenticationManager getAuthenticationManager() {
        return this.authenticationManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Assert.state(clientDetailsService != null, "ClientDetailsService must be provided");
        Assert.state(authenticationManager != null, "AuthenticationManager must be provided");

        oAuth2RequestFactory = new DefaultOAuth2RequestFactory(clientDetailsService);
    }

    protected WebResponseExceptionTranslator getExceptionTranslator() {
        return providerExceptionHandler;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.authenticationManager == null) {
            this.authenticationManager = (AuthenticationManager) applicationContext.getBean(AuthenticationManager.class);
        }
    }
}