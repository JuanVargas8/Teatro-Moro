package cl.teatromoro.common;

import cl.teatromoro.common.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Auto-configuración de la librería common.
 *
 * Spring Boot descubre esta clase automáticamente al leer el archivo:
 *   META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 *
 * Esto permite que cualquier microservicio que declare common como dependencia
 * Maven reciba el bean GlobalExceptionHandler solo si no existe otro bean
 * con el mismo nombre en el contexto.
 */
@AutoConfiguration
public class CommonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "globalExceptionHandler")
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
