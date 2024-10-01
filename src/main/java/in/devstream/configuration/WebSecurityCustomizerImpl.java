package in.devstream.configuration;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.stereotype.Component;

@Component
public class WebSecurityCustomizerImpl implements WebSecurityCustomizer {

    @Override
    public void customize(WebSecurity webSecurity) {
        // Spring Security should completely ignore URLs starting with /resources/
        // reference: https://stackoverflow.com/questions/56388865/spring-security-configuration-httpsecurity-vs-websecurity
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
