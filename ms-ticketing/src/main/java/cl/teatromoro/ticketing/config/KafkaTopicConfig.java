package cl.teatromoro.ticketing.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicTicketCreado() {
        log.debug("publicado topic kafka -> topic {}", "ticketing.ticket.created");
        return TopicBuilder.name("ticketing.ticket.created")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicTicketUpadted() {
        log.debug("publicado topic kafka -> topic {}", "ticketing.ticket.updated");
        return TopicBuilder.name("ticketing.ticket.updated")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicTicketDeleted() {
        log.debug("publicado topic kafka -> topic {}", "ticketing.ticket.deleted");
        return TopicBuilder.name("ticketing.ticket.deleted")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
