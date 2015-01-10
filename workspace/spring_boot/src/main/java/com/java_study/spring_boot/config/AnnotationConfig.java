package com.java_study.spring_boot.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class AnnotationConfig {
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	        return new EmbeddedServletContainerCustomizer() {
	            @Override
	            public void customize(ConfigurableEmbeddedServletContainer container) {
	                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/jsp/404.jsp"));
	            }
	        };
	}
	
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//	    factory.setPort(9000);
//	    factory.setSessionTimeout(10, TimeUnit.MINUTES);
//	    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/WEB-INF/jsp/404.jsp"));
//	    return factory;
//	}
	
//	@Bean
//	public ViewResolver htmlViewResolver() {
//	 
//	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	 
//	    resolver.setPrefix("/WEB-INF/jsp/");
//	    resolver.setSuffix(".jsp");
//	 
//	  return resolver;
//	}

}
