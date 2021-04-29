package com.dve.petclinic.globalConfig;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfiguration {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
        bean.setBasename("messages");
        bean.setUseCodeAsDefaultMessage(false);
        bean.setDefaultEncoding("UTF-8");
        return bean;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
