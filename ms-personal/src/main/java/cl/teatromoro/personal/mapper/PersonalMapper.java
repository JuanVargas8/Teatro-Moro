package cl.teatromoro.personal.mapper;

import org.springframework.stereotype.Component;
import cl.teatromoro.personal.dto.PersonalRequest;
import cl.teatromoro.personal.dto.PersonalResponse;
import cl.teatromoro.personal.model.entity.Personal;

@Component
public class PersonalMapper {

    public Personal toEntity(PersonalRequest request) {
        return Personal.builder()
                .nombre(request.getNombre())
                .especialidad(request.getEspecialidad())
                .tipoContrato(request.getTipoContrato())
                .build();
    }

    public PersonalResponse toResponse(Personal entity) {
        return PersonalResponse.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .especialidad(entity.getEspecialidad())
                .tipoContrato(entity.getTipoContrato())
                .build();
    }
}
