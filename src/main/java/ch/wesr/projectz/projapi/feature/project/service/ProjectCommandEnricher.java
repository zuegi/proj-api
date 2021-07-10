package ch.wesr.projectz.projapi.feature.project.service;

import ch.wesr.projectz.projapi.feature.project.domain.User;
import ch.wesr.projectz.projapi.feature.project.domain.command.AddProjectOwner;
import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;
import ch.wesr.projectz.projapi.feature.project.domain.command.ProjectCommandHandler;
import ch.wesr.projectz.projapi.shared.command.Command;
import org.springframework.stereotype.Component;

@Component
public class ProjectCommandEnricher {


    public void enrich(Command cmd) {
        if (cmd instanceof CreateProject) {
            // enricht Project
        } else if (cmd instanceof AddProjectOwner) {
            AddProjectOwner addProjectOwner = (AddProjectOwner) cmd;
            addProjectOwner.setProjectOwner(findProjectOwnerById(addProjectOwner.getUserId()));
        }
    }

    private User findProjectOwnerById(String userId) {
        // FIXME
        return new User("uex1234","Paul", "King");
    }
}
