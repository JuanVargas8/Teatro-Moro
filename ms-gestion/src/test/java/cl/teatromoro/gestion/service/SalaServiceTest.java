package cl.teatromoro.gestion.service;

import cl.teatromoro.common.exception.DuplicateResourceException;
import cl.teatromoro.common.exception.EntityNotFoundException;
import cl.teatromoro.gestion.dto.SalaRequest;
import cl.teatromoro.gestion.dto.SalaResponse;
import cl.teatromoro.gestion.mapper.SalaMapper;
import cl.teatromoro.gestion.model.entity.Sala;
import cl.teatromoro.gestion.repository.SalaRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas unitarias para SalaService.
 *
 * Mockito permite aislar SalaService de sus dependencias reales:
 * el repositorio y el mapper, sin necesidad de una base de datos real.
 */
@ExtendWith(MockitoExtension.class)
class SalaServiceTest {

    // @Mock crea una dependencia simulada, evitando usar la base de datos real.
    @Mock
    private SalaRepository repository;

    // Mock del mapper para controlar la conversion entre entidad y DTO.
    @Mock
    private SalaMapper mapper;

    // @InjectMocks crea SalaService e inyecta automaticamente los mocks anteriores.
    @InjectMocks
    private SalaService salaService;

    // DataFaker genera datos aleatorios para los objetos de prueba.
    private final Faker faker = new Faker();

    /**
     * Configuracion comun antes de cada prueba.
     *
     * Se define como debe comportarse el mock del mapper al convertir
     * una entidad Sala a SalaResponse.
     */
    @BeforeEach
    void setUp() {

        /*
         * lenient():
         * Evita que Mockito falle si alguna prueba no usa esta configuracion.
         *
         * when(...).thenAnswer(...):
         * Permite crear una respuesta dinamica usando el argumento recibido.
         * Aqui simulamos la conversion de Sala a SalaResponse sin usar el mapper real.
         */
        lenient().when(mapper.toResponse(any(Sala.class))).thenAnswer(invocation -> {
            Sala sala = invocation.getArgument(0);

            if (sala == null) return null;

            return SalaResponse.builder()
                    .id(sala.getId())
                    .nombre(sala.getNombre())
                    .capacidadTotal(sala.getCapacidadTotal())
                    .descripcionTecnica(sala.getDescripcionTecnica())
                    .build();
        });
    }

    /**
     * Crea una entidad Sala con datos aleatorios usando DataFaker.
     */
    private Sala crearSalaSimulada(Long id) {
        Sala sala = new Sala();

        sala.setId(id);
        sala.setNombre("Sala " + faker.space().constellation());   // ej: "Sala Orion"
        sala.setCapacidadTotal(faker.number().numberBetween(50, 800));
        sala.setDescripcionTecnica(faker.lorem().sentence());

        return sala;
    }

    /**
     * Crea un SalaRequest con datos aleatorios.
     */
    private SalaRequest crearSalaRequestSimulado() {
        SalaRequest request = new SalaRequest();

        request.setNombre("Sala " + faker.space().constellation());
        request.setCapacidadTotal(faker.number().numberBetween(50, 800));
        request.setDescripcionTecnica(faker.lorem().sentence());

        return request;
    }

    // =========================================================================
    // PRUEBAS PARA listar()
    // =========================================================================

    /**
     * Prueba listar() cuando existen 3 salas registradas.
     *
     * Se crean 3 salas simuladas y se verifica que el servicio
     * las retorne correctamente sin tocar la base de datos real.
     */
    @Test
    void listar_DeberiaRetornarListaDeSalas_CuandoExistenRegistros() {

        // Se crean 3 salas falsas con datos aleatorios.
        Sala sala1 = crearSalaSimulada(1L);
        Sala sala2 = crearSalaSimulada(2L);
        Sala sala3 = crearSalaSimulada(3L);

        // when(...).thenReturn(...): simula lo que devuelve el repositorio.
        when(repository.findAll()).thenReturn(List.of(sala1, sala2, sala3));

        List<SalaResponse> resultado = salaService.listar();

        // assertNotNull: verifica que el resultado no sea null.
        assertNotNull(resultado, "La lista retornada no debe ser nula");

        // assertEquals: compara el valor esperado con el valor obtenido.
        assertEquals(3, resultado.size(), "La lista debe contener exactamente 3 salas");

        // Verificamos los datos de la primera sala retornada.
        SalaResponse primera = resultado.get(0);

        assertEquals(sala1.getId(), primera.getId(), "El ID debe coincidir");
        assertEquals(sala1.getNombre(), primera.getNombre(), "El nombre debe coincidir");
        assertEquals(sala1.getCapacidadTotal(), primera.getCapacidadTotal(), "La capacidad debe coincidir");
        assertEquals(sala1.getDescripcionTecnica(), primera.getDescripcionTecnica(), "La descripcion tecnica debe coincidir");

        // verify: comprueba que el metodo del mock fue llamado exactamente una vez.
        verify(repository).findAll();
    }

