package kr.co.cyberline.cmm.config.spring;

import kr.co.cyberline.cmm.web.dao.CylMyBatisBase2DAO;
import kr.co.cyberline.cmm.web.dao.CylMyBatisBase3DAO;
import kr.co.cyberline.cmm.web.dao.CylMyBatisBaseDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cmm {

    @Bean
    public CylMyBatisBaseDAO baseDAO() {
        return new CylMyBatisBaseDAO();
    }

    @Bean
    public CylMyBatisBase2DAO baseDAO2() {
        return new CylMyBatisBase2DAO();
    }

    @Bean
    public CylMyBatisBase3DAO baseDAO3() {
        return new CylMyBatisBase3DAO();
    }
}
