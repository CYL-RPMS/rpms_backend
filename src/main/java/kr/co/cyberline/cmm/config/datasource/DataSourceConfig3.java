package kr.co.cyberline.cmm.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "kr.co.cyberline")
public class DataSourceConfig3 {

    @Value("${jdbc.type3}")
    private String dbType;

    @Bean(name = "datasource3")
    @ConfigurationProperties(prefix = "spring.datasource3")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactory3")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("datasource3") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.co.cyberline");

        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(myBatisConfig);

        String mapperLocation = "classpath:kr/co/cyberline/mapper/**/" + dbType + "/**/*.xml";
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(mapperLocation));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate3")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory3") SqlSessionFactory sqlSessionFactory)  {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }
}
