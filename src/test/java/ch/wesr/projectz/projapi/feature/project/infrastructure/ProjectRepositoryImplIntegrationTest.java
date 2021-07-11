package ch.wesr.projectz.projapi.feature.project.infrastructure;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"integrationtest","local"})
class ProjectRepositoryImplIntegrationTest {

    @Autowired
    ProjectRepository repository;

//    @Test
//    void list_all_projects() {
//        repository.findAll().stream().forEach(System.out::println);
//    }
//    @Test
//    void project_find_latest_by_projectId() {
//        Project latestByProjectId = repository.findLatestByProjectId(new ProjectId("d363e682-dea7-443f-a3b0-e8e0ff2b3fa3"));
//        assertNotNull(latestByProjectId);
//    }


}
