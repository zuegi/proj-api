package ch.wesr.projectz.projapi.shared.eventbus;

import ch.wesr.projectz.projapi.feature.project.service.ProjectCommandService;
import ch.wesr.projectz.projapi.feature.user.service.UserService;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectAccepted;
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

    @EventListener
    public void handleStringEvent(StringEvent stringEvent) {
        System.out.println("Recieved string event: " + stringEvent.getMessage());
    }

    @EventListener
    public void handleProjectPlaced(ProjectPlaced projectPlaced) {
        log.info("Recieved  projectInfo: " + projectPlaced);
        projectEventStore.apply(projectPlaced);
        // looking for a user like usd2835 then publish accept Project
        // look for userid und dann muss der User irgenwie in das Object rein, denn zum schluss schreiben wir das Object in den Store
        userService.publishUserById(projectPlaced.getProjectInfo().getProjectId(), projectPlaced.getProjectInfo().getUserId());
    }

    @EventListener
    public void handleUserFound(UserFound userFound) {
        commandService.acceptProject(userFound.getProjectId(), userFound.getUser());
    }

    @EventListener
    public void handleProjectAccepted(ProjectAccepted projectAccepted) {
        projectEventStore.apply(projectAccepted);
    }

}
