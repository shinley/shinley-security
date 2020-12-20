package com.shinley.security.browser;

import com.shinley.security.browser.authentication.ShinleyAuthenticationFailHandler;
import com.shinley.security.browser.authentication.ShinleyAuthenticationSuccessHandler;
import com.shinley.security.core.properties.SecurityPerties;
import com.shinley.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityPerties securityPerties;
    /**
     * 登录成功处理器
     */
    @Autowired
    private ShinleyAuthenticationSuccessHandler shinleyAuthenticationSuccessHandler;

    /**
     * 登录失败处理器
     */
    @Autowired
    private ShinleyAuthenticationFailHandler shinleyAuthenticationFailHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 处理验证码过滤器
         */
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(shinleyAuthenticationFailHandler);

//      http.httpBasic()

        http.formLogin()
            .loginPage(securityPerties.getBrowser().getLoginPage())
            .loginProcessingUrl("/authentication/form")
            .successHandler(shinleyAuthenticationSuccessHandler)
            .failureHandler(shinleyAuthenticationFailHandler)
            .and()
            .authorizeRequests()
            .antMatchers("/authentication/require", securityPerties.getBrowser().getLoginPage(), "/code/image").permitAll()
            .anyRequest()
            .authenticated()
            .and().csrf().disable();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
}

    /**
     * 密码加密 和 比较密码
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
