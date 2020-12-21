package com.shinley.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthticationToken authticationToken = (SmsCodeAuthticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername(authticationToken.getPrincipal().toString());
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsCodeAuthticationToken authticationTokenResult = new SmsCodeAuthticationToken(user, user.getAuthorities());
        authticationTokenResult.setDetails(authticationToken.getDetails());
        return authticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return SmsCodeAuthticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
