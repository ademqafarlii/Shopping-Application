package org.adem.paymentservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PaymentConfig {

    @LoadBalanced
    @Bean
    public WebClient.Builder webclientBuilder(){
        return WebClient.builder();
    }
}
