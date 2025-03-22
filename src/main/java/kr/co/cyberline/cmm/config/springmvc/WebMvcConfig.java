package kr.co.cyberline.cmm.config.springmvc;

import kr.co.cyberline.cmm.web.view.CylFileDownView;
import kr.co.cyberline.cmm.web.view.CylMappingJacksonJsonViewExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebMvcConfig {

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

    @Bean
    public MappingJackson2JsonView jsonView() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        return view;
    }
}
