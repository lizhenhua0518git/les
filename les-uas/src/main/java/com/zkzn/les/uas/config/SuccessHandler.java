package com.zkzn.les.uas.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zkzn.les.uas.pojo.SecurityUser;
import com.zkzn.les.uas.pojo.User;
import com.zkzn.les.uas.service.LoginService;
import com.zkzn.les.uas.service.OrgnizationService;
import com.zkzn.les.uas.service.UserService;
import com.zkzn.les.uas.util.BeanUtil;
import com.zkzn.les.uas.util.Ecode;
import com.zkzn.les.uas.util.RedisUtil;
import com.zkzn.les.uas.util.Result;
/**.
 * 成功处理器
 * @author wangzhou
 *
 */
@Component
public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  implements AuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(SuccessHandler.class);

	@Autowired
	private LoginService loginService;
	@Autowired
	private OrgnizationService orgnizationService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private AuthorizationServerConfig authorizationServerConfig;

	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("成功成功处理界面"+authentication);
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
		//存入日志表
		loginService.recordLoginInfo(securityUser.getId(),1);
		//默认 初始化登陆人所属组织
		orgnizationService.defualtWarehouse(securityUser.getId());
		User user = new User();
		user.setId(securityUser.getId());
		user.setLastLoginTime(new Date());
		userService.updateUser(user);
		securityUser.setLastLoginTime(user.getLastLoginTime());
		RedisUtil.putCache(redisTemplate, securityUser.getId(), securityUser, 3600);

		String clientId = "les-wms-ssoclient";
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        AuthorizationServerTokenServices authorizationService = authorizationServerConfig.getAuthorizationServerTokenServices();
        OAuth2AccessToken token = authorizationService.createAccessToken(oAuth2Authentication);

        response.setContentType("application/json; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out =  response.getWriter();

		Map<String,Object> userMap = BeanUtil.objectToMap(securityUser);
//		List<String> userUrlList = userService.getUserUrl(securityUser.getId());
//		if (userUrlList.size()>0){
//			Map<String,Object> userUrlMap = new HashMap<>();
//			for (int i = 0; i < userUrlList.size(); i++) {
//				userUrlMap.put(userUrlList.get(i),null);
//			}
//			System.out.println("-------------》"+token.getValue());
//			userMap.put("userUrlMap",userUrlMap);
//		}else {
//			userMap.put("userUrlMap",null);
//		}
		RedisUtil.putCache(redisTemplate, token.getValue(), userMap, 3600);
		System.out.println(userMap);

		userMap = new HashMap<>();
        userMap.put("userName", securityUser.getUsername());
        userMap.put("password", securityUser.getPassword());
        userMap.put("id", securityUser.getId());
        userMap.put("authSet", securityUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userMap.put("token", token);
        out.write(Result.toJson(Ecode.SUCCESS, userMap));
        out.flush();
        out.close();
		//super.onAuthenticationSuccess(request, response, authentication);
	}

	/**
     * 将获取到的token转换为展示的JSON
     * @param token Oauth的token
     */
    private JSONObject token2Json(OAuth2AccessToken token) {
		JSONObject json = new JSONObject();

		// 核心信息
		json.put("access_token", token.getValue());
		json.put("token_type", token.getTokenType());
		//json.put("refresh_token", token.getRefreshToken().getValue());
		json.put("expires_in", token.getExpiresIn());
		json.put("scope", token.getScope());

		// 补充信息
		Map<String, Object> map = token.getAdditionalInformation();
		json.putAll(map);

		return json;
    }

}
