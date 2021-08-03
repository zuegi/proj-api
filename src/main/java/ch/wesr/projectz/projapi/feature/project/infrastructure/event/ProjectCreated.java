package ch.wesr.projectz.projapi.feature.project.infrastructure.event;

import ch.wesr.projectz.projapi.shared.eventbus.ProjectCreation;
import ch.wesr.projectz.projapi.shared.eventbus.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ProjectCreated extends AbstractEvent {
    ProjectCreation projectCreation;
}
