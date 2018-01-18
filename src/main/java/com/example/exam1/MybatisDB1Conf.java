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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages="com.example.dao.mapper1",sqlSessionFactoryRef="sqlSessionFactory1")
public class MybatisDB1Conf {
	   @Qualifier("ds1")
	    @Autowired
	    DataSource dataSource;

	    @Bean(name = "sqlSessionFactory1")
	    @Primary
	    public SqlSessionFactory sqlSessionFactory1() throws Exception {
	        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	        factoryBean.setDataSource(dataSource);
	        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/mapper1/*.xml"));
	        factoryBean.setTypeAliasesPackage("com.example.entity");
	        return factoryBean.getObject();

	    }

	    @Bean
	    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
	        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1()); // 使用上面配置的Factory
	        return template;
	    }
}
