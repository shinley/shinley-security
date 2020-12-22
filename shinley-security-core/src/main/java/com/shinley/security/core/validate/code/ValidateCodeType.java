package com.shinley.security.core.validate.code;

import com.shinley.security.core.properties.SecurityConstants;

public enum ValidateCodeType {
    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    }
    ;

    /**
     * 校验时从请求中获取的参数名字
     */
    public abstract  String getParamNameOnValidate();

}
