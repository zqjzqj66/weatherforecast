package cn.edu.zzuli.weatherforecast.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * Description: Druid数据源配置类
 *
 * @ClassName: DruidConfig
 * 
 * @author 崔定豪
 * 
 * @date 2018年7月28日上午11:24:57
 * 
 * @version 1.0
 *
 */
@Configuration
public class DruidConfig {

	/**
	 * Description: 根据配置文件 创建 druid 数据源
	 *
	 * @Title: druid
	 * 
	 * @return {@link DataSource}
	 */
	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource druid() {
		return new DruidDataSource();
	}

	/**
	 * Description:配置一个管理后台的Servlet
	 *
	 * @Title: statViewServlet
	 * 
	 * @return ServletRegistrationBean<StatViewServlet>
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> statViewServlet() {
		ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),
				"/druid/*");
		Map<String, String> initParams = new HashMap<>();

		initParams.put("loginUsername", "admin");
		initParams.put("loginPassword", "123456");
		initParams.put("allow", "127.0.0.1");// 默认就是允许所有访问
		initParams.put("deny", "192.168.15.21");

		bean.setInitParameters(initParams);
		return bean;
	}

	/**
	 * Description: 配置一个web监控的filter
	 *
	 * @Title: webStatFilter
	 * 
	 * @return FilterRegistrationBean<WebStatFilter>
	 */
	@Bean
	public FilterRegistrationBean<WebStatFilter> webStatFilter() {
		FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new WebStatFilter());

		Map<String, String> initParams = new HashMap<>();
		initParams.put("exclusions", "*.js,*.css,/druid/*");

		bean.setInitParameters(initParams);

		bean.setUrlPatterns(Arrays.asList("/*"));

		return bean;
	}

}