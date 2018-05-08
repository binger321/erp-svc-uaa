package com.binger.uaa.config;

import com.binger.common.security.CompositePrincipal;
import com.binger.common.util.MyEasyJsonUtil;
import com.binger.uaa.domain.User;
import com.binger.uaa.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: zhuyubin
 * Date: 2018/5/7
 * Time: 上午11:25
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByName(username);
        // 以下错误,用户登录会产生401错误, 而错误信息是放在http header里面, 所以要base64加密
        if(user == null){
            throw new UsernameNotFoundException(Base64.encodeBase64String("用户不存在".getBytes()));
        }
        if(user.getStatus().equals(0)){
            throw new UsernameNotFoundException(Base64.encodeBase64String("用户已被禁用".getBytes()));
        }

        CompositePrincipal Principal = new CompositePrincipal();
        Principal.getBasicUser().setUserId(user.getId());
        Principal.getBasicUser().setUserCode(user.getUserCode());
        Principal.getBasicUser().setUserName(user.getUserName());
        Principal.getBasicUser().setPersonId(user.getPersonId());
        Collection<SimpleGrantedAuthority> collection = new ArrayList<>();

        collection.add(new SimpleGrantedAuthority("erpPrincipal"+":"+ Base64.encodeBase64String(MyEasyJsonUtil.json2string(Principal).getBytes())));

        return new org.springframework.security.core.userdetails.User(username, user.getUserPassword(), collection);

    }
}
