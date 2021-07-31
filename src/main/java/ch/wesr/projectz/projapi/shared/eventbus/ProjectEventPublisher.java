package ch.wesr.projectz.projapi.shared.eventbus;

import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.ProjectInfo;
import ch.wesr.projectz.projapi.shared.eventbus.event.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProjectEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(ProjectPlaced projectPlaced) {
        log.info("Publishing event: {}", projectPlaced);
        applicationEventPublisher.publishEvent(projectPlaced);
    }

    public void publish(ProjectAccepted projectAccepted) {
        log.info("Publishing event: {}", projectAccepted);
        applicationEventPublisher.publishEvent(projectAccepted);
    }

    public void publish(UserFound userFound) {
        log.info("Publishing event: {}", userFound);
        applicationEventPublisher.publishEvent(userFound);
    }

    public void publish(UserNotFound userNotFound) {
        log.info("Publishing event: {}", userNotFound);
        applicationEventPublisher.publishEvent(userNotFound);
    }

    public void publish(ProjectCreated projectCreated) {
        log.info("Publishing event: {}", projectCreated);
        applicationEventPublisher.publishEvent(projectCreated);
    }
}
