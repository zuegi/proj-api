package ch.wesr.projectz.projapi.feature.project.domain;


import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.shared.exception.BusinessValidationException;
import ch.wesr.projectz.projapi.shared.exception.BusinessValidationMsg;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Project {

    private ProjectId projectId;
    private ProjectCommitId commitId;
    private String name;
    private String description;
    private User projectOwner;
    private ProjectMembers projectMembers;
    private boolean latest;
    private int version = -1;

    public static Project create(ProjectId projectId, String name, String description, User projectOwner, List<User> projectMembers) {
        Project project = new Project();
        project.projectId = projectId;
        project.name = name;
        project.description = description;
        project.projectOwner = projectOwner;
        project.projectMembers = ProjectMembers.create(projectMembers, projectOwner);

        project.validate();
        return project;
    }

    public Project copy() {
        Project newProject = new Project();
        newProject.projectId = this.projectId;
        newProject.name = this.name;
        newProject.description = this.description;
        newProject.projectOwner = projectOwner;
        newProject.projectMembers = projectMembers;
        newProject.validate();
        newProject.newCommit();
        return newProject;
    }

    public void changeProjectOwner(User projectOwner) {
        Project newProject = copy();
        newProject.projectOwner = projectOwner;
        this.projectOwner = projectOwner;
    }

    private void newCommit() {
        this.commitId = ProjectCommitId.generate();
        this.latest = true;
        this.version++;
    }

    private void validate() {
        if(StringUtils.isBlank(this.name)) {
            throw new BusinessValidationException(BusinessValidationMsg.NAME_IS_MANDATORY);
        }
        if(this.projectId == null) {
            throw new BusinessValidationException(BusinessValidationMsg.PROJECT_ID_IS_MANDATORY);
        }
        if (this.projectOwner == null) {
            throw new BusinessValidationException(BusinessValidationMsg.PROJECT_OWNER_IS_MANDATORY);
        }

        this.projectMembers.validate();
    }

    public void changeProjectName(String name) {
        this.name = name;
        this.newCommit();
        this.validate();
    }
}
