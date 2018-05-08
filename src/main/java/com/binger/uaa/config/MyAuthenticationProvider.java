package com.binger.uaa.config;

import com.binger.uaa.domain.User;
import com.binger.uaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Collection;

/**　
 * 用户验证提供者
 */

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    /**最大密码尝试次数*/
    private final Integer MAX_PWD_TRY_TIMES = 5;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = DigestUtils.md5DigestAsHex( ((String)authentication.getCredentials()).getBytes() );
//        String password = authentication.getCredentials().toString();
        UserDetails userDetail = userDetailsService.loadUserByUsername(username);
        User user = userService.findUserByName(userDetail.getUsername());
        User selective = new User();
        selective.setId(user.getId());
        if (!password.equals(userDetail.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetail, password, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}