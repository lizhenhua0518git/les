package com.zkzn.les.oms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**.
 * 
 * @author wangzhou
 * @date 2020年8月5日
 * @Description swagger 配置信息类
 */
@Configuration
public class SwaggerConfig {

	 @Bean
	 public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
	 
	private ApiInfo apiInfo() {
	        
	    Contact contact = new Contact("LES项目团队", "1", "1");
	    return new ApiInfoBuilder()
	            .title("订单管理服务")
	            .description("系统中应用的订单管理维护及展示")
	            .contact(contact)   // 联系方式
	            .version("1.1.0")  // 版本
	            .build();
	}
}
