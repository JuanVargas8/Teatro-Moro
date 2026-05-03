package cl.teatromoro.personal.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import cl.teatromoro.personal.dto.AsistenciaRequest;
import cl.teatromoro.personal.dto.AsistenciaResponse;
import cl.teatromoro.personal.service.AsistenciaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/asistencia")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService service;

    @GetMapping
    public List<AsistenciaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AsistenciaResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public AsistenciaResponse crear(@RequestBody AsistenciaRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/personal/{idPersonal}")
    public List<AsistenciaResponse> porPersonal(@PathVariable Long idPersonal) {
        return service.listar().stream()
                .filter(a -> a.getIdPersonal().equals(idPersonal))
                .toList();
    }

    @GetMapping("/funcion/{idFuncion}")
    public List<AsistenciaResponse> porFuncion(@PathVariable Long idFuncion) {
        return service.listar().stream()
                .filter(a -> a.getIdFuncion().equals(idFuncion))
                .toList();
    }
}
