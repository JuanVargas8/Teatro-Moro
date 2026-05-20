package cl.teatromoro.pagos.repository;

import cl.teatromoro.pagos.model.MetodoPagoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPagoUsuario, Long> {}
    