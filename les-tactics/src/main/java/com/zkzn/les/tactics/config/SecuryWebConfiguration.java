package com.zkzn.les.tactics.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Order(300)
public class SecuryWebConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> permitAllResouces= new ArrayList<>();
        //Swagger资源
        permitAllResouces.addAll(Arrays.asList("/data/**","/oauth/**", "/login/**", "/logout/**","/test/**","/swaggger-ui.html","/doc.html", "/v2/**", "/swagger-resources", "/swagger-resources/**", "/wms/oauth/authorize/","/webjars/**"));
        String[] resouceArray=permitAllResouces.toArray(new String[]{});
        http.csrf().disable();
        http
                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin().permitAll()
        .and().authorizeRequests().mvcMatchers(resouceArray).permitAll(); //新增login form支持用户登录及授权
        http.exceptionHandling().authenticationEntryPoint(new CustomizeAuthenticationEntryPoint());
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                        inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user").password("1234").roles("ADMIN");
    }
}
