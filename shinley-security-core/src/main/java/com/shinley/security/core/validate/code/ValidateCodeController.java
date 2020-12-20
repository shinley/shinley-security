package com.shinley.security.core.validate.code;

import com.shinley.security.core.sessionstrategy.HttpSessionSessionStrategy;
import com.shinley.security.core.sessionstrategy.SessionStrategy;
import com.shinley.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    /**
     * 原来是spring-social-web中的包， 现在spring-web-social包不推荐使用了
     * 但是原来的教程使用了Spring-seb-social中的类, 所以把原包中的类抄了过来
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.gengrate(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG",response.getOutputStream());

    }

    @GetMapping("/code/sms")
    public void createSms(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        ValidateCode smsCode = imageCodeGenerator.gengrate(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
        /**
         * 连接短信服务商， 发送短信
         */
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCodeSender.send(mobile, smsCode.getCode());
    }

}
