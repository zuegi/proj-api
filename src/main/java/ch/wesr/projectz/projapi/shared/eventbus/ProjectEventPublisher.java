package ch.wesr.projectz.projapi.shared.eventbus;

import ch.wesr.projectz.projapi.feature.project.infrastructure.event.*;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.ChangeProjectOwnerFound;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.ChangeProjectOwnerNotFound;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.UserFound;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.UserNotFound;
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

    public void publish(ProjectCanceled projectCanceled) {
        log.info("Publishing event: {}", projectCanceled);
        applicationEventPublisher.publishEvent(projectCanceled);
    }

    public void publish(ProjectOwnerChangePlaced projectOwnerChangeAccepted) {
        log.info("Publishing event: {}", projectOwnerChangeAccepted);
        applicationEventPublisher.publishEvent(projectOwnerChangeAccepted);
    }

    public void publish(ChangeProjectOwnerFound changeProjectOwnerFound) {
        log.info("Publishing event: {}", changeProjectOwnerFound);
        applicationEventPublisher.publishEvent(changeProjectOwnerFound);
    }

    public void publish(ChangeProjectOwnerNotFound changeProjectOwnerNotFound) {
        log.info("Publishing event: {}", changeProjectOwnerNotFound);
        applicationEventPublisher.publishEvent(changeProjectOwnerNotFound);
    }

    public void publish(ProjectOwnerChangeCreated projectOwnerChangeCreated) {
        log.info("Publishing event: {}", projectOwnerChangeCreated);
        applicationEventPublisher.publishEvent(projectOwnerChangeCreated);
    }
}
