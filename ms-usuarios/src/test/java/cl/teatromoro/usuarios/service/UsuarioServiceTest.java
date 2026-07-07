package cl.teatromoro.usuarios.service;

import cl.teatromoro.usuarios.dto.UsuarioDTO;
import cl.teatromoro.usuarios.dto.UsuarioResponseDTO;
import cl.teatromoro.usuarios.kafka.KafkaProducerService;
import cl.teatromoro.usuarios.model.Usuario;
import cl.teatromoro.usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private KafkaProducerService producer;

    @InjectMocks
    private UsuarioService service;

    @Test
    void deberiaCrearUsuarioCorrectamente() {

        // Arrange
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Nicolás");
        dto.setEmail("nico@test.cl");
        dto.setPassword("1234");

        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setId(1L);
        usuarioGuardado.setNombre(dto.getNombre());
        usuarioGuardado.setEmail(dto.getEmail());
        usuarioGuardado.setPassword(dto.getPassword());
        usuarioGuardado.setFechaRegistro(LocalDate.now());

        when(repository.save(any(Usuario.class)))
                .thenReturn(usuarioGuardado);

        // Act
        UsuarioResponseDTO respuesta = service.crear(dto);

        // Assert
        assertNotNull(respuesta);
        assertEquals("Nicolás", respuesta.getNombre());
        assertEquals("nico@test.cl", respuesta.getEmail());
        assertNotNull(respuesta.getFechaRegistro());

        verify(repository, times(1))
                .save(any(Usuario.class));

        verify(producer, times(1))
                .enviarMensaje(any(String.class));
    }
}