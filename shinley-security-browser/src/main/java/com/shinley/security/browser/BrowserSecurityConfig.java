package com.shinley.security.browser;

import com.shinley.security.browser.authentication.ShinleyAuthenticationFailHandler;
import com.shinley.security.browser.authentication.ShinleyAuthenticationSuccessHandler;
import com.shinley.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.shinley.security.core.authentication.mobile.SmsCodeFilter;
import com.shinley.security.core.properties.SecurityPerties;
import com.shinley.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);

        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 处理验证码过滤器
         */
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(shinleyAuthenticationFailHandler);
        validateCodeFilter.setSecurityPerties(securityPerties);
        validateCodeFilter.afterPropertiesSet();

        /**
         * 处理短信验证码过滤器
         */
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(shinleyAuthenticationFailHandler);
        smsCodeFilter.setSecurityPerties(securityPerties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin()
                .loginPage(securityPerties.getBrowser().getLoginPage())
                .loginProcessingUrl("/authentication/form")
                .successHandler(shinleyAuthenticationSuccessHandler)
                .failureHandler(shinleyAuthenticationFailHandler)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityPerties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
            .antMatchers("/authentication/require", securityPerties.getBrowser().getLoginPage(), "/code/*").permitAll()
            .anyRequest()
            .authenticated()
            .and().csrf().disable()
        .apply(smsCodeAuthenticationSecurityConfig);
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
