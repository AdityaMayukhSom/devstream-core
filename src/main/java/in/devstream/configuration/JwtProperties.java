package in.devstream.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

// https://github.com/spring-projects/spring-boot/pull/39452
// https://stackoverflow.com/questions/52960921/spring-boot-configurationproperties-change-property-key
// https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/html/boot-features-external-config.html#boot-features-external-config-yaml-shortcomings
@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {

    private String issuer;
    private String audience;
    private String secretKey;

    @Getter(AccessLevel.NONE)
    private String ttlInSeconds;

    public String getJwtExpiration() {
        return this.ttlInSeconds;
    }
}
