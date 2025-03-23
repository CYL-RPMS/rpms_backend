package kr.co.cyberline.cmm.config.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.co.cyberline.cmm.web.servlet.CylSysConfInitServlet;

@Configuration
public class SystemInitConfig {

    @Bean
    public ServletRegistrationBean<CylSysConfInitServlet> systemInitConfigServlet() {
        ServletRegistrationBean<CylSysConfInitServlet> registrationBean =
                new ServletRegistrationBean<>(new CylSysConfInitServlet(), "/systemInitConfig.com");

        // 초기화 파라미터 설정
        registrationBean.addInitParameter("hierCodeServiceImplId", "cylHierCodeService");
        registrationBean.addInitParameter("hierDetailCodeServiceImplId", "cylHierDetailCodeService");

        // 로드 순서 설정 (load-on-startup)
        registrationBean.setLoadOnStartup(2);

        return registrationBean;
    }
}