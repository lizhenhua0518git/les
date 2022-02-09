package com.zkzn.les.uas.config;

import com.zkzn.les.uas.service.impl.AuthoUserDetailsServiceImpl;
import com.zkzn.les.uas.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**.
 * 服务端：安全策略配置 
 * @author wangzhou
 *
 */
@Configuration
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthoUserDetailsServiceImpl authoUserDetailsServiceImpl; 
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	@Autowired
	private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private FailureHandler failureHandler;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
    	PasswordEncoder passwordEncoder = new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// TODO Auto-generated method stub
				return encodedPassword.equals(MD5.getStringMD5((String)rawPassword));
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return MD5.getStringMD5((String)rawPassword);
			}
		};
	   return passwordEncoder;
    }
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
            .antMatchers("/login","/login/**")
            .antMatchers("/oauth/authorize")
            .antMatchers("/css/**")
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .and()
            .authorizeRequests()
            .antMatchers("/login/changePassWord","/druid/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .and()
            .logout().logoutUrl("/simpleLogout")
            .and().csrf().disable();
        //http.exceptionHandling().authenticationEntryPoint(new CustomizeAuthenticationEntryPoint());
        http.cors().configurationSource(CorsConfigurationSource());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用内存模拟数据库查询的用户
       /* auth.inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("123456"))
            .roles("ADMIN");*/
    	auth.userDetailsService(authoUserDetailsServiceImpl)
		.passwordEncoder(passwordEncoder());
		//记着功能
		auth.eraseCredentials(false);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    public CorsConfigurationSource CorsConfigurationSource() {
    	CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");	//同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");	//允许的请求方法，PSOT、GET等
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
        return source;
    }


}
