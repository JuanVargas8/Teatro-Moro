package cl.teatromoro.catalogo.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.catalogo.dto.ObraRequest;
import cl.teatromoro.catalogo.dto.ObraResponse;
import cl.teatromoro.catalogo.model.entity.Categoria;
import cl.teatromoro.catalogo.model.entity.Obra;

@Component
public class ObraMapper {

    public Obra toEntity(ObraRequest request, Categoria categoria) {
        return Obra.builder()
                .titulo(request.getTitulo())
                .sinopsis(request.getSinopsis())
                .duracion(request.getDuracion())
                .clasificacionEdad(request.getClasificacionEdad())
                .categoria(categoria)
                .build();
    }

    public ObraResponse toResponse(Obra obra) {
        return ObraResponse.builder()
                .id(obra.getId())
                .titulo(obra.getTitulo())
                .sinopsis(obra.getSinopsis())
                .duracion(obra.getDuracion())
                .clasificacionEdad(obra.getClasificacionEdad())
                .categoriaNombre(
                        obra.getCategoria() != null ? obra.getCategoria().getNombre() : null
                )
                .build();
    }
}
