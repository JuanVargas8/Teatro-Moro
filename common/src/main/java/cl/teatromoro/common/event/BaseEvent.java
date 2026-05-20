package cl.teatromoro.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEvent {
    private String eventId;
    private LocalDateTime timestamp;
    private String source;
}