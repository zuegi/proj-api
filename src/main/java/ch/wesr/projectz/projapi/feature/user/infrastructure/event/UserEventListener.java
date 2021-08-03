package ch.wesr.projectz.projapi.feature.user.infrastructure.event;

import ch.wesr.projectz.projapi.feature.project.service.ProjectCommandService;
import ch.wesr.projectz.projapi.shared.eventbus.ProjectEventStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserEventListener {
    @Autowired
    private ProjectEventStore projectEventStore;

    @Autowired
    private ProjectCommandService commandService;

    @EventListener
    public void handleUserFound(UserFound userFound) {
        log.info("Consuming Event: {} - accepting project with projectId: {}", userFound.getUser(), userFound.getProjectId());
        projectEventStore.applyUser(userFound);
        commandService.acceptProject(userFound.getProjectId(), userFound.getUser());
    }

    @EventListener
    public void handleUserNotFound(UserNotFound userNotFound) {
        log.info("Consuming Event: Invalid user {} - canceling project with projectId: {}", userNotFound.getUserId(), userNotFound.getProjectId());
        commandService.cancelProject(userNotFound.getProjectId(), userNotFound.getUserId());
    }

    @EventListener
    public void handleChangeProjectOwnerFound(ChangeProjectOwnerFound changeProjectOwnerFound) {
        log.info("Consuming Event: {} - accepting project with projectId: {}", changeProjectOwnerFound.getUser(), changeProjectOwnerFound.getProjectId());
        projectEventStore.applyChangeProjectOwnerFound(changeProjectOwnerFound);
        commandService.acceptChangeProjectOwner(changeProjectOwnerFound.projectId, changeProjectOwnerFound.user );
    }


    @EventListener
    public void handleChangeProjectOwnerNotFound(ChangeProjectOwnerNotFound changeProjectOwnerNotFound) {
        log.info("Consuming Event: Invalid user {} - canceling project with projectId: {}", changeProjectOwnerNotFound.getUserId(), changeProjectOwnerNotFound.getProjectId());
        commandService.cancelProject(changeProjectOwnerNotFound.getProjectId(), changeProjectOwnerNotFound.getUserId());
    }

}
