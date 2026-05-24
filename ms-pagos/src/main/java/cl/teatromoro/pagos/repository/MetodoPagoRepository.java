package cl.teatromoro.pagos.repository;

import cl.teatromoro.pagos.model.MetodoPagoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPagoUsuario, Long> {
    
    // Al declarar esto aquí, el Service ya no te dará error en la línea roja
    List<MetodoPagoUsuario> findByIdUsuario(Long idUsuario);
}