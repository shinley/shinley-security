package com.shinley.security.core.validate.code;


import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {

    ValidateCode gengrate(HttpServletRequest request);
}
