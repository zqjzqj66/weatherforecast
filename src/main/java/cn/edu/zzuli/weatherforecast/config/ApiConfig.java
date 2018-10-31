package cn.edu.zzuli.weatherforecast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: ApiConfig
 * @date 2018/10/30 15:45
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class ApiConfig {

    /**
     * Description: 管理端api
     *
     * @Title: adminDocket
     *
     * @return {@link Docket}
     */
    @Bean
    public Docket adminDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("管理端")
                /* 设置api信息 */
                .apiInfo(apiInfo("zhou","/weatherforecast","2766285474@qq.com"))
                // 设置以管理员分组
                .select().apis(RequestHandlerSelectors.basePackage("cn.edu.zzuli.weatherforecast.controller.admin")).build();
        return docket;
    }

    @Bean
    public Docket studentDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.groupName("用户端")
                // 设置api信息
                .apiInfo(apiInfo("zhou", "/weatherforecast/", "2766285474@qq.com"))
                // 设置以用户分组
                .select().apis(RequestHandlerSelectors.basePackage("cn.edu.zzuli.weather.controller.user")).build();
        return docket;
    }

    private ApiInfo apiInfo(String userName, String url, String email) {
        // 作者信息
        Contact contact = new Contact(userName, url, email);
        return new ApiInfoBuilder().title("API接口").description("API接口").contact(contact).version("1.1.0").build();
    }

}
