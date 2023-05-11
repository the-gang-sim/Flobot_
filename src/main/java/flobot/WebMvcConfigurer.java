package flobot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
	@Autowired
	LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
			.addPathPatterns("/member/*","/market/*","/admin/*","/goodsIpgo/*","/register/*")
			.excludePathPatterns("/login/*","/search/*","/purchase/*","/inquire/*","/goods/*","/review/*","/corner/*");
	}
	
	
}
