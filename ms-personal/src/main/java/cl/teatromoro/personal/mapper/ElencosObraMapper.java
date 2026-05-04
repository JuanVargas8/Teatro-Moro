package cl.teatromoro.personal.mapper;

import org.springframework.stereotype.Component;
import cl.teatromoro.personal.dto.ElencosObraRequest;
import cl.teatromoro.personal.dto.ElencosObraResponse;
import cl.teatromoro.personal.model.entity.ElencosObra;

@Component
public class ElencosObraMapper {

    public ElencosObra toEntity(ElencosObraRequest request) {
        return ElencosObra.builder()
                .idObra(request.getIdObra())
                .idPersonal(request.getIdPersonal())
                .rolEnObra(request.getRolEnObra())
                .build();
    }

    public ElencosObraResponse toResponse(ElencosObra entity) {
        return ElencosObraResponse.builder()
                .id(entity.getId())
                .idObra(entity.getIdObra())
                .idPersonal(entity.getIdPersonal())
                .rolEnObra(entity.getRolEnObra())
                .build();
    }
}
