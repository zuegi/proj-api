package ch.wesr.projectz.projapi.shared.eventbus;


import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectAccepted;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectPlaced;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
public class ProjectEventStore {
    private Map<String, ProjectCreation> projectStore = new ConcurrentHashMap<>();

    public ProjectCreation get(final String projectId) {
        return projectStore.get(projectId);
    }

    public void apply(ProjectPlaced projectPlaced) {
        projectStore.putIfAbsent(projectPlaced.getProjectInfo().getProjectId(), new ProjectCreation());
        applyFor(projectPlaced.getProjectInfo().getProjectId(), o -> o.place(projectPlaced.getProjectInfo()));
    }

    public void apply(ProjectAccepted projectAccepted) {
        applyFor(projectAccepted.getProjectCreation().projectInfo.getProjectId(), ProjectCreation::accept);
    }

    private void applyFor(final String orderId, final Consumer<ProjectCreation> consumer) {
        final ProjectCreation projectCreation = projectStore.get(orderId);
        if (projectCreation != null)
            consumer.accept(projectCreation);
    }
}
