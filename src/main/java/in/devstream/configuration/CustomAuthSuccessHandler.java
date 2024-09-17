package in.devstream.configuration;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // reference: https://www.youtube.com/watch?v=ycemlr5uXD0
        response.setStatus(HttpServletResponse.SC_OK);
        for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
            if ("ADMIN".equals(grantedAuth.getAuthority())) {
                response.sendRedirect("/dashboard");
            }
        }
    }

}
