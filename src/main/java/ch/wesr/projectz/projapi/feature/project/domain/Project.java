package ch.wesr.projectz.projapi.feature.project.domain;


import ch.wesr.projectz.projapi.shared.exception.BusinessValidationException;
import ch.wesr.projectz.projapi.shared.exception.BusinessValidationMsg;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

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
    private boolean latest;
    private int version = -1;

    public static Project create(ProjectId projectId, String name, String description) {
        Project project = new Project();
        project.projectId = projectId;
        project.name = name;
        project.description = description;

        project.validate();
        return project;
    }

    public static Project copy(Project project) {
        Project newProject = create(project.getProjectId(), project.getName(), project.getDescription());
        newProject.changeProjectOwner(project.getProjectOwner());
        return newProject;
    }

    public void changeProjectOwner(User projectOwner) {
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
    }

    public void changeProjectName(String name) {
        this.name = name;
        this.newCommit();
        this.validate();
    }
}
