package com.github.parkhana.vo;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginSession {

    private static final long serialVersionUID = 1L;

    private String access_token;
    private String refresh_token;
    private String token_type;
    private String expires_in;
}
