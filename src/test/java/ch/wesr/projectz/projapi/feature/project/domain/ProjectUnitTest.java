package ch.wesr.projectz.projapi.feature.project.domain;

import ch.wesr.projectz.projapi.feature.user.UserTestHelper;
import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.shared.exception.BusinessValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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

        createValidProject();
    }

    private Project createValidProject() {
        Project project = Project.create(ProjectId.generate(), PROJECT_A, PROJECT_DESCRIPTION_A,UserTestHelper.PROJECT_LEADER, Arrays.asList(UserTestHelper.PROJECT_MEMBER_ANNA, UserTestHelper.PROJECT_MEMBER_KARL));

        assertNotNull(project.getProjectId());
        assertEquals(project.getName(), PROJECT_A);
        assertEquals(project.getDescription(), PROJECT_DESCRIPTION_A);
        assertEquals(project.getProjectOwner(), UserTestHelper.PROJECT_LEADER);
        assertThat(project.getProjectMembers().getMemberSet(), hasSize(3));

        assertThat(project.getVersion(), is(0));
        assertNotNull(project.getCommitId());

        assertThat(project.isLatest(), is(true));
        return project;
    }

    @Test
    void project_change_project_owner_valid() {
        Project project = createValidProject();

        User paul = new User("pk060464", "Paul", "King");
        Project changedProject = project.changeProjectOwner(paul);
        assertEquals(project.getProjectId(), changedProject.getProjectId());
        assertEquals(changedProject.getName(), PROJECT_A);
        assertEquals(changedProject.getDescription(), PROJECT_DESCRIPTION_A);
        assertEquals(changedProject.getProjectOwner(), paul);

        assertThat(project.getVersion() +1 , is(changedProject.getVersion()));
        assertNotEquals(project.getCommitId(), changedProject.getCommitId());

        assertThat(project.isLatest(), is(false));
        assertThat(changedProject.isLatest(), is(true));
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
