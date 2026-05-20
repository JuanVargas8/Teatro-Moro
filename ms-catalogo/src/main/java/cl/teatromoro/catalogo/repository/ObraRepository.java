package cl.teatromoro.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.teatromoro.catalogo.model.entity.Obra;
import feign.Param;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long>{

    List<Obra> findByTituloContainingIgnoreCase(String titulo);

    List<Obra> findByClasificacionEdad(String clasificacionEdad);

    List<Obra> findByDuracionGreaterThan(Integer minutos);

    List<Obra> findByCategoriaId(Long categoriaId);
    
    boolean existsByTitulo(String titulo);

    List<Obra> findByCategoriaIdAndDuracionGreaterThan(Long categoriaId, Integer minutos);

    @Query("SELECT o FROM Obra o WHERE o.duracion BETWEEN :min AND :max")
    List<Obra> buscarPorRangoDuracion(@Param("min") int min, @Param("max") int max);
}
