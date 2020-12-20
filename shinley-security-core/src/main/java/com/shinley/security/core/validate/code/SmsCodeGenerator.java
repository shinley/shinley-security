package com.shinley.security.core.validate.code;

import com.shinley.security.core.properties.SecurityPerties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCodeGenerator")
public class SmsCodeGenerator implements  ValidateCodeGenerator {

    @Autowired
    private SecurityPerties securityPerties;

    @Override
    public ValidateCode gengrate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityPerties.getCode().getSms().getLength());
        return new ValidateCode(code, securityPerties.getCode().getSms().getExpireIn());
    }

    public SecurityPerties getSecurityPerties() {
        return securityPerties;
    }

    public void setSecurityPerties(SecurityPerties securityPerties) {
        this.securityPerties = securityPerties;
    }
}
