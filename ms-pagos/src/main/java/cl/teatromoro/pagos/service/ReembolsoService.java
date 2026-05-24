package cl.teatromoro.pagos.service;

import cl.teatromoro.pagos.model.Reembolso;
import cl.teatromoro.pagos.repository.ReembolsoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReembolsoService {

    private final ReembolsoRepository repository;

    public Reembolso guardar(Reembolso reembolso) {
        // Aquí podrías agregar lógica: por ejemplo, validar si la transacción existe
        return repository.save(reembolso);
    }
}