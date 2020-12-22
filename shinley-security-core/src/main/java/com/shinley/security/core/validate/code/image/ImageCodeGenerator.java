package com.shinley.security.core.validate.code.image;

import com.shinley.security.core.properties.SecurityPerties;
import com.shinley.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityPerties securityPerties;

    @Override
    public ImageCode gengrate(ServletWebRequest servletWebRequest) {
        HttpServletRequest request = servletWebRequest.getRequest();
        int width = ServletRequestUtils.getIntParameter(request,  "width", securityPerties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request,  "height", securityPerties.getCode().getImage().getHeight());
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random= new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0,0, width, height);
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 255; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x+x1, y+y1);
        }
        String sRand = "";
        for (int i = 0; i < securityPerties.getCode().getImage().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();
        return new ImageCode(image, sRand, securityPerties.getCode().getImage().getExpireIn());
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if  (bc > 255) {
            bc =255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public SecurityPerties getSecurityPerties() {
        return securityPerties;
    }

    public void setSecurityPerties(SecurityPerties securityPerties) {
        this.securityPerties = securityPerties;
    }
}
