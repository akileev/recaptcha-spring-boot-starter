package com.github.mkopylec.recaptcha;

import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(RecaptchaProperties.class)
public class RecaptchaConfiguration {

    @Autowired
    private RecaptchaProperties recaptcha;

    @Bean
    public RecaptchaValidator userResponseValidator(RestTemplate restTemplate) {
        return new RecaptchaValidator(restTemplate, recaptcha);
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}