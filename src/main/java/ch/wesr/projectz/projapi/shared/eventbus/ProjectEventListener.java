package ch.wesr.projectz.projapi.shared.eventbus;

import ch.wesr.projectz.projapi.feature.project.service.ProjectApplicationService;
import ch.wesr.projectz.projapi.feature.project.service.ProjectCommandService;
import ch.wesr.projectz.projapi.feature.user.service.UserService;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectAccepted;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectCreated;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectPlaced;
import ch.wesr.projectz.projapi.shared.eventbus.event.UserFound;
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
        // looking for a user like usd2835 then publish accept Project
        // look for userid und dann muss der User irgenwie in das Object rein, denn zum schluss schreiben wir das Object in den Store
        log.info("Looking for user with userId: {}", projectPlaced.getProjectInfo().getUserId());
        userService.publishUserById(projectPlaced.getProjectInfo().getProjectId(), projectPlaced.getProjectInfo().getUserId());
    }

    @EventListener
    public void handleUserFound(UserFound userFound) {
        log.info("Consuming Event: {} - accepting project with projectId: {}", userFound.getUser(), userFound.getProjectId());
        projectEventStore.applyUser(userFound);
        commandService.acceptProject(userFound.getProjectId(), userFound.getUser());
    }

    @EventListener
    public void handleProjectAccepted(ProjectAccepted projectAccepted) {
        log.info("Consuming Event: {}", projectAccepted);
        projectEventStore.apply(projectAccepted);
        commandService.createProject(projectAccepted.getProjectCreation().getProjectInfo().getProjectId());
    }

    @EventListener
    public void handleProjectCreated(ProjectCreated projectCreated) {
        log.info("Consuming Event: {}", projectCreated);
        projectEventStore.apply(projectCreated);
        projectApplicationService.createProject(projectCreated);
    }

}
