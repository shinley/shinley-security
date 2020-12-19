package com.shinley.security.core;

import com.shinley.security.core.properties.SecurityPerties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityPerties.class)
public class SecurityConfig {
}
