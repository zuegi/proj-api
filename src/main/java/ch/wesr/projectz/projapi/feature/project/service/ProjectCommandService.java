package ch.wesr.projectz.projapi.feature.project.service;

import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.ProjectInfo;
import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.shared.eventbus.ProjectCreation;
import ch.wesr.projectz.projapi.shared.eventbus.ProjectEventPublisher;
import ch.wesr.projectz.projapi.shared.eventbus.ProjectEventStore;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectAccepted;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectCreated;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectPlaced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectCommandService {

    @Autowired
    private ProjectEventPublisher projectEventPublisher;

    @Autowired
    private ProjectEventStore projectEventStore;

    public void placeProject(ProjectInfo projectInfo) {
        projectEventPublisher.publish(new ProjectPlaced(projectInfo));
    }

    public void acceptProject(final String projectId, User user) {
        ProjectCreation projectCreation = projectEventStore.get(projectId);
        projectEventPublisher.publish(new ProjectAccepted(projectCreation, user));
    }

    public void createProject(String projectId) {
        ProjectCreation projectCreation = projectEventStore.get(projectId);
        projectEventPublisher.publish(new ProjectCreated(projectCreation));
    }
}