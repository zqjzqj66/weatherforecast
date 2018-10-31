package cn.edu.zzuli.weatherforecast.config;

import cn.edu.zzuli.weatherforecast.Interceptor.VerifyLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 *
 * @author 周启江
 * @ClassName: WeatherForecastConfig
 * @date 2018/10/30 16:26
 */
@Configuration
public class WeatherForecastConfig implements WebMvcConfigurer {


    /**
     * Description: 对一些静态资源的再次配置, 似乎是由于 spring boot 2.0 以后导致的一些关于资源访问的问题
     *
     * @param registry
     * @Title: addResourceHandlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源的配置
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //swagger api 的配置
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        //web jars 配置  这个web的jar应该是没用的
        //registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        //本地文件的访问的配置
        //registry.addResourceHandler("/files/**").addResourceLocations("classpath:/files/");

    }

    /**
     * Description: 页面转向
     *
     * @param registry
     * @Title: addViewControllers
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("index.page").setViewName("/index.html");
    }

    /**
     * Description: 添加拦截器
     *
     * @param registry
     * @Title: addInterceptors
     */
   /* @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 静态资源 ： springboot已经做好静态资源映射
        // 不用担心拦到静态资源,但在 2.x 时因为spring 5.X 的 拦截器的原因 导致spring 的一些配置失效
        // 所以加上 对静态资源的回避， 以及对 webjars 的回避
        // 登陆拦截器
        registry.addInterceptor(
                new VerifyLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/webjars/**",
                        "/", "/index.html", "/user/login");
    }*/

}
