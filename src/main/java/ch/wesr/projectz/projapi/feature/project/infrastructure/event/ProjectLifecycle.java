package ch.wesr.projectz.projapi.feature.project.infrastructure.event;

import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.PlaceProject;
import ch.wesr.projectz.projapi.feature.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProjectLifecycle {

    ProjectLifecycleState state;
    PlaceProject placeProject;
    User user;

    public void place(final PlaceProject placeProject) {
        this.placeProject = placeProject;
        this.state = ProjectLifecycleState.PLACED;
    }

    public void accept() {
        state = ProjectLifecycleState.ACCEPTED;
    }
    public void create() {
        state = ProjectLifecycleState.CREATED;
    }
    public void cancel() {
        state = ProjectLifecycleState.CANCELLED;
    }
    public void update() { state = ProjectLifecycleState.UPDATED; }
    public void delete() { state = ProjectLifecycleState.DELETED; }

    public enum ProjectLifecycleState {
        PLACED,
        ACCEPTED,
        STARTED,
        CREATED,
        CANCELLED,
        UPDATED,
        DELETED
    }
}
