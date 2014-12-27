package com.school.bank_java.config;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.school.bank_java.aop.AnnotationServiceAop;

@Configuration
@ComponentScan(basePackages = "com.school.bank_java")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig {// implements TransactionManagementConfigurer {

    @Bean
    public SimpleDriverDataSource dataSource() {
    	SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
    	simpleDriverDataSource.setDriverClass( com.mysql.jdbc.Driver.class );
    	simpleDriverDataSource.setUrl("jdbc:mysql://localhost/springbook");
    	simpleDriverDataSource.setUsername("student");
    	simpleDriverDataSource.setPassword("1234");
    	return simpleDriverDataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
    	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    	sqlSessionFactoryBean.setDataSource(dataSource());
    	sqlSessionFactoryBean.setMapperLocations(
    			new PathMatchingResourcePatternResolver().getResources("classpath*:com/school/bank_java/resource/mapper/**/*.xml"));
    	return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public SqlSession sqlSession() throws Exception{
    	return new SqlSessionTemplate(sqlSessionFactory());
    }
    
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		 return txManager();
//	}
    
    
    @Bean
    public AnnotationServiceAop annotationServiceAop(){
    	return new AnnotationServiceAop();
    }

}