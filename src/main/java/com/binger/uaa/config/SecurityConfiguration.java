package com.binger.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author binger
 * Date 2018-04-19
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;


    @Autowired
    private AuthenticationTokenFilter myAuthenticationTokenFilter;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
//    }


//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
////        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        password 方案一：明文存储，用于测试，不能用于生产
////        String finalPassword = "1";
////        password 方案二：用 BCrypt 对密码编码
////        String finalPassword = bCryptPasswordEncoder.encode("123456");
//        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
////        String finalPassword = "{bcrypt}"+bCryptPasswordEncoder.encode("123456");
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
////        manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
//        UserExample example = new UserExample();
//        UserExample.Criteria criteria= example.createCriteria();
//        criteria.andStatusEqualTo(1);
//        List<com.binger.uaa.domain.User> userList = userService.listByExample(example);
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        userList.forEach(user -> {
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            String password = bCryptPasswordEncoder.encode(user.getUserPassword());
//            manager.createUser(User.withUsername(user.getUserName()).password(password).authorities("USER").build());
//        });
//        return manager;
//    }

    /**
     * springboot2.0 删除了原来的 plainTextPasswordEncoder
     * https://docs.spring.io/spring-security/site/docs/5.0.4.RELEASE/reference/htmlsingle/#10.3.2 DelegatingPasswordEncoder
     *
     */


    // password 方案一：明文存储，用于测试，不能用于生产
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    // password 方案二：用 BCrypt 对密码编码
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    // password 方案三：支持多种编码，通过密码的前缀区分编码方式,推荐
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    //
//    /**
//     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
//     */

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/revoke-token","/user/online");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http
//                .requestMatchers().anyRequest()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**").permitAll();
//        // @formatter:on
//    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().exceptionHandling().
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterAfter(myAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("ADMIN");
        auth.authenticationProvider(myAuthenticationProvider);
    }
}
