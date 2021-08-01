package ch.wesr.projectz.projapi.shared.eventbus.event;

import ch.wesr.projectz.projapi.shared.eventbus.ProjectCreation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCanceled extends AbstractEvent {
    ProjectCreation projectCreation;
    String reason;
}
