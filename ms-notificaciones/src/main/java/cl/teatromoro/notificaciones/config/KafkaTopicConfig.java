package cl.teatromoro.notificaciones.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicNotificacionCreado() {
        log.debug("publicado topic kafka -> topic {}", "notificaciones.notificacion.created");
        return TopicBuilder.name("notificaciones.notificacion.created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicNotificacionUpadted() {
        log.debug("publicado topic kafka -> topic {}", "notificaciones.notificacion.updated");
        return TopicBuilder.name("notificaciones.notificacion.updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicNotificacionDeleted() {
        log.debug("publicado topic kafka -> topic {}", "notificaciones.notificacion.deleted");
        return TopicBuilder.name("notificaciones.notificacion.deleted")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
