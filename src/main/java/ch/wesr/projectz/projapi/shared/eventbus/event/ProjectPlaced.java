package ch.wesr.projectz.projapi.shared.eventbus.event;

import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.ProjectInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPlaced extends AbstractEvent {
    ProjectInfo projectInfo;
}
