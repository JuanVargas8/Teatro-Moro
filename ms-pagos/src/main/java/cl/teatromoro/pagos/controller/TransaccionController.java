package cl.teatromoro.pagos.controller;

import cl.teatromoro.pagos.model.*;
import cl.teatromoro.pagos.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService service;

    // Endpoints para Transacciones
    @GetMapping("/transacciones")
    public List<Transaccion> obtenerTodas() {
        return service.listarTransacciones();
    }

    @PostMapping("/transacciones")
    public Transaccion crear(@RequestBody Transaccion t) {
        return service.guardarTransaccion(t);
    }

    // Endpoint para Métodos de Pago
    @PostMapping("/metodos-pago")
    public MetodoPagoUsuario registrarMetodo(@RequestBody MetodoPagoUsuario metodo) {
        return service.guardarMetodoPago(metodo);
    }

    // Endpoint para Reembolsos
    @PostMapping("/reembolsos")
    public Reembolso registrarReembolso(@RequestBody Reembolso reembolso) {
        return service.procesarReembolso(reembolso);
    }
}