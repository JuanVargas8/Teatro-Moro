package cl.teatromoro.catalogo.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.catalogo.dto.MultimediaObraRequest;
import cl.teatromoro.catalogo.dto.MultimediaObraResponse;
import cl.teatromoro.catalogo.model.entity.MultimediaObra;
import cl.teatromoro.catalogo.model.entity.Obra;

@Component
public class MultimediaObraMapper {

    public MultimediaObra toEntity(MultimediaObraRequest request, Obra obra) {
        return MultimediaObra.builder()
                .urlImagen(request.getUrlImagen())
                .tipo(request.getTipo())
                .obra(obra)
                .build();
    }

    public MultimediaObraResponse toResponse(MultimediaObra multimedia) {
        return MultimediaObraResponse.builder()
                .id(multimedia.getId())
                .urlImagen(multimedia.getUrlImagen())
                .tipo(multimedia.getTipo())
                .obraId(
                        multimedia.getObra() != null ? multimedia.getObra().getId() : null
                )
                .build();
    }
}
