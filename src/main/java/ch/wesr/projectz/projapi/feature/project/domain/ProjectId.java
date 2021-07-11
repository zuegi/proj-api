package ch.wesr.projectz.projapi.feature.project.domain;

import ch.wesr.projectz.projapi.shared.command.CommandId;
import lombok.Value;

import java.util.UUID;

@Value
public class ProjectId implements CommandId {
    String id;

    public static ProjectId generate() {
        return new ProjectId(UUID.randomUUID().toString());
    }
}
