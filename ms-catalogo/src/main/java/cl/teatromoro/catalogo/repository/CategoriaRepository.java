package cl.teatromoro.catalogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.catalogo.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    Optional<Categoria> findByNombre(String nombre);


    List<Categoria> findByNombreContainingIgnoreCase(String nombre);
}
