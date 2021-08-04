package ch.wesr.projectz.projapi.feature.project.service;

import ch.wesr.projectz.projapi.feature.project.infrastructure.event.action.*;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.PlaceProject;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.query.ProjectOwnerUi;
import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.feature.project.infrastructure.event.ProjectLifecycle;
import ch.wesr.projectz.projapi.feature.project.infrastructure.event.ProjectEventPublisher;
import ch.wesr.projectz.projapi.feature.project.infrastructure.event.ProjectEventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectCommandService {

    @Autowired
    private ProjectEventPublisher projectEventPublisher;

    @Autowired
    private ProjectEventStore projectEventStore;

    public void placeProject(PlaceProject placeProject) {
        projectEventPublisher.publish(new ProjectPlaced(placeProject));
    }

    public void acceptProject(final String projectId, User user) {
        ProjectLifecycle projectLifecycle = projectEventStore.get(projectId);
        projectEventPublisher.publish(new ProjectAccepted(projectLifecycle, user));
    }

    public void createProject(String projectId) {
        ProjectLifecycle projectLifecycle = projectEventStore.get(projectId);
        projectEventPublisher.publish(new ProjectCreated(projectLifecycle));
    }

    public void cancelProject(String projectId, String userId) {
        ProjectLifecycle projectLifecycle = projectEventStore.get(projectId);
        String reason = "User with ID: ["+userId +"] not found";
        projectEventPublisher.publish(new ProjectCanceled(projectLifecycle, reason));
    }

    public void changeProjectOwner(String projectId, ProjectOwnerUi projectOwnerUI) {
        ProjectLifecycle projectLifecycle = projectEventStore.get(projectId);
        projectEventPublisher.publish(new ProjectOwnerChangePlaced(projectLifecycle, projectOwnerUI.getUserId()));
    }


    public void acceptChangeProjectOwner(String projectId, User user) {
        ProjectLifecycle projectLifecycle = projectEventStore.get(projectId);
        projectLifecycle.setUser(user);
        projectEventPublisher.publish(new ProjectOwnerChangeCreated(projectLifecycle));
    }
}
