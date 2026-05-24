package cl.teatromoro.promociones.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic promocionesCampanaCreatedTopic() {
        return TopicBuilder.name("promociones.campana.created").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic promocionesCampanaDeletedTopic() {
        return TopicBuilder.name("promociones.campana.deleted").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic pagosTransaccionCreatedTopic() {
        return TopicBuilder.name("pagos.transaccion.created").partitions(1).replicas(1).build();
    }
}
