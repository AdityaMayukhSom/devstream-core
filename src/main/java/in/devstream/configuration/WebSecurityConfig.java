package in.devstream.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private final AuthenticationProvider authProvider;

    @Autowired
    private final AuthenticationSuccessHandler authSuccessHandler;

    @Autowired
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // reference https://github.com/spring-projects/spring-security/issues/10938#issuecomment-1062359527
        // reference: https://stackoverflow.com/questions/56388865/spring-security-configuration-httpsecurity-vs-websecurity
        httpSecurity
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(customize -> {
                    customize
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/health").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/signup").permitAll()
                            .requestMatchers(
                                    "/js/**",
                                    "/css/**",
                                    "/static/**",
                                    "/images/**",
                                    "/resources/**"
                            ).permitAll()
                            .requestMatchers("/dashboard/**").hasAuthority("ADMIN")
                            .anyRequest().authenticated();
                })
                .sessionManagement(customize -> {
                    customize.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(customize -> {
                    customize
                            .logoutSuccessUrl("/")
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                })
//                .formLogin(customize -> {
//                    customize
//                            .usernameParameter("email")
//                            .passwordParameter("password")
//                            .loginPage("/login")
//                            .failureUrl("/login?error=true")
//                            .successHandler(authSuccessHandler);
//                })
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

}
