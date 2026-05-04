package cl.teatromoro.personal.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import cl.teatromoro.personal.dto.PersonalRequest;
import cl.teatromoro.personal.dto.PersonalResponse;
import cl.teatromoro.personal.service.PersonalService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/personal")
@RequiredArgsConstructor
public class PersonalController {

    private final PersonalService service;

    @GetMapping
    public List<PersonalResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PersonalResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public PersonalResponse crear(@RequestBody PersonalRequest request) {
        return service.guardar(request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
