package com.brokermanagement.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate buildRestTemplate() {
        HttpClient client = HttpClientBuilder
                .create()
                .useSystemProperties()
                .build();
        return  new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
    }
}
