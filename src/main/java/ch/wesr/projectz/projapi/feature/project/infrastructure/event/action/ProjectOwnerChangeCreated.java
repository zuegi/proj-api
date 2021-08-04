package ch.wesr.projectz.projapi.feature.project.infrastructure.event.action;

import ch.wesr.projectz.projapi.feature.project.infrastructure.event.ProjectLifecycle;
import ch.wesr.projectz.projapi.shared.eventbus.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProjectOwnerChangeCreated extends AbstractEvent {
    ProjectLifecycle projectLifecycle;
}
