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
    private String name;
    private String description;
    private User projectOwner;

    public static Project create(ProjectId projectId, String name, String description, User projectOwner) {
        Project project = new Project();
        project.projectId = projectId;
        project.name = name;
        project.description = description;
        project.projectOwner = projectOwner;

        project.validate();
        return project;
    }

    private void validate() {
        if(StringUtils.isBlank(this.name)) {
            throw new BusinessValidationException(BusinessValidationMsg.NAME_IS_MANDATORY);
        }
        if(this.projectId == null) {
            throw new BusinessValidationException(BusinessValidationMsg.PROJECT_ID_IS_MANDATORY);
        }
    }

}
