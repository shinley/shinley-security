package com.shinley.security.core.validate.code;


import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {

    ValidateCode gengrate(ServletWebRequest request);
}
