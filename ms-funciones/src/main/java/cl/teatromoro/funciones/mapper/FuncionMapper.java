package cl.teatromoro.funciones.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.funciones.dto.FuncionRequest;
import cl.teatromoro.funciones.dto.FuncionResponse;
import cl.teatromoro.funciones.model.entity.Funcion;

@Component
public class FuncionMapper {

    public Funcion toEntity(FuncionRequest request) {
        Funcion f = new Funcion();
        f.setIdObra(request.getObraId());
        f.setIdSala(request.getSalaId());
        f.setFechaHora(request.getFechaHora());
        f.setPrecioBase(request.getPrecioBase());
        return f;
    }

    public FuncionResponse toResponse(Funcion f) {
        return FuncionResponse.builder()
                .id(f.getId())
                .obraId(f.getIdObra())
                .salaId(f.getIdSala())
                .fechaHora(f.getFechaHora())
                .precioBase(f.getPrecioBase())
                .build();
    }
}
