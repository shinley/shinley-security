package com.shinley.security.core.validate.code;

import com.shinley.security.core.properties.SecurityPerties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SmsCodeGenerator implements  ValidateCodeGenerator {

    private SecurityPerties securityPerties;

    @Override
    public ValidateCode gengrate(HttpServletRequest request) {
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
