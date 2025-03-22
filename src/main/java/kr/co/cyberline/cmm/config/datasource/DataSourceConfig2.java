package kr.co.cyberline.cmm.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

import static kr.co.cyberline.cmm.config.datasource.DataSourceConfig.getSqlSessionFactory;

@Configuration
@PropertySource("classpath:jdbc-${spring.profiles.active}.properties")
public class DataSourceConfig2 {

    @Value("${jdbc.type2}")
    private String dbType;

    @Value("${spring.datasource2.jdbc-url}")
    private String dbUrl;

    @Value("${spring.datasource2.username}")
    private String dbUsername;

    @Value("${spring.datasource2.password}")
    private String dbPassword;

    @Value("${spring.datasource2.driver-class-name}")
    private String dbDriverClassName;

    @Bean(name = "datasource2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(dbUsername)
                .password(dbPassword)
                .driverClassName(dbDriverClassName)
                .build();
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("datasource2") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        return getSqlSessionFactory(dataSource, applicationContext, dbType);
    }

    @Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory)  {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
