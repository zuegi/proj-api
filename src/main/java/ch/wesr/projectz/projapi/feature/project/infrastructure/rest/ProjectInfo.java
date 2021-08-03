package ch.wesr.projectz.projapi.feature.project.infrastructure.rest;

import ch.wesr.projectz.projapi.feature.project.domain.ProjectMembers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectInfo {
    private String projectId;
    private String name;
    private String description;
    private ProjectOwnerInfo projectOwnerInfo;
    private ProjectMembersInfo projectMembersInfo;
}
