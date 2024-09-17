package in.devstream.configuration;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.stereotype.Component;

@Component
public class CustomWebSecurityCustomizer implements WebSecurityCustomizer {

    @Override
    public void customize(WebSecurity webSecurity) {
        // Spring Security should completely ignore URLs starting with /resources/
        webSecurity
                .ignoring()
                .requestMatchers(
                        "/js/**",
                        "/css/**",
                        "/static/**",
                        "/images/**",
                        "/resources/**"
                );
    }

}
