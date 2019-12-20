package com.zhang.template.config;

import com.zhang.template.util.PasswordEncoder;
import com.zhang.template.vo.JwtUserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 身份验证组件提供者
 */
public class JwtAuthenticationProvider extends DaoAuthenticationProvider {
     
    public JwtAuthenticationProvider (UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        String presentedPassword = authentication.getPrincipal().toString();
        String salt = ((JwtUserDetails) userDetails).getSalt();
        //覆写密码验证逻辑
        if(!new PasswordEncoder(salt).matches(userDetails.getPassword(),presentedPassword)) {
            logger.debug("密码校验失败");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }
}
