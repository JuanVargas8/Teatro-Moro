package cl.teatromoro.suscripciones.kafka;

import cl.teatromoro.suscripciones.model.Abonado;
import cl.teatromoro.suscripciones.model.Plan;
import cl.teatromoro.suscripciones.repository.AbonadoRepository;
import cl.teatromoro.suscripciones.repository.PlanRepository;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class KafkaConsumerService {

    private final AbonadoRepository abonadoRepository;
    private final PlanRepository planRepository;

    public KafkaConsumerService(
            AbonadoRepository abonadoRepository,
            PlanRepository planRepository) {

        this.abonadoRepository = abonadoRepository;
        this.planRepository = planRepository;
    }

    @KafkaListener(
            topics = "abonados-topic",
            groupId = "ms-suscripciones"
    )
    public void consumir(String mensaje) {

        System.out.println(
                "Mensaje recibido Kafka: "
                        + mensaje
        );
    }

    @KafkaListener(
            topics = "usuarios-topic",
            groupId = "ms-suscripciones"
    )
    public void consumirUsuarios(String mensaje) {

        try {

            String[] datos = mensaje.split(":");

            Long usuarioId =
                    Long.parseLong(datos[0]);

            Plan plan =
                    planRepository.findById(1L)
                            .orElseThrow();

            Abonado abonado =
                    new Abonado();

            abonado.setUsuarioId(usuarioId);
            abonado.setPlan(plan);
            abonado.setFechaInicio(LocalDate.now());

            abonadoRepository.save(abonado);

            System.out.println(
                    "Abonado automático creado para usuario "
                            + usuarioId
            );

        } catch (Exception e) {

            System.out.println(
                    "Error procesando evento Kafka"
            );
        }
    }
}