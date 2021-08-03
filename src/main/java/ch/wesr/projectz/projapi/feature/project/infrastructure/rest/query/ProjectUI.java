package ch.wesr.projectz.projapi.feature.project.infrastructure.rest.query;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUI {
    String projectId;
    String name;
    String description;
    ProjectOwnerUi projectOwnerUi;
}
