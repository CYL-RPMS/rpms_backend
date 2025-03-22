package kr.co.cyberline.cmm.config.idgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

@Configuration
public class UatConfig {

    @Autowired
    private DataSource dataSource; // Spring Boot에서 자동 구성된 DataSource

    // 게시판 ID 생성기
    @Bean(name = "bbsIdGnrService")
    public EgovTableIdGnrServiceImpl bbsIdGnrService() {
        EgovTableIdGnrServiceImpl service = new EgovTableIdGnrServiceImpl();
        service.setDataSource(dataSource);
        service.setStrategy(bbsNttIdStrategy());
        service.setBlockSize(10);
        service.setTable("CYL_CMM_0900_TE");
        service.setTableName("CYL_CMM_0403_TN");
        return service;
    }

    @Bean(name = "bbsNttIdStrategy")
    public EgovIdGnrStrategyImpl bbsNttIdStrategy() {
        EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
        strategy.setCipers(20);
        return strategy;
    }
}