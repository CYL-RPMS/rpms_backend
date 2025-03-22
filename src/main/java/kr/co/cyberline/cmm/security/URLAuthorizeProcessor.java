package kr.co.cyberline.cmm.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.util.Assert;

public class URLAuthorizeProcessor implements BeanPostProcessor, InitializingBean {
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    public Object postProcessAfterInitialization(Object bean, String name) {
        if (bean instanceof FilterSecurityInterceptor) {
            ((FilterSecurityInterceptor) bean).setSecurityMetadataSource(securityMetadataSource);
        }
        return bean;
    }

    public Object postProcessBeforeInitialization(Object bean, String name) {
        return bean;
    }

    public void setProgramURLSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    public void afterPropertiesSet()  {
        Assert.notNull(securityMetadataSource, "securityMetadataSource cannot be null");
    }
}