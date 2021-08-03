package ch.wesr.projectz.projapi.feature.project.infrastructure.rest.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectOwnerUi {
    String userId;
    String name;
    String lastName;
}
