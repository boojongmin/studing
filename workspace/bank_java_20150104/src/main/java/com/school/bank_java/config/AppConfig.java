package com.school.bank_java.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.school.bank_java.aop.AnnotationServiceAop;

@Configuration
@ComponentScan(basePackages = "com.school.bank_java")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig {
	
	@Bean
	public SimpleDriverDataSource dataSource(){
		SimpleDriverDataSource dataSource
			=new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://192.168.0.25/school");
		dataSource.setUsername("student");
		dataSource.setPassword("1234");
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlSessionFactory
			= new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver()
					.getResources("classpath:com/school/bank_java/resource/mapper/*.xml")
				);
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSession sqlSesion() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean 
	public AnnotationServiceAop annotionServiceAop(){
		return new AnnotationServiceAop();
	}
	
	@Bean
	public DataSourceTransactionManager txManager(){
		return  new DataSourceTransactionManager(dataSource()); 
	}
	
	
}
