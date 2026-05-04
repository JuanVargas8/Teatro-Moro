package cl.teatromoro.ticketing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import cl.teatromoro.ticketing.dto.TicketRequest;
import cl.teatromoro.ticketing.dto.TicketResponse;
import cl.teatromoro.ticketing.service.TicketService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;

    // ─── LISTAR ─────────────────────────────────────────

    @GetMapping
    public List<TicketResponse> listar() {
        return service.listar();
    }

    // ─── OBTENER POR ID ────────────────────────────────

    @GetMapping("/{id}")
    public TicketResponse obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    // ─── CREAR ─────────────────────────────────────────

    @PostMapping
    public TicketResponse crear(@RequestBody TicketRequest request) {
        return service.guardar(request);
    }

    // ─── ACTUALIZAR ────────────────────────────────────

    @PutMapping("/{id}")
    public TicketResponse actualizar(@PathVariable Long id,
                                     @RequestBody TicketRequest request) {
        return service.actualizar(id, request);
    }

    // ─── ELIMINAR ──────────────────────────────────────

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // ─── FILTRO POR USUARIO ────────────────────────────

    @GetMapping("/usuario/{idUsuario}")
    public List<TicketResponse> porUsuario(@PathVariable Integer idUsuario) {
        return service.porUsuario(idUsuario);
    }

    // ─── FILTRO POR FUNCION ────────────────────────────

    @GetMapping("/funcion/{idFuncion}")
    public List<TicketResponse> porFuncion(@PathVariable Integer idFuncion) {
        return service.porFuncion(idFuncion);
    }
}