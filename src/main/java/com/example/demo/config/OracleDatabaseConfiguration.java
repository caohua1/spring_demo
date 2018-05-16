package com.example.demo.config;


import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.shenmajr.insurance.mapper.oracle", sqlSessionTemplateRef = "sqlSessionTemplateForOracle")
public class OracleDatabaseConfiguration {

  @Bean(name = "dataSourceForOracle")
  @ConfigurationProperties(prefix = "spring.datasource.oracle")
  @Primary
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "sqlSessionFactoryForOracle")
  @Primary
  public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceForOracle") DataSource dataSource) throws Exception {

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
    sqlSessionFactoryBean.setTypeAliasesPackage("com.shenmajr.insurance.mapper.oracle");

    //分页插件
    PageHelper pageHelper = new PageHelper();
    Properties properties = new Properties();
    properties.setProperty("reasonable", "true");
    properties.setProperty("supportMethodsArguments", "true");
    properties.setProperty("returnPageInfo", "check");
    properties.setProperty("params", "count=countSql");
    pageHelper.setProperties(properties);

    //添加插件
    sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});

    sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name = "transactionManagerForOracle")
  @Primary
  public PlatformTransactionManager transactionManager(@Qualifier("dataSourceForOracle") DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = "sqlSessionTemplateForOracle")
  @Primary
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryForOracle") SqlSessionFactory sqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

}