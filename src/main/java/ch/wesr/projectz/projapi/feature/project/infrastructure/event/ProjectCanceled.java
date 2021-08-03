package ch.wesr.projectz.projapi.feature.project.infrastructure.event;

import ch.wesr.projectz.projapi.shared.eventbus.ProjectCreation;
import ch.wesr.projectz.projapi.shared.eventbus.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProjectCanceled extends AbstractEvent {
    ProjectCreation projectCreation;
    String reason;
}
