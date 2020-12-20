package com.shinley.security.core.validate.code;

import com.shinley.security.core.properties.SecurityPerties;
import com.shinley.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.shinley.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityPerties securityPerties;


    @Bean
    @ConditionalOnMissingBean(ValidateCodeGenerator.class)
//    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityPerties(securityPerties);
        return codeGenerator;
    }

    @Bean
//    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeGenerator() {
        return new DefaultSmsCodeSender();
    }
}
