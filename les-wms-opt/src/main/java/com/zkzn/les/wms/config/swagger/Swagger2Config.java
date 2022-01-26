package com.zkzn.les.wms.config.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Swagger2Config
 * @Description TODO
 * @Author zhanglei
 * Date 2021/6/9 13:36
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {
    @Bean
    public Docket productApi() {
        //schema
        List<GrantType> grantTypes=new ArrayList<>();
        //简单模式implicit
        ImplicitGrant implicitGrant=new ImplicitGrant(new LoginEndpoint("http://localhost:8082/wms/oauth/authorize"),"access_token");
        grantTypes.add(implicitGrant);
        OAuth oAuth=new OAuthBuilder().name("oauth2")
                .grantTypes(grantTypes).build();
        //context
        //scope方位
        List<AuthorizationScope> scopes=new ArrayList<>();
        scopes.add(new AuthorizationScope("read","read  resources"));
        scopes.add(new AuthorizationScope("write","write resources"));
        scopes.add(new AuthorizationScope("reads","read all resources"));
        scopes.add(new AuthorizationScope("writes","write all resources"));

        SecurityReference securityReference=new SecurityReference("oauth2",scopes.toArray(new AuthorizationScope[]{}));
        SecurityContext securityContext=new SecurityContext(Lists.newArrayList(securityReference),PathSelectors.ant("/api/**"));
        //schemas
        List<SecurityScheme> securitySchemes=Lists.newArrayList(oAuth);
        //securyContext
        List<SecurityContext> securityContexts=Lists.newArrayList(securityContext);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zkzn.les.wms.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts)
                .securitySchemes(securitySchemes)
                .apiInfo(apiInfo());
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("les-wms pai文档").description("")
                .termsOfServiceUrl("http://127.0.0.1")
                .contact(new Contact("zhangLei", "", "mrzhangzx@aliyun.com"))
                .license("Open Source")
                .licenseUrl("")
                .version("1.0.0")
                .build();

    }
}