    /**
     * Prueba listar() cuando no hay salas registradas.
     */
    @Test
    void listar_DeberiaRetornarListaVacia_CuandoNoExistenRegistros() {

        // Simula que el repositorio no tiene datos.
        when(repository.findAll()).thenReturn(List.of());

        List<SalaResponse> resultado = salaService.listar();

        // La lista debe existir pero estar vacia.
        assertNotNull(resultado, "La lista no debe ser null aunque este vacia");
        assertTrue(resultado.isEmpty(), "La lista debe estar vacia");

        verify(repository).findAll();
    }

    // =========================================================================
    // PRUEBAS PARA obtenerPorId()
    // =========================================================================

    /**
     * Prueba obtenerPorId() cuando el ID existe.
     */
    @Test
    void obtenerPorId_DeberiaRetornarSala_CuandoIdExiste() {
        Long id = 10L;
        Sala sala = crearSalaSimulada(id);

        // Simula que el repositorio encuentra la sala.
        when(repository.findById(id)).thenReturn(Optional.of(sala));

        SalaResponse resultado = salaService.obtenerPorId(id);

        // Verifica que el servicio retorne un objeto valido.
        assertNotNull(resultado);

        // Compara los datos esperados con los datos retornados.
        assertEquals(sala.getId(), resultado.getId());
        assertEquals(sala.getNombre(), resultado.getNombre());
        assertEquals(sala.getCapacidadTotal(), resultado.getCapacidadTotal());

        // Verifica que se haya buscado por ID.
        verify(repository).findById(id);
    }

    /**
     * Prueba obtenerPorId() cuando el ID no existe.
     */
    @Test
    void obtenerPorId_DeberiaLanzarEntityNotFoundException_CuandoIdNoExiste() {
        Long id = 999L;

        // Simula que el repositorio no encuentra la sala.
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Verifica que se lance la excepcion esperada.
        assertThrows(EntityNotFoundException.class, () -> salaService.obtenerPorId(id));

        // Verifica que se haya consultado el repositorio.
        verify(repository).findById(id);
    }

    // =========================================================================
    // PRUEBAS PARA guardar()
    // =========================================================================

    /**
     * Prueba guardar() cuando el nombre de la sala no esta duplicado.
     */
    @Test
    void guardar_DeberiaCrearSala_CuandoElNombreEsUnico() {
        SalaRequest request = crearSalaRequestSimulado();

        // Simula que no existe otra sala con el mismo nombre.
        when(repository.existsByNombreIgnoreCase(request.getNombre())).thenReturn(false);

        // Simula la conversion del request a entidad.
        Sala salaEntidad = crearSalaSimulada(null);
        salaEntidad.setNombre(request.getNombre());
        salaEntidad.setCapacidadTotal(request.getCapacidadTotal());
        salaEntidad.setDescripcionTecnica(request.getDescripcionTecnica());

        when(mapper.toEntity(request)).thenReturn(salaEntidad);

        /*
         * thenAnswer permite modificar y retornar el argumento recibido.
         * Aqui se simula que la base de datos asigna el ID al guardar.
         */
        when(repository.save(any(Sala.class))).thenAnswer(invocation -> {
            Sala s = invocation.getArgument(0);
            s.setId(100L);
            return s;
        });

        SalaResponse resultado = salaService.guardar(request);

        // Verifica que el servicio retorne una respuesta valida.
        assertNotNull(resultado);

        // Verifica que los datos retornados coincidan con lo esperado.
        assertEquals(100L, resultado.getId());
        assertEquals(request.getNombre(), resultado.getNombre());

        // Verifica las llamadas esperadas a los mocks.
        verify(repository).existsByNombreIgnoreCase(request.getNombre());
        verify(repository).save(any(Sala.class));
    }

