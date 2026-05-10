package cl.teatromoro.informes.mapper;

import org.springframework.stereotype.Component;
import cl.teatromoro.informes.dto.SnapshotVentasRequest;
import cl.teatromoro.informes.dto.SnapshotVentasResponse;
import cl.teatromoro.informes.model.entity.SnapshotVentas;

@Component
public class SnapshotVentasMapper {

    public SnapshotVentas toEntity(SnapshotVentasRequest request) {
        return SnapshotVentas.builder()
                .fecha(request.getFecha())
                .totalRecaudado(request.getTotalRecaudado())
                .totalTickets(request.getTotalTickets())
                .build();
    }

    public SnapshotVentasResponse toResponse(SnapshotVentas entity) {
        return SnapshotVentasResponse.builder()
                .id(entity.getId())
                .fecha(entity.getFecha())
                .totalRecaudado(entity.getTotalRecaudado())
                .totalTickets(entity.getTotalTickets())
                .build();
    }
}
