package ch.wesr.projectz.projapi.shared.eventbus.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class AbstractEvent {
    Instant instant;
    public AbstractEvent() {
        this.instant = Instant.now();
    }
}
