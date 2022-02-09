package com.zkzn.les.gateway.config;

import com.zkzn.les.gateway.feign.AuthorFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**.
 *
 * @author wangzhou
 * @date 2020年7月17日
 * @Description：验证用户是否有访问此url权限
 */
@Component
@ConfigurationProperties(prefix = "spring.security")
public class RdbaAccessManager implements ReactiveAuthorizationManager<AuthorizationContext>{

	private String[] ignoreds;//存放不需要授权的url地址

    private String[] notRoles;//存放只需要登录，不需要控件角色的url地址
    @Autowired
    private AuthorFeignService authorFeignService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AntPathMatcher matcher = new AntPathMatcher();

	@Override
	public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
		// TODO Auto-generated method stub

		//获取请求
        ServerHttpRequest request =  authorizationContext.getExchange().getRequest();
        String url =request.getPath().value();
        logger.debug("当前请求url:{}",url);
        String httpMethod = request.getMethod().name();
        logger.debug("请求方法:{}",httpMethod);
        //如果是OPTIONS的请求直接放过
        if(HttpMethod.OPTIONS.name().equals(httpMethod)){
            return Mono.just(new AuthorizationDecision(true));
        }
        logger.debug("白名单："+ Arrays.toString(ignoreds));
        for (String path : ignoreds) {//不需要登录权限访问的url直接放行
            String temp = path.trim();
            if (matcher.match(temp, url)) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        logger.debug("不需要角色权限判断的接口：{}",Arrays.toString(notRoles));
        for (String path : notRoles) {
            String temp = path.trim();
            if (matcher.match(temp, url)) {
                //对于不需要验证角色的接口，只要token验证成功返回成功即可
                return authentication.map(a ->  {
                    if(a.isAuthenticated()){
                        return new AuthorizationDecision(true);
                    }else{
                        return new AuthorizationDecision(false);
                    }
                }).defaultIfEmpty(new AuthorizationDecision(false));
            }
        }

        //需要进行权限验证的
        return authentication.map(a ->  {
            if(a.isAuthenticated()){
            	HttpHeaders headers = request.getHeaders();
            	List<String> authHeaderValues = headers.get("Authorization");
            	String authHeaderValue = null;
            	if(authHeaderValues!=null && authHeaderValues.size()>0){
        			authHeaderValue = authHeaderValues.get(0);
        			authHeaderValue = authHeaderValue.substring(OAuth2AccessToken.TokenType.BEARER.getValue().length()).trim();
        			int commaIndex = authHeaderValue.indexOf(',');
        			if (commaIndex > 0) {
        				authHeaderValue = authHeaderValue.substring(0, commaIndex);
        			}
        		}
                boolean ispermissed = false;
//                Map<String,Object> cache = (Map<String, Object>) RedisUtil.getCache(redisTemplate, authHeaderValue);
//                String phone = cache.get("phone")+"";
                //if ("admin".equals(phone)){//admin不需要权限控制
                    return new AuthorizationDecision(true);
                //}
//                if (cache.get("userUrlMap") == null){
//                    return new AuthorizationDecision(false);
//                }else {
//                    Map<String,Object> userUrlMap = (Map<String, Object>) cache.get("userUrlMap");
//                    logger.debug("请求权限:"+userUrlMap);
//                    logger.debug("请求:"+url);
//                    if (userUrlMap.containsKey(url)){
//                        ispermissed = true;
//                    }
////                String resultStr = authorFeignService.isPermission(url,authHeaderValue);
////            	logger.debug("resultStr:"+resultStr);
////            	JSONObject  jsonObject = JSONObject.parseObject(resultStr);
////            	boolean ispermissed = false;
////            	if("0".equals(""+jsonObject.get("code"))){
////            		ispermissed = (Boolean) jsonObject.get("data");
////            	}
//                    return new AuthorizationDecision(ispermissed);
//                }
            }else{
                return new AuthorizationDecision(false);
            }
        }).defaultIfEmpty(new AuthorizationDecision(false));
	}

	public void setIgnored(String ignored) {
        ignored = org.springframework.util.StringUtils.trimAllWhitespace(ignored);
        if (ignored != null && !"".equals(ignored)) {
            this.ignoreds = ignored.split(",");
        } else {
            this.ignoreds = new String[]{};
        }
    }

    public void setNotRole(String notRole) {
        notRole = org.springframework.util.StringUtils.trimAllWhitespace(notRole);
        if (notRole != null && !"".equals(notRole)) {
            this.notRoles = notRole.split(",");
        } else {
            this.notRoles = new String[]{};
        }
    }
}
