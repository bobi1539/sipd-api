package com.suzume.sipd.config;

import com.suzume.sipd.constant.Constant;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = Constant.AUTHORIZATION,
        type = SecuritySchemeType.HTTP,
        bearerFormat = Constant.JWT,
        scheme = Constant.BEARER
)
public class SwaggerConfig {
}
