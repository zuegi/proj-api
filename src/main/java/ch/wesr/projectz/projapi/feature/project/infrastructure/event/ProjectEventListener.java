package ch.wesr.projectz.projapi.feature.project.infrastructure.event;

import ch.wesr.projectz.projapi.feature.project.infrastructure.event.action.*;
import ch.wesr.projectz.projapi.feature.project.service.ProjectApplicationService;
import ch.wesr.projectz.projapi.feature.project.service.ProjectCommandService;
import ch.wesr.projectz.projapi.feature.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProjectEventListener {

    @Autowired
    private ProjectEventStore projectEventStore;

    @Autowired
    private ProjectCommandService commandService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectApplicationService projectApplicationService;

    @EventListener
    public void handleProjectPlaced(ProjectPlaced projectPlaced) {
        projectEventStore.apply(projectPlaced);
        log.info("Consuming Event: {}", projectPlaced);
        // looking for a user like rw170669 then publish accept Project
        // look for userid und dann muss der User irgenwie in das Object rein, denn zum schluss schreiben wir das Object in den Store
        log.info("Looking for user with userId: {}", projectPlaced.getPlaceProject().getProjectOwnerInfo().getUserId());
        userService.publishUserById(projectPlaced.getPlaceProject().getProjectId(), projectPlaced.getPlaceProject().getProjectOwnerInfo().getUserId());
    }

    @EventListener
    public void handleProjectAccepted(ProjectAccepted projectAccepted) {
        log.info("Consuming Event: {}", projectAccepted);
        projectEventStore.apply(projectAccepted);
        commandService.createProject(projectAccepted.getProjectLifecycle().getPlaceProject().getProjectId());
    }

    @EventListener
    public void handleProjectCreated(ProjectCreated projectCreated) {
        log.info("Consuming Event: {}", projectCreated);
        projectEventStore.apply(projectCreated);
        projectApplicationService.createProject(projectCreated);
    }

    @EventListener
    public void handleProjectCanceled(ProjectCanceled projectCanceled) {
        log.info("Consuming Event: {}", projectCanceled);
        projectEventStore.apply(projectCanceled);
    }

    @EventListener
    public void handleProjectOwnerChangePlaced(ProjectOwnerChangePlaced projectOwnerChangeAccepted) {
        log.info("Consuming Event: {}", projectOwnerChangeAccepted);
        log.info("Looking for user with userId: {}", projectOwnerChangeAccepted.getProjectOwnerId());
        userService.publishChangeProjectOwnerById(projectOwnerChangeAccepted.getProjectLifecycle().getPlaceProject().getProjectId(), projectOwnerChangeAccepted.getProjectOwnerId());
    }

    @EventListener
    public void handleProjectOwnerChangeCreated(ProjectOwnerChangeCreated projectOwnerChangeCreated) {
        log.info("Consuming Event: {}", projectOwnerChangeCreated);
        projectEventStore.applyChangeProjectOwner(projectOwnerChangeCreated);
        projectApplicationService.saveProject(projectOwnerChangeCreated);

    }

}
