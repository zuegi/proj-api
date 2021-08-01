package ch.wesr.projectz.projapi.feature.project.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class ProjectId{
    String id;

    public static ProjectId generate() {
        return new ProjectId(UUID.randomUUID().toString());
    }
}
