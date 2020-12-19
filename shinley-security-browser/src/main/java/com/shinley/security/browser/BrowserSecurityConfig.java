package com.shinley.security.browser;

import com.shinley.security.browser.authentication.ShinleyAuthenticationFailHandler;
import com.shinley.security.browser.authentication.ShinleyAuthenticationSuccessHandler;
import com.shinley.security.core.properties.SecurityPerties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
//        http.httpBasic()
        http.formLogin()
                .loginPage("/shinley-signIn.html")
                .loginProcessingUrl("/authentication/form")
                .successHandler(shinleyAuthenticationSuccessHandler)
                .failureHandler(shinleyAuthenticationFailHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require", securityPerties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
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
