package cl.teatromoro.common.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanaDeletedEvent {
    private Long id;
}