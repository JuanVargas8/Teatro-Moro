package cl.teatromoro.catalogo.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.catalogo.dto.CategoriaRequest;
import cl.teatromoro.catalogo.dto.CategoriaResponse;
import cl.teatromoro.catalogo.model.entity.Categoria;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequest request) {
        return Categoria.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public CategoriaResponse toResponse(Categoria categoria) {
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }
}
