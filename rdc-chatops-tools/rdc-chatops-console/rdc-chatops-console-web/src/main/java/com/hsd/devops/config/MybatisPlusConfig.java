package com.hsd.devops.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.hsd.devops.common.constant.DSEnum;
import com.hsd.devops.config.properties.DruidProperties;
import com.hsd.devops.core.datascope.DataScopeInterceptor;
import com.hsd.devops.core.mutidatasource.DynamicDataSource;
import com.hsd.devops.core.mutidatasource.config.MutiDataSourceProperties;


@Configuration
@EnableTransactionManagement(order = 2)//由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
@MapperScan(basePackages = {"com.hsd.devops.modular.*.dao", "com.hsd.devops.common.persistence.dao"})
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;


    
    private DruidDataSource bizDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        mutiDataSourceProperties.config(dataSource);
        return dataSource;
    }

    
    private DruidDataSource dataSourceDevops(){
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    
    @Bean
    @ConditionalOnProperty(prefix = "devops", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return dataSourceDevops();
    }

    
    @Bean
    @ConditionalOnProperty(prefix = "devops", name = "muti-datasource-open", havingValue = "true")
    public DynamicDataSource mutiDataSource() {

        DruidDataSource dataSourceDevops = dataSourceDevops();
        DruidDataSource bizDataSource = bizDataSource();

        try {
            dataSourceDevops.init();
            bizDataSource.init();
        }catch (SQLException sql){
            sql.printStackTrace();
        }

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put(DSEnum.DATA_SOURCE_DEVOPS, dataSourceDevops);
        hashMap.put(DSEnum.DATA_SOURCE_BIZ, bizDataSource);

        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceDevops);
        return dynamicDataSource;
    }

    
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }
}
