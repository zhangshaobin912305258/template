package com.zhang.template.security;

import cn.hutool.json.JSONUtil;
import com.zhang.template.constants.ResultState;
import com.zhang.template.entity.User;
import com.zhang.template.exception.BusinessException;
import com.zhang.template.util.PasswordEncoder;
import com.zhang.template.vo.user.LoginUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 身份验证提供者
 */
public class JwtAuthenticationProvider extends DaoAuthenticationProvider {

  public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
    setUserDetailsService(userDetailsService);
  }

  @Override
  protected void additionalAuthenticationChecks(
      UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    if (authentication.getCredentials() == null) {
      logger.debug("Authentication failed: no credentials provided");
      throw new BadCredentialsException(
          messages.getMessage(
              "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
    }

    String presentedPassword = authentication.getCredentials().toString();
    User user = ((LoginUser) userDetails).getUser();
    String salt = user.getSalt();
    // 覆写密码验证逻辑
    if (! PasswordEncoder.matches(salt,userDetails.getPassword(),presentedPassword)) {
      logger.info(String.format("密码校验失败,用户信息:s%", JSONUtil.toJsonStr(user)));
      throw new BusinessException(ResultState.INCORRECT_USER);
    }
   /* if (!new PasswordEncoder(salt).matches(userDetails.getPassword(), presentedPassword)) {
      logger.debug("Authentication failed: password does not match stored value");
      throw new BadCredentialsException(
          messages.getMessage(
              "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
    }*/
  }
}