    /**
     * Prueba guardar() cuando el nombre ya existe (debe lanzar excepcion).
     */
    @Test
    void guardar_DeberiaLanzarDuplicateResourceException_CuandoElNombreYaExiste() {
        SalaRequest request = crearSalaRequestSimulado();

        // Simula que ya existe una sala con ese nombre.
        when(repository.existsByNombreIgnoreCase(request.getNombre())).thenReturn(true);

        // Verifica que se lance excepcion por duplicidad.
        assertThrows(DuplicateResourceException.class, () -> salaService.guardar(request));

        // Verifica que se haya validado la existencia del nombre.
        verify(repository).existsByNombreIgnoreCase(request.getNombre());

        // never(): verifica que no se guardo nada en la base de datos.
        verify(repository, never()).save(any(Sala.class));
    }

    // =========================================================================
    // PRUEBAS PARA actualizar()
    // =========================================================================

    /**
     * Prueba actualizar() cuando la sala existe.
     */
    @Test
    void actualizar_DeberiaModificarSala_CuandoIdExiste() {
        Long id = 5L;

        Sala salaExistente = crearSalaSimulada(id);
        SalaRequest request = crearSalaRequestSimulado();

        // Simula que la sala existe.
        when(repository.findById(id)).thenReturn(Optional.of(salaExistente));

        // Simula que save() retorna la entidad modificada.
        when(repository.save(any(Sala.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SalaResponse resultado = salaService.actualizar(id, request);

        // Verifica que la respuesta no sea null.
        assertNotNull(resultado);

        // Verifica que los datos hayan sido actualizados con los del request.
        assertEquals(request.getNombre(), resultado.getNombre());
        assertEquals(request.getCapacidadTotal(), resultado.getCapacidadTotal());
        assertEquals(request.getDescripcionTecnica(), resultado.getDescripcionTecnica());

        // Verifica que se busco la sala por ID.
        verify(repository).findById(id);

        // Verifica que se guardaron los cambios.
        verify(repository).save(any(Sala.class));
    }

    /**
     * Prueba actualizar() cuando la sala no existe.
     */
    @Test
    void actualizar_DeberiaLanzarEntityNotFoundException_CuandoIdNoExiste() {
        Long id = 999L;
        SalaRequest request = crearSalaRequestSimulado();

        // Simula que la sala no existe.
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Verifica que se lance excepcion.
        assertThrows(EntityNotFoundException.class, () -> salaService.actualizar(id, request));

        verify(repository).findById(id);

        // No debe guardar nada si la sala no existe.
        verify(repository, never()).save(any(Sala.class));
    }

    // =========================================================================
    // PRUEBAS PARA eliminar()
    // =========================================================================

    /**
     * Prueba eliminar() cuando la sala existe.
     */
    @Test
    void eliminar_DeberiaEliminarSala_CuandoIdExiste() {
        Long id = 15L;

        Sala sala = crearSalaSimulada(id);

        // Simula que la sala existe.
        when(repository.findById(id)).thenReturn(Optional.of(sala));

        salaService.eliminar(id);

        // Verifica que se busco la sala antes de eliminarla.
        verify(repository).findById(id);

        // Verifica que se elimino la sala correctamente.
        verify(repository).delete(sala);
    }

    /**
     * Prueba eliminar() cuando la sala no existe.
     */
    @Test
    void eliminar_DeberiaLanzarEntityNotFoundException_CuandoIdNoExiste() {
        Long id = 999L;

        // Simula que la sala no existe.
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Verifica que se lance excepcion.
        assertThrows(EntityNotFoundException.class, () -> salaService.eliminar(id));

        verify(repository).findById(id);

        // No debe eliminar nada si la sala no existe.
        verify(repository, never()).delete(any(Sala.class));
    }
}