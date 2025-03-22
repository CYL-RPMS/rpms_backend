package kr.co.cyberline.cmm.config.spring;

import kr.co.cyberline.cmm.security.ProgramURLSource;
import kr.co.cyberline.cmm.security.URLAuthorizeProcessor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
public class SecurityConfig {

    @Autowired
    private SqlSession sqlSession;

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Bean
    public URLAuthorizeProcessor urlAuthorizeProcessor() {
        URLAuthorizeProcessor processor = new URLAuthorizeProcessor();
        ProgramURLSource urlSource = new ProgramURLSource();
        urlSource.setSqlSession(sqlSession);
        processor.setProgramURLSource(urlSource);
        return processor;
    }
}
