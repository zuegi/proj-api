package ch.wesr.projectz.projapi.feature.user.infrastructure.event;

import ch.wesr.projectz.projapi.shared.eventbus.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ChangeProjectOwnerNotFound extends AbstractEvent {
    String projectId;
    String userId;
}
