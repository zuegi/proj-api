package ch.wesr.projectz.projapi.feature.project.infrastructure.event;


import ch.wesr.projectz.projapi.feature.project.infrastructure.event.action.*;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.ChangeProjectOwnerFound;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.UserFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Slf4j
@Component
public class ProjectEventStore {
    private Map<String, ProjectLifecycle> projectStore = new ConcurrentHashMap<>();

    public ProjectLifecycle get(final String projectId) {
        return projectStore.get(projectId);
    }

    public void apply(ProjectPlaced projectPlaced) {
        projectStore.putIfAbsent(projectPlaced.getPlaceProject().getProjectId(), new ProjectLifecycle());
        applyFor(projectPlaced.getPlaceProject().getProjectId(), o -> o.place(projectPlaced.getPlaceProject()));
    }

    public void apply(ProjectAccepted projectAccepted) {
        applyFor(projectAccepted.getProjectLifecycle().getPlaceProject().getProjectId(), ProjectLifecycle::accept);
    }

    private void applyFor(final String projectId, final Consumer<ProjectLifecycle> consumer) {
        final ProjectLifecycle projectLifecycle = projectStore.get(projectId);
        if (projectLifecycle != null)
            consumer.accept(projectLifecycle);

        log.info("Status of project with projectId: {} has changed to: {}", projectId, projectLifecycle.getState() );
    }

    public void applyUser(UserFound userFound) {
        final ProjectLifecycle projectLifecycle = projectStore.get(userFound.getProjectId());
        projectLifecycle.setUser(userFound.getUser());
    }

    public void apply(ProjectCreated projectCreated) {
        applyFor(projectCreated.getProjectLifecycle().getPlaceProject().getProjectId(), ProjectLifecycle::create);
    }

    public void apply(ProjectCanceled projectCanceled) {
        applyFor(projectCanceled.getProjectLifecycle().getPlaceProject().getProjectId(), ProjectLifecycle::cancel);
    }

    public void applyChangeProjectOwnerFound(ChangeProjectOwnerFound changeProjectOwnerFound) {
        final ProjectLifecycle projectLifecycle = projectStore.get(changeProjectOwnerFound.getProjectId());
        projectLifecycle.setUser(changeProjectOwnerFound.getUser());
    }

    public void applyChangeProjectOwner(ProjectOwnerChangeCreated projectOwnerChangeCreated) {
        applyFor(projectOwnerChangeCreated.getProjectLifecycle().getPlaceProject().getProjectId(), ProjectLifecycle::update);
    }
}
