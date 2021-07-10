package ch.wesr.projectz.projapi.feature.project.domain.command;

import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.shared.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeProjectName implements Command {
    ProjectId projectId;
    String name;
}
