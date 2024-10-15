package com.example.rqchallenge.config;

import com.example.rqchallenge.interceptor.RestLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ChallengeConfig {

    @Bean
    public RestTemplate restTemplateBean(){
        RestTemplate restTemplate = new RestTemplate();
        new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RestLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;

    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter(){
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }

}
