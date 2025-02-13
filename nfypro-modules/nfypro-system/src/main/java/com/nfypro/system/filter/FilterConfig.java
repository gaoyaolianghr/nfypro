package com.nfypro.system.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    /*@Bean
    public FilterRegistrationBean<XssFilter> xssFilter() {
        FilterRegistrationBean<XssFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new XssFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1); // 最先执行（处理 XSS）
        return registrationBean;
    }*/

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(2); // 认证在 XSS 之后
        return registrationBean;
    }

    /*@Bean
    public FilterRegistrationBean<BlackListUrlFilter> blackListUrlFilter() {
        FilterRegistrationBean<BlackListUrlFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BlackListUrlFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(3);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ValidateCodeFilter> validateCodeFilter() {
        FilterRegistrationBean<ValidateCodeFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ValidateCodeFilter());
        registrationBean.addUrlPatterns("/api/login");
        registrationBean.setOrder(4);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CacheRequestFilter> cacheRequestFilter() {
        FilterRegistrationBean<CacheRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CacheRequestFilter());
        registrationBean.addUrlPatterns("/api/cache/*");
        registrationBean.setOrder(5); // 最后执行（缓存响应）
        return registrationBean;
    }*/
}
