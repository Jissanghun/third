package com.example.third;

import com.example.third.filter.LogFilter;
import com.example.third.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean LogFilter(){ // 필터들간의 순서 등록, 적용 패턴
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*"); // 모든 요청이 들어오는 URI에 대해서 다 적용
        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean LoginCheckFilter(){ // 필터들간의 순서 등록, 적용 패턴
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
        filterFilterRegistrationBean.setOrder(2);
        filterFilterRegistrationBean.addUrlPatterns("/*"); // 모든 요청이 들어오는 URI에 대해서 다 적용
        return filterFilterRegistrationBean;
    }
}
