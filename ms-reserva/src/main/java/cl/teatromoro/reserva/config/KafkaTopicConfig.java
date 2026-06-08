package cl.teatromoro.reserva.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicReservaCreado() {
        log.debug("publicado topic kafka -> topic {}", "reservas.reserva.created");
        return TopicBuilder.name("reservas.reserva.created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicReservaUpadted() {
        log.debug("publicado topic kafka -> topic {}", "reservas.reserva.updated");
        return TopicBuilder.name("reservas.reserva.updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicReservaDeleted() {
        log.debug("publicado topic kafka -> topic {}", "reservas.reserva.deleted");
        return TopicBuilder.name("reservas.reserva.deleted")
                .partitions(1)
                .replicas(1)
                .build();
    }

}