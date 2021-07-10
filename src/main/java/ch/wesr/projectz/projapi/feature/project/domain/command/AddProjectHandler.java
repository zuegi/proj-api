package ch.wesr.projectz.projapi.feature.project.domain.command;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.shared.command.CommandHandler;

public class AddProjectHandler implements CommandHandler<AddProject, Project> {

    @Override
    public Project handle(AddProject cmd) {
        return Project.create(ProjectId.generate(), cmd.getName(), cmd.getDescription(), cmd.getProjectOwner());
    }

}
