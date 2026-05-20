package cl.teatromoro.common.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PeliculaDeletedEvent extends BaseEvent {
    private Long id;
}