package kr.co.cyberline.cmm.config.idgen;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

import javax.sql.DataSource;

@Configuration
public class IdgnExm {

    @Bean
    public EgovIdGnrStrategyImpl atchFileIdStrategy() {
        EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
        strategy.setPrefix("FIL_");
        strategy.setCipers(16);
        strategy.setFillChar('0');
        return strategy;
    }

    @Bean
    public EgovTableIdGnrServiceImpl atchFileIdGnrService(
            @Qualifier("dataSource") DataSource dataSource,
            EgovIdGnrStrategyImpl atchFileIdStrategy) {

        EgovTableIdGnrServiceImpl service = new EgovTableIdGnrServiceImpl();
        service.setDataSource(dataSource);
        service.setStrategy(atchFileIdStrategy);
        service.setBlockSize(10);
        service.setTable("CYL_CMM_0900_TE");
        service.setTableName("CYL_CMM_0400_TN");

        return service;
    }
}
