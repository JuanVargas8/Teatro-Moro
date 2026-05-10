package cl.teatromoro.ticketing.mapper;

import org.springframework.stereotype.Component;

import cl.teatromoro.ticketing.dto.TicketRequest;
import cl.teatromoro.ticketing.dto.TicketResponse;
import cl.teatromoro.ticketing.model.Ticket;

@Component
public class TicketMapper {

    public Ticket toEntity(TicketRequest request){
        return Ticket.builder()
        .idFuncion(request.getIdFuncion())
        .idUsuario(request.getIdUsuario())
        .precioFinal(request.getPrecioFinal())
        .build();
    }

    public TicketResponse toResponse(Ticket entity){
        return TicketResponse.builder()
        .id(entity.getId())
        .idFuncion(entity.getIdFuncion())
        .idUsuario(entity.getIdUsuario())
        .precioFinal(entity.getPrecioFinal())
        .build();
    }




}
