package ch.wesr.projectz.projapi.feature.project.domain;

import ch.wesr.projectz.projapi.shared.command.CommandId;
import lombok.Value;

import java.util.UUID;

@Value
public class ProjectCommitId  implements CommandId {
    String id;
    public static ProjectCommitId generate() {
        return new ProjectCommitId(UUID.randomUUID().toString());
    }
}
