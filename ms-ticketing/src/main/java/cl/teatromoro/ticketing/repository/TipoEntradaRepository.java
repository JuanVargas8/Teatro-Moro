package cl.teatromoro.ticketing.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.ticketing.model.TipoEntrada;

import java.util.Optional;

@Repository
public interface TipoEntradaRepository extends JpaRepository<TipoEntrada, Long> {

    Optional<TipoEntrada> findByNombre(String nombre);

}