package com.zkzn.les.uas.config;

import com.zkzn.les.uas.service.impl.AuthoUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


/**.
 * OAuth2服务配置
 * @author wangzhou
 *
 */
@Configuration

@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

    
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
	private AuthoUserDetailsServiceImpl authoUserDetailsServiceImpl; 
    @Autowired
    private AuthorizationServerTokenServices   authorizationServerTokenServices;
    
    @Bean
    public RedisTokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }
    
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		
				//启用oauth2管理
	   endpoints.authenticationManager(authenticationManager)
	   			//用户管理
       			.userDetailsService(authoUserDetailsServiceImpl)
	   			//token存到redis
	   			.tokenStore(tokenStore())
       			//.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter())
	   			//接收GET和POST
	   			.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	}
	
    @Override
    public void configure(final  AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");


    }

    @Override
    public void configure(final  ClientDetailsServiceConfigurer clients) throws Exception {


        clients.inMemory()
                .withClient("les-oms-ssoclient")//用户名
                .secret(passwordEncoder.encode("les-oms-ssosecret"))//密码
                .authorizedGrantTypes("authorization_code")//授权码模式
                .scopes("user_info") //授权范围
                .autoApprove(true)//开启自动授权
                .redirectUris("http://localhost:8081/oms/login").accessTokenValiditySeconds(3600)
                
               .and().withClient("les-wms-ssoclient").secret(passwordEncoder.encode("les-wms-ssosecret"))
               .authorizedGrantTypes("authorization_code")
               .scopes("user_info") //授权范围
 			   .autoApprove(true)//自动授权
 			   .redirectUris("http://localhost:8082/login").accessTokenValiditySeconds(3600)
 			   
 			  .and().withClient("les-api-ssoclient").secret(passwordEncoder.encode("les-api-ssosecret"))
              .authorizedGrantTypes("authorization_code")
              .scopes("user_info") //授权范围
              .autoApprove(true)//自动授权
              .redirectUris("http://localhost:8083/api/login").accessTokenValiditySeconds(3600)
              
              .and().withClient("les-basicInfo-ssoclient").secret(passwordEncoder.encode("les-basicInfo-ssosecret"))
              .authorizedGrantTypes("authorization_code")
              .scopes("user_info") //授权范围
              .autoApprove(true)//自动授权
              .redirectUris("http://localhost:8084/basicInfo/login").accessTokenValiditySeconds(3600)
 			   
              .and().withClient("les-dispatch-ssoclient").secret(passwordEncoder.encode("les-dispatch-ssosecret"))
              .authorizedGrantTypes( "authorization_code")
              .scopes("user_info") //授权范围
              .autoApprove(true)//自动授权
              .redirectUris("http://localhost:8085/dispatch/login").accessTokenValiditySeconds(3600)
              
              .and().withClient("les-decision-ssoclient").secret(passwordEncoder.encode("les-decision-ssosecret"))
              .authorizedGrantTypes("authorization_code")
              .autoApprove(true)//自动授权
              .scopes("user_info") //授权范围
              .redirectUris("http://localhost:8086/decision/login").accessTokenValiditySeconds(3600)
              
 		       .and().withClient("les-message-ssoclient").secret(passwordEncoder.encode("les-message-ssosecret"))
 		 	   .authorizedGrantTypes( "authorization_code")
 		 	   .scopes("user_info") //授权范围
 		 	   .autoApprove(true)//自动授权
 		 	   .redirectUris("http://localhost:8087/message/login").accessTokenValiditySeconds(3600)
 		 	   
 		 	   .and().withClient("les-monitor-ssoclient").secret(passwordEncoder.encode("les-monitor-ssosecret"))
 		 	   .authorizedGrantTypes( "authorization_code")
 		 	   .scopes("user_info") //授权范围
 		 	   .autoApprove(true)//自动授权
 		 	   .redirectUris("http://localhost:8089/monitor/login").accessTokenValiditySeconds(3600)
                     
 			   .and().withClient("les-quartz-ssoclient").secret(passwordEncoder.encode("les-quartz-ssosecret"))
 		 	   .authorizedGrantTypes("authorization_code")
 		 	   .scopes("user_info") //授权范围
 		 	   .autoApprove(true)//自动授权
 			   .redirectUris("http://localhost:8090/quartz/login").accessTokenValiditySeconds(3600)//设置超时时间
        
		        .and().withClient("les-gateway-ssoclient").secret(passwordEncoder.encode("les-gateway-ssosecret"))
		 	   .authorizedGrantTypes("authorization_code")
		 	   .scopes("user_info") //授权范围
		 	   .autoApprove(true)//自动授权
			   .redirectUris("http://localhost:8095/login").accessTokenValiditySeconds(3600);//设置超时时间
    }

	public AuthorizationServerTokenServices getAuthorizationServerTokenServices() {
		return authorizationServerTokenServices;
	}

    
    
	
}
