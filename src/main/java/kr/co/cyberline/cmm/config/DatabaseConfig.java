package kr.co.cyberline.cmm.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.io.Resource;

@Configuration
@MapperScan(basePackages = "kr.co.cyberline")
public class DatabaseConfig {

    @Value("${jdbc.type}")
    private String dbType;

    @Value("${jdbc.type2}")
    private String dbType2;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource routingDataSource() {
        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(dbType, dataSource1());
        targetDataSources.put(dbType2, dataSource2());

        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(dataSource1());

        return routingDataSource;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setTypeAliasesPackage("kr.co.cyberline");

        // 동적으로 매퍼 파일 설정
        setMapperLocations(sessionFactoryBean);

        return sessionFactoryBean.getObject();
    }

    private void setMapperLocations(SqlSessionFactoryBean sessionFactoryBean) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        // 현재 선택된 데이터베이스 타입에 따라 매퍼 파일 위치 설정
        String currentDbType = DatabaseContextHolder.getDatabaseType();
        if (currentDbType == null) {
            currentDbType = dbType; // 기본값 설정
            DatabaseContextHolder.setDatabaseType(currentDbType);
        }

        String mapperLocation = "classpath:kr/co/cyberline/mapper/**/" + currentDbType + "/*.xml";
        Resource[] resources = resolver.getResources(mapperLocation);

        sessionFactoryBean.setMapperLocations(resources);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    // DatabaseContextHolder에게 기본 dbType 주입
    @Bean
    public DatabaseContextInitializer databaseContextInitializer() {
        return new DatabaseContextInitializer(dbType);
    }
}

class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDatabaseType();
    }
}

