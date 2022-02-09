package com.zkzn.les.basicInfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


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
	            .title("基础信息服务")
	            .description("系统中应用的基础数据维护及展示")
	            .contact(contact)   // 联系方式
	            .version("1.1.0")  // 版本
	            .build();
	}
}
