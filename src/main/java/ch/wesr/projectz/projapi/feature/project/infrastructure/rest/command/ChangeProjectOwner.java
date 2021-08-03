package ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeProjectOwner {
    String projectId;
    String projectOwnerId;
}
