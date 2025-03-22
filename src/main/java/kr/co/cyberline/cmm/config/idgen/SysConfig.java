package kr.co.cyberline.cmm.config.idgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

@Configuration
public class SysConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public EgovTableIdGnrServiceImpl userManageIdGnrService() {
        EgovTableIdGnrServiceImpl service = new EgovTableIdGnrServiceImpl();
        service.setDataSource(dataSource);
        service.setStrategy(userManageIdStrategy());
        service.setBlockSize(10);
        service.setTable("CYL_CMM_0900_TE");
        service.setTableName("CYL_CMM_0100_TN");
        return service;
    }

    @Bean
    public EgovIdGnrStrategyImpl userManageIdStrategy() {
        EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
        strategy.setPrefix("USR_");
        strategy.setCipers(16);
        strategy.setFillChar('0');
        return strategy;
    }

    @Bean
    public EgovTableIdGnrServiceImpl fncGroupIdGnrService() {
        EgovTableIdGnrServiceImpl service = new EgovTableIdGnrServiceImpl();
        service.setDataSource(dataSource);
        service.setStrategy(fncGroupIdStrategy());
        service.setBlockSize(10);
        service.setTable("CYL_CMM_0900_TE");
        service.setTableName("CYL_CMM_0306_TN");
        return service;
    }

    @Bean
    public EgovIdGnrStrategyImpl fncGroupIdStrategy() {
        EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
        strategy.setPrefix("FNC_");
        strategy.setCipers(16);
        strategy.setFillChar('0');
        return strategy;
    }

    @Bean
    public EgovTableIdGnrServiceImpl menuIdGnrService() {
        EgovTableIdGnrServiceImpl service = new EgovTableIdGnrServiceImpl();
        service.setDataSource(dataSource);
        service.setStrategy(menuIdStrategy());
        service.setBlockSize(10);
        service.setTable("CYL_CMM_0900_TE");
        service.setTableName("CYL_CMM_0300_TN");
        return service;
    }

    @Bean
    public EgovIdGnrStrategyImpl menuIdStrategy() {
        EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
        strategy.setPrefix("MNU_");
        strategy.setCipers(16);
        strategy.setFillChar('0');
        return strategy;
    }

    @Bean
    public EgovTableIdGnrServiceImpl programIdGnrService() {
        EgovTableIdGnrServiceImpl service = new EgovTableIdGnrServiceImpl();
        service.setDataSource(dataSource);
        service.setStrategy(programIdStrategy());
        service.setBlockSize(10);
        service.setTable("CYL_CMM_0900_TE");
        service.setTableName("CYL_CMM_0303_TN");
        return service;
    }

    @Bean
    public EgovIdGnrStrategyImpl programIdStrategy() {
        EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
        strategy.setPrefix("PGM_");
        strategy.setCipers(16);
        strategy.setFillChar('0');
        return strategy;
    }

    @Bean
    public EgovTableIdGnrServiceImpl bbsManageIdGnrService() {
        EgovTableIdGnrServiceImpl service = new EgovTableIdGnrServiceImpl();
        service.setDataSource(dataSource);
        service.setStrategy(bbsManageIdStrategy());
        service.setBlockSize(10);
        service.setTable("CYL_CMM_0900_TE");
        service.setTableName("CYL_CMM_0402_TN");
        return service;
    }

    @Bean
    public EgovIdGnrStrategyImpl bbsManageIdStrategy() {
        EgovIdGnrStrategyImpl strategy = new EgovIdGnrStrategyImpl();
        strategy.setPrefix("BBS_");
        strategy.setCipers(16);
        strategy.setFillChar('0');
        return strategy;
    }
}