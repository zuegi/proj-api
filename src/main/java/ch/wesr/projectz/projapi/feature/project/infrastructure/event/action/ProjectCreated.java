package ch.wesr.projectz.projapi.feature.project.infrastructure.event.action;

import ch.wesr.projectz.projapi.feature.project.infrastructure.event.ProjectLifecycle;
import ch.wesr.projectz.projapi.shared.eventbus.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ProjectCreated extends AbstractEvent {
    ProjectLifecycle projectLifecycle;
}
