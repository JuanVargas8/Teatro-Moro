package cl.teatromoro.suscripciones.service;

import feign.FeignException;

import cl.teatromoro.suscripciones.client.UsuarioClient;
import cl.teatromoro.suscripciones.dto.AbonadoDTO;
import cl.teatromoro.suscripciones.dto.AbonadoResponseDTO;
import cl.teatromoro.suscripciones.dto.AbonadoUpdateDTO;
import cl.teatromoro.suscripciones.dto.PlanResponseDTO;
import cl.teatromoro.suscripciones.exception.ResourceNotFoundException;
import cl.teatromoro.suscripciones.kafka.KafkaProducerService;
import cl.teatromoro.suscripciones.model.Abonado;
import cl.teatromoro.suscripciones.model.Plan;
import cl.teatromoro.suscripciones.repository.AbonadoRepository;
import cl.teatromoro.suscripciones.repository.PlanRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AbonadoService {

    private final AbonadoRepository repository;
    private final UsuarioClient usuarioClient;
    private final PlanRepository planRepository;
    private final KafkaProducerService producer;

    public AbonadoService(
            AbonadoRepository repository,
            UsuarioClient usuarioClient,
            PlanRepository planRepository,
            KafkaProducerService producer) {

        this.repository = repository;
        this.usuarioClient = usuarioClient;
        this.planRepository = planRepository;
        this.producer = producer;
    }

    public AbonadoResponseDTO crear(AbonadoDTO dto) {

        try {

            usuarioClient.obtenerUsuario(dto.getUsuarioId());

        } catch (FeignException.NotFound e) {

            throw new ResourceNotFoundException(
                    "Usuario no existe"
            );

        } catch (FeignException e) {

            throw new RuntimeException(
                    "Error al validar usuario"
            );
        }

        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Plan no existe"
                        ));

        Abonado abonado = new Abonado();

        abonado.setUsuarioId(dto.getUsuarioId());
        abonado.setPlan(plan);
        abonado.setFechaInicio(LocalDate.now());

        Abonado guardado = repository.save(abonado);

        producer.enviarMensaje(
                "Nuevo abonado creado para usuario "
                        + dto.getUsuarioId()
        );

        PlanResponseDTO planDTO = new PlanResponseDTO(
                guardado.getPlan().getId(),
                guardado.getPlan().getNombre(),
                guardado.getPlan().getPrecio(),
                guardado.getPlan().getBeneficios()
        );

        return new AbonadoResponseDTO(
                guardado.getId(),
                guardado.getUsuarioId(),
                planDTO,
                guardado.getFechaInicio(),
                guardado.getFechaFin()
        );
    }

    public List<AbonadoResponseDTO> listar() {

        return repository.findAll()
                .stream()
                .map(abonado -> {

                    PlanResponseDTO planDTO =
                            new PlanResponseDTO(
                                    abonado.getPlan().getId(),
                                    abonado.getPlan().getNombre(),
                                    abonado.getPlan().getPrecio(),
                                    abonado.getPlan().getBeneficios()
                            );

                    return new AbonadoResponseDTO(
                            abonado.getId(),
                            abonado.getUsuarioId(),
                            planDTO,
                            abonado.getFechaInicio(),
                            abonado.getFechaFin()
                    );
                })
                .toList();
    }

    public List<AbonadoResponseDTO> porUsuario(
            Long usuarioId) {

        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(abonado -> {

                    PlanResponseDTO planDTO =
                            new PlanResponseDTO(
                                    abonado.getPlan().getId(),
                                    abonado.getPlan().getNombre(),
                                    abonado.getPlan().getPrecio(),
                                    abonado.getPlan().getBeneficios()
                            );

                    return new AbonadoResponseDTO(
                            abonado.getId(),
                            abonado.getUsuarioId(),
                            planDTO,
                            abonado.getFechaInicio(),
                            abonado.getFechaFin()
                    );
                })
                .toList();
    }

    public AbonadoResponseDTO actualizar(
            Long id,
            AbonadoUpdateDTO dto) {

        Abonado abonado =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Abonado no encontrado"
                                ));

        Plan plan =
                planRepository.findById(dto.getPlanId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Plan no existe"
                                ));

        abonado.setPlan(plan);
        abonado.setFechaFin(dto.getFechaFin());

        Abonado actualizado =
                repository.save(abonado);

        PlanResponseDTO planDTO =
                new PlanResponseDTO(
                        actualizado.getPlan().getId(),
                        actualizado.getPlan().getNombre(),
                        actualizado.getPlan().getPrecio(),
                        actualizado.getPlan().getBeneficios()
                );

        return new AbonadoResponseDTO(
                actualizado.getId(),
                actualizado.getUsuarioId(),
                planDTO,
                actualizado.getFechaInicio(),
                actualizado.getFechaFin()
        );
    }
    public void eliminar(Long id) {

        Abonado abonado =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Abonado no encontrado"
                                ));

        repository.delete(abonado);
        }

    public AbonadoResponseDTO obtenerPorId(Long id) {

    Abonado abonado = repository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Abonado no encontrado"
                    ));

    PlanResponseDTO planDTO =
            new PlanResponseDTO(
                    abonado.getPlan().getId(),
                    abonado.getPlan().getNombre(),
                    abonado.getPlan().getPrecio(),
                    abonado.getPlan().getBeneficios()
            );

    return new AbonadoResponseDTO(
            abonado.getId(),
            abonado.getUsuarioId(),
            planDTO,
            abonado.getFechaInicio(),
            abonado.getFechaFin()
    );
}    
}