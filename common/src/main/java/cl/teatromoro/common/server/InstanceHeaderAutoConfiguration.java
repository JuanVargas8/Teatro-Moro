package cl.teatromoro.common.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class InstanceHeaderAutoConfiguration {

    @Bean
    public InstanceHeaderFilter instanceHeaderFilter(@Value("${spring.application.name:unknown-service}") String applicationName) {
        return new InstanceHeaderFilter(applicationName);
    }
}
