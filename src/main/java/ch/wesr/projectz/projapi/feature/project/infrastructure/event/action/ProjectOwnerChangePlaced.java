package ch.wesr.projectz.projapi.feature.project.infrastructure.event.action;

import ch.wesr.projectz.projapi.feature.project.infrastructure.event.ProjectLifecycle;
import ch.wesr.projectz.projapi.shared.eventbus.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectOwnerChangePlaced extends AbstractEvent {
    ProjectLifecycle projectLifecycle;
    String projectOwnerId;
}
