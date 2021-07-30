package ch.wesr.projectz.projapi.shared.eventbus;

import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.ProjectInfo;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectAccepted;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectPlaced;
import ch.wesr.projectz.projapi.shared.eventbus.event.UserFound;
import ch.wesr.projectz.projapi.shared.eventbus.event.UserNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProjectEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(String message) {
        System.out.println("Publishing event: " +message);
        StringEvent stringEvent = new StringEvent(message);
        applicationEventPublisher.publishEvent(stringEvent);
    }

    public void publish(ProjectInfo projectInfo) {
        ProjectPlaced projectPlaced = new ProjectPlaced(projectInfo);
        log.info("Publishing event: ${projectPlaced}");
        applicationEventPublisher.publishEvent(projectPlaced);
    }

    public void publish(ProjectAccepted projectAccepted) {
        log.info("Publishing event: ${projectAccepted}");
        applicationEventPublisher.publishEvent(projectAccepted);
    }

    public void publish(UserFound userFound) {
        log.info("Publishing event: ${userFound}");
        applicationEventPublisher.publishEvent(userFound);
    }

    public void publish(UserNotFound userNotFound) {
        log.info("Publishing event: ${userNotFound}");
        applicationEventPublisher.publishEvent(userNotFound);
    }
}
