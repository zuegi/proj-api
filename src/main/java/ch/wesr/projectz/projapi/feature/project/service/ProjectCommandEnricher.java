package ch.wesr.projectz.projapi.feature.project.service;

import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.feature.project.domain.command.AddProjectOwner;
import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;
import ch.wesr.projectz.projapi.shared.command.Command;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProjectCommandEnricher {

    private ProjectRepository repository;

    public void enrich(Command cmd) {
        if (cmd instanceof CreateProject) {
            CreateProject createProject = (CreateProject) cmd;
            createProject.setProjectId(ProjectId.generate().getId());
        } else if (cmd instanceof AddProjectOwner) {
            AddProjectOwner addProjectOwner = (AddProjectOwner) cmd;
            addProjectOwner.setProjectOwner(findProjectOwnerById(addProjectOwner.getUserId()));
        }

    }

    private User findProjectOwnerById(String userId) {
        // FIXME Implement findProjectOwnwerById...
        return new User("uex1234","Paul", "King");
    }

}
