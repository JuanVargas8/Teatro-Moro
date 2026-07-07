package cl.teatromoro.suscripciones.service;

import cl.teatromoro.common.exception.EntityNotFoundException;
import cl.teatromoro.suscripciones.dto.PlanDTO;
import cl.teatromoro.suscripciones.dto.PlanResponseDTO;
import cl.teatromoro.suscripciones.kafka.KafkaProducerService;
import cl.teatromoro.suscripciones.model.Plan;
import cl.teatromoro.suscripciones.repository.PlanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlanServiceTest {

    @Mock
    private PlanRepository repository;

    @Mock
    private KafkaProducerService producer;

    @InjectMocks
    private PlanService service;

    @Test
    void crearPlan() {

        PlanDTO dto = new PlanDTO();
        dto.setNombre("Premium");
        dto.setPrecio(14990.0);
        dto.setBeneficios("Acceso VIP");

        Plan planGuardado = new Plan(
                1L,
                "Premium",
                14990.0,
                "Acceso VIP"
        );

        when(repository.save(any(Plan.class)))
                .thenReturn(planGuardado);

        PlanResponseDTO response = service.crear(dto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Premium", response.getNombre());
        assertEquals(14990.0, response.getPrecio());

        verify(repository).save(any(Plan.class));
        verify(producer).enviarMensaje(anyString());
    }

    @Test
    void obtenerPlanExistente() {

        Plan plan = new Plan(
                1L,
                "Premium",
                14990.0,
                "Acceso VIP"
        );

        when(repository.findById(1L))
                .thenReturn(Optional.of(plan));

        PlanResponseDTO response = service.obtener(1L);

        assertNotNull(response);
        assertEquals("Premium", response.getNombre());

        verify(repository).findById(1L);
    }

    @Test
    void obtenerPlanInexistente() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> service.obtener(99L)
        );

        verify(repository).findById(99L);
    }
}