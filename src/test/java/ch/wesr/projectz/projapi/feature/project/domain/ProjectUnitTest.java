package ch.wesr.projectz.projapi.feature.project.domain;

import ch.wesr.projectz.projapi.feature.user.UserTestHelper;
import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.shared.exception.BusinessValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProjectUnitTest {


    public static final String PROJECT_A = "Project A";
    public static final String PROJECT_DESCRIPTION_A = "This is a project named A";

    @Test
    void project_without_name_invalid() {
        Assertions.assertThrows(BusinessValidationException.class, () -> {
            final String name = "";
            Project.create(ProjectId.generate(), name, PROJECT_DESCRIPTION_A, UserTestHelper.PROJECT_LEADER, Arrays.asList(UserTestHelper.PROJECT_MEMBER_ANNA, UserTestHelper.PROJECT_MEMBER_KARL));
        });
        Assertions.assertThrows(BusinessValidationException.class, () -> Project.create(ProjectId.generate(), null, PROJECT_DESCRIPTION_A,UserTestHelper.PROJECT_LEADER, Arrays.asList(UserTestHelper.PROJECT_MEMBER_ANNA, UserTestHelper.PROJECT_MEMBER_KARL)));
    }

    @Test
    void project_is_valid() {
        User paul = new User("pk060464", "Paul", "King");

        Project project = Project.create(ProjectId.generate(), PROJECT_A, PROJECT_DESCRIPTION_A,UserTestHelper.PROJECT_LEADER, Arrays.asList(UserTestHelper.PROJECT_MEMBER_ANNA, UserTestHelper.PROJECT_MEMBER_KARL));
        project.changeProjectOwner(paul);
        assertNotNull(project.getProjectId());
        assertEquals(project.getName(), PROJECT_A);
        assertEquals(project.getDescription(), PROJECT_DESCRIPTION_A);
        assertEquals(project.getProjectOwner(), paul);
    }

    @Test
    void project_without_projectOwner_invalid() {
        Assertions.assertThrows(BusinessValidationException.class, () -> Project.create(ProjectId.generate(), PROJECT_A, PROJECT_DESCRIPTION_A, null, Arrays.asList(UserTestHelper.PROJECT_MEMBER_ANNA, UserTestHelper.PROJECT_MEMBER_KARL)));
    }

    @Test
    void project_without_projectMembers_valid() {
        Project project = Project.create(ProjectId.generate(), PROJECT_A, PROJECT_DESCRIPTION_A, UserTestHelper.PROJECT_LEADER, null);
        assertNotNull(project.getProjectId());
        assertEquals(project.getName(), PROJECT_A);
        assertEquals(project.getDescription(), PROJECT_DESCRIPTION_A);
        assertEquals(project.getProjectOwner(), UserTestHelper.PROJECT_LEADER);
    }
}
