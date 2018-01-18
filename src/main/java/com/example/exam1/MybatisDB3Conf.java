package com.example.exam1;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages="com.example.dao.mapper3",sqlSessionFactoryRef="sqlSessionFactory3")
public class MybatisDB3Conf {
	   @Qualifier("ds3")
	    @Autowired
	    DataSource dataSource;

	    @Bean(name = "sqlSessionFactory3")
	    public SqlSessionFactory sqlSessionFactory3() throws Exception {
	        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	        factoryBean.setDataSource(dataSource);
	        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/mapper3/*.xml"));
	        factoryBean.setTypeAliasesPackage("com.example.entity");
	        return factoryBean.getObject();

	    }

	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate3() throws Exception {
	        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory3()); // 使用上面配置的Factory
	        return template;
	    }
}
