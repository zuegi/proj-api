package ch.wesr.projectz.projapi.feature.project.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class ProjectCommitId  {
    String id;
    public static ProjectCommitId generate() {
        return new ProjectCommitId(UUID.randomUUID().toString());
    }
}
