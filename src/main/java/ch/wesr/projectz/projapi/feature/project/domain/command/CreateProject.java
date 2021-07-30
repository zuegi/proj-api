package ch.wesr.projectz.projapi.feature.project.domain.command;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.shared.command.Command;
import ch.wesr.projectz.projapi.shared.command.CommandId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProject implements Command {
    private String projectId;
    private String name;
    private String description;

    private String type = "createProject";

    @Override
    public String getCommandId() {
        return projectId;
    }
}
