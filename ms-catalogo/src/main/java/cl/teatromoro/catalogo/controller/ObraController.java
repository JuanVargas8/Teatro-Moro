package cl.teatromoro.catalogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.catalogo.entity.Obra;
import cl.teatromoro.catalogo.service.ObraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/obras")
@RequiredArgsConstructor
public class ObraController {

    private final ObraService service;

    @GetMapping
    public List<Obra> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Obra obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public Obra crear(@RequestBody Obra obra) {
        return service.guardar(obra);
    }

    @PutMapping("/{id}")
    public Obra actualizar(@PathVariable Long id, @RequestBody Obra obra) {
        return service.actualizar(id, obra);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // 🔍 ENDPOINTS PRO

    @GetMapping("/buscar")
    public List<Obra> buscarPorTitulo(@RequestParam String titulo) {
        return service.buscarPorTitulo(titulo);
    }

    @GetMapping("/categoria/{id}")
    public List<Obra> porCategoria(@PathVariable Long id) {
        return service.porCategoria(id);
    }

    @GetMapping("/largas")
    public List<Obra> obrasLargas(@RequestParam int minutos) {
        return service.largas(minutos);
    }
}
