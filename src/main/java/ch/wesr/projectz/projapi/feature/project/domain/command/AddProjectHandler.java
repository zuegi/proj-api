package ch.wesr.projectz.projapi.feature.project.domain.command;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.shared.command.CommandHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProjectHandler implements CommandHandler<CreateProject, Project> {

    Project project;

    @Override
    public Project handle(CreateProject cmd) {
        return Project.create(ProjectId.generate(), cmd.getName(), cmd.getDescription());
    }

}
