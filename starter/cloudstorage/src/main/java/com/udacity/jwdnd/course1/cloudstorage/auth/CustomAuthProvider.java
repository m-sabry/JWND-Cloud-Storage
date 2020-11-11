package com.udacity.jwdnd.course1.cloudstorage.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // TODO
        return false;
    }
}
