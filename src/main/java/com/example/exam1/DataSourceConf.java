package com.example.exam1;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
/**
 * 另一个方法:DataSource
 * @author Administrator
 *
 */
@Configuration
public class DataSourceConf {
    @Autowired
    Environment env;

    @Bean(name = "ds1")
    public DataSource dataSource1() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(env.getProperty("spring.write.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.write.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.write.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.write.datasource.driverClassName"));
        return dataSource;
    }

    @Bean(name = "ds2")
    public DataSource dataSource2() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(env.getProperty("spring.read.one.url"));
        dataSource.setUsername(env.getProperty("spring.read.one.username"));
        dataSource.setPassword(env.getProperty("spring.read.one.password"));
        dataSource.setDriverClassName(env.getProperty("spring.read.one.driverClassName"));
        return dataSource;
    }
    @Bean(name = "ds3")
    public DataSource dataSource3() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(env.getProperty("spring.read.two.url"));
        dataSource.setUsername(env.getProperty("spring.read.two.username"));
        dataSource.setPassword(env.getProperty("spring.read.two.password"));
        dataSource.setDriverClassName(env.getProperty("spring.read.two.driverClassName"));
        return dataSource;
    }
    /*@Bean(name = "ds4")
    public DataSource dataSource4() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(env.getProperty("spring.read.three.url"));
        dataSource.setUsername(env.getProperty("spring.read.three.username"));
        dataSource.setPassword(env.getProperty("spring.read.three.password"));
        dataSource.setDriverClassName(env.getProperty("spring.read.three.driverClassName"));
        return dataSource;
    }
	    @Bean(name = "ds5")
	    public DataSource dataSource5() {
	        DruidDataSource dataSource = new DruidDataSource();
	
	        dataSource.setUrl(env.getProperty("spring.read.four.url"));
	        dataSource.setUsername(env.getProperty("spring.read.four.username"));
	        dataSource.setPassword(env.getProperty("spring.read.four.password"));
	        dataSource.setDriverClassName(env.getProperty("spring.read.four.driverClassName"));
	        return dataSource;
	    }*/

}
