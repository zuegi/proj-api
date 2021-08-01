package ch.wesr.projectz.projapi.shared.eventbus;

import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.ProjectInfo;
import ch.wesr.projectz.projapi.feature.user.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProjectCreation {

    ProjectCreationState  state;
    ProjectInfo projectInfo;
    User user;

    public void place(final ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
        this.state = ProjectCreationState.PLACED;
    }

    public void accept() {
        state = ProjectCreationState.ACCEPTED;
    }
    public void create() {
        state = ProjectCreationState.CREATED;
    }
    public void cancel() {
        state = ProjectCreationState.CANCELLED;
    }

    public enum ProjectCreationState {
        PLACED,
        ACCEPTED,
        STARTED,
        CREATED,
        CANCELLED,
        DELETED
    }
}
