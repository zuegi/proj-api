package ch.wesr.projectz.projapi.feature.project.infrastructure.event;

import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.ProjectInfo;
import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.shared.eventbus.ProjectCreation;
import ch.wesr.projectz.projapi.shared.eventbus.event.AbstractEvent;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProjectAccepted extends AbstractEvent {
   ProjectCreation projectCreation;
   User user;

}
