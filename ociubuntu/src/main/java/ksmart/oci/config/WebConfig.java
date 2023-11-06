package ksmart.oci.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import ksmart.oci.interceptor.CommonInterceptor;
import ksmart.oci.interceptor.LoginInterceptor;



@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${files.path}")
	private String filePath;

	private final CommonInterceptor commonInterceptor; 
	private final LoginInterceptor loginInterceptor;
	
	public WebConfig(LoginInterceptor loginInterceptor, CommonInterceptor commonInterceptor) {
		this.commonInterceptor = commonInterceptor;
		this.loginInterceptor = loginInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/favicon.ico");
				
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/css/**")
				.excludePathPatterns("/member/addMember")
				.excludePathPatterns("/member/login")
				.excludePathPatterns("/member/idCheck")
				.excludePathPatterns("/member/logout");
		
		WebMvcConfigurer.super.addInterceptors(registry);
		
	}
	
	/**
	 * 주소요청에 따른 외부파일 경로 설정
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String rootPath = getOSFilePath();
		registry.addResourceHandler("/resources/**")
				.addResourceLocations(rootPath + filePath + "resources/")
				.setCachePeriod(3600)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	public String getOSFilePath() {
		String rootPath = "file:///";
		String os = System.getProperty("os.name").toLowerCase();
		
		if(os.contains("win")) {
			rootPath = "file:///c:";
		}else if(os.contains("linux")) {
			rootPath = "file:///";			
		}else if(os.contains("mac")){
			rootPath = "file:///Users/Shared";
		}
		return rootPath;
	}
}







