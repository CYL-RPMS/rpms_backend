package kr.co.cyberline.cmm.config.spring;

import kr.co.cyberline.cmm.web.view.CylFileDownView;
import kr.co.cyberline.cmm.web.view.CylMappingJacksonJsonViewExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class ViewConfig {

    @Bean
    public CylFileDownView fileDownView() {
        return new CylFileDownView();
    }

    @Bean
    public CylMappingJacksonJsonViewExtension jsonViewExtension() {
        CylMappingJacksonJsonViewExtension view = new CylMappingJacksonJsonViewExtension();
        view.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return view;
    }
}
