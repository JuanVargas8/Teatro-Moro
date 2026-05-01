package cl.teatromoro.catalogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.teatromoro.catalogo.entity.Categoria;
import cl.teatromoro.catalogo.service.CategoriaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;

    @GetMapping
    public List<Categoria> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Categoria obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return service.guardar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        return service.actualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
