/*
 * Copyright (c) 2016 Uber Technologies, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.uber.sdk.core.auth;

import com.squareup.moshi.Moshi;
import com.uber.sdk.core.auth.internal.OAuth2Service;
import com.uber.sdk.core.auth.internal.OAuthScopesAdapter;
import com.uber.sdk.core.client.SessionConfiguration;
import com.uber.sdk.core.client.internal.ApiInterceptor;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class AccessTokenAuthenticator extends
        BaseRefreshableAuthenticator implements Authenticator {

    private static final String HEADER_BEARER_ACCESS_VALUE = "Bearer %s";
    private static final String TOKEN_URL = "%s/oauth/v2/mobile/";

    private final SessionConfiguration sessionConfiguration;
    private final AccessTokenStorage tokenStorage;
    private final OAuth2Service auth2Service;

    public AccessTokenAuthenticator(SessionConfiguration sessionConfiguration,
                                    AccessTokenStorage tokenStorage) {
        this(sessionConfiguration,
                tokenStorage,
                createOAuthService(String.format(TOKEN_URL, sessionConfiguration.getLoginHost())));
    }

    AccessTokenAuthenticator(SessionConfiguration sessionConfiguration,
                             AccessTokenStorage tokenStorage,
                             OAuth2Service auth2Service) {
        this.sessionConfiguration = sessionConfiguration;
        this.tokenStorage = tokenStorage;
        this.auth2Service = auth2Service;
    }

    @Override
    public void signRequest(Request.Builder builder) {
        if(tokenStorage.getAccessToken() != null && tokenStorage.getAccessToken().getToken() !=
                null) {
            setBearerToken(builder, tokenStorage.getAccessToken());
        }
    }

    @Override
    public boolean isRefreshable() {
        return tokenStorage.getAccessToken() != null && tokenStorage.getAccessToken().getRefreshToken() != null;
    }

    /**
     * Get SessionConfiguration used for authentication
     */
    @Override
    public SessionConfiguration getSessionConfiguration() {
        return sessionConfiguration;
    }

    /**
     * Get AccessTokenStorage used for authentication
     */
    public AccessTokenStorage getTokenStorage() {
        return tokenStorage;
    }

    protected synchronized Request doRefresh(Response response) throws IOException {
        final AccessToken token = tokenStorage.getAccessToken();

        if (signedByOldToken(response, token)) {
            return resign(response, token);
        } else {
            return refreshAndSign(response, token);
        }
    }

    Request resign(Response response, AccessToken auth2Token) {
        Request.Builder builder = response.request().newBuilder();
        setBearerToken(builder, auth2Token);

        return builder.build();
    }

    Request refreshAndSign(Response response, AccessToken auth2Token) throws IOException {
        AccessToken token = refreshToken(auth2Token);
        return resign(response, token);
    }

    AccessToken refreshToken(AccessToken auth2Token) throws IOException {
        AccessToken newToken = auth2Service.refresh(auth2Token.getRefreshToken(),
                sessionConfiguration.getClientId())
                .execute().body();
        tokenStorage.setAccessToken(newToken);
        return newToken;
    }

    boolean signedByOldToken(Response response, AccessToken oAuth2Token) {
        String value = ApiInterceptor.getAuthorizationHeader(response.request());

        String accessToken = createBearerToken(oAuth2Token);

        return value != null && !value.equals(accessToken);
    }

    void setBearerToken(Request.Builder builder, AccessToken OAuth2Token) {
        ApiInterceptor.setAuthorizationHeader(builder, createBearerToken(OAuth2Token));
    }

    String createBearerToken(AccessToken oAuth2Token) {
        return String.format(HEADER_BEARER_ACCESS_VALUE, oAuth2Token.getToken());
    }

    static OAuth2Service createOAuthService(String baseUrl) {
        final Moshi moshi = new Moshi.Builder().add(new OAuthScopesAdapter()).build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(OAuth2Service.class);
    }
}
