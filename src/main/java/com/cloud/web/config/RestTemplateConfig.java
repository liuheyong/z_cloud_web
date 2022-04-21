package com.cloud.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author: heyongliu
 * @date: 2022/4/10
 * @description:
 */
@Configuration
public class RestTemplateConfig {

    public RestTemplateConfig() {
    }

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        return new RestTemplate(this.getSimpleClientHttpRequestFactory());
    }

    public ClientHttpRequestFactory getSimpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(600000);
        factory.setConnectTimeout(600000);
        return new BufferingClientHttpRequestFactory(factory);
    }
}
