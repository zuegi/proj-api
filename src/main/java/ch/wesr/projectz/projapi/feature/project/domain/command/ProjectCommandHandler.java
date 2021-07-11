package ch.wesr.projectz.projapi.feature.project.domain.command;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.shared.command.Command;
import ch.wesr.projectz.projapi.shared.command.CommandHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCommandHandler implements CommandHandler<Command, Project> {

    private Project project;

    @Override
    public Project handle(Command cmd) {

        if (cmd instanceof CreateProject) {
            CreateProject createProject = (CreateProject) cmd;
            return Project.create(new ProjectId(createProject.getCommandId()), createProject.getName(), createProject.getDescription());
        } else if (cmd instanceof ChangeProjectName) {
            ChangeProjectName changeProjectName = (ChangeProjectName) cmd;
            Project newProject = Project.copy(this.project);
            newProject.changeProjectName(changeProjectName.getName());
            return newProject;
        } else if (cmd instanceof AddProjectOwner) {
            AddProjectOwner addProjectOnwer = (AddProjectOwner) cmd;
            this.project.changeProjectOwner(addProjectOnwer.getProjectOwner());
        }
        return this.project;

    }
}
