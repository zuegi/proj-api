package ch.wesr.projectz.projapi.feature.project.infrastructure;

import ch.wesr.projectz.projapi.AbstractIntegrationTest;
import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.feature.user.UserTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ProjectRepositoryImplIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    ProjectRepository repository;

    @Test
    void project_create_and_change_owner_valid() {
        ProjectId projectId = ProjectId.generate();
        Project project = Project.create(projectId, "Project A", "Description of project A", UserTestHelper.PROJECT_LEADER, null);
        repository.add(project);

        Project latestByProjectId = repository.findLatestByProjectId(projectId);
        assertThat(latestByProjectId.getProjectId(), is(projectId));
        assertThat(latestByProjectId.getProjectOwner(), is(UserTestHelper.PROJECT_LEADER));

        Project changedOwnerProject = latestByProjectId.changeProjectOwner(UserTestHelper.PROJECT_MEMBER_ANNA);
        repository.persist(latestByProjectId);
        repository.add(changedOwnerProject);

        Project changedProjectFromRepo = repository.findLatestByProjectId(projectId);
        assertThat(changedProjectFromRepo.getProjectId(), is(projectId));
        assertThat(changedProjectFromRepo.getProjectOwner(), is(UserTestHelper.PROJECT_MEMBER_ANNA));
    }


}
