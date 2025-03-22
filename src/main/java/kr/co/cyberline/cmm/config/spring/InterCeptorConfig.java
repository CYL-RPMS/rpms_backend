package kr.co.cyberline.cmm.config.spring;

import kr.co.cyberline.cmm.web.interceptor.CylExecuteTimeInterceptor;
import kr.co.cyberline.cmm.web.interceptor.CylLoggingInterceptor;
import kr.co.cyberline.cmm.web.interceptor.FileUploadInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterCeptorConfig implements WebMvcConfigurer {

    private final CylExecuteTimeInterceptor executeTimeInterceptor;
    private final CylLoggingInterceptor cylLoggingInterceptor;
    private final FileUploadInterceptor fileUploadInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(executeTimeInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(cylLoggingInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(fileUploadInterceptor)
                .addPathPatterns("/**");

    }
}
