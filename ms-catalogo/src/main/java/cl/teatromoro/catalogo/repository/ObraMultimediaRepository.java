package cl.teatromoro.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.teatromoro.catalogo.entity.MultimediaObra;

@Repository
public interface ObraMultimediaRepository extends JpaRepository<MultimediaObra, Long>{

    List<MultimediaObra> findByObraId(Long obraId);

    List<MultimediaObra> findByTipo(String tipo);

    List<MultimediaObra> findByObraIdAndTipo(Long obraId, String tipo);
}
