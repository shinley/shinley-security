package com.shinley.code;


import com.shinley.security.core.validate.code.ImageCode;
import com.shinley.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode gengrate(HttpServletRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
