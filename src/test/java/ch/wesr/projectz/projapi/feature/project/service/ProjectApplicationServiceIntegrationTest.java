package ch.wesr.projectz.projapi.feature.project.service;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.command.AddProjectOwner;
import ch.wesr.projectz.projapi.feature.project.domain.command.ChangeProjectName;
import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"integrationtest","local"})
class ProjectApplicationServiceIntegrationTest {

    @Autowired
    ProjectApplicationService service;

    @Test
    void Project_create_valid() {
        CreateProject createProject = new CreateProject(null, "Project A", "Description of project A");
        AddProjectOwner addProjectOwner = new AddProjectOwner(null, "uex1234" );
        Project project = service.execute(Arrays.asList(createProject, addProjectOwner));

        assertEquals(project.getName(), createProject.getName());
        assertEquals(project.getProjectOwner().getName(), "Paul");

    }

    @Test
    void project_change_name_valid() {
        CreateProject createProject = new CreateProject(null, "Project A", "Description of project A");
        AddProjectOwner addProjectOwner = new AddProjectOwner(null, "uex1234" );
        Project project = service.execute(Arrays.asList(createProject, addProjectOwner));
        assertEquals(project.getName(), createProject.getName());
        assertEquals(project.getProjectOwner().getName(), "Paul");

        ChangeProjectName changeProjectName = new ChangeProjectName(project.getProjectId().getId(), "Project B");
        project = service.execute(Arrays.asList(changeProjectName));
        assertEquals(project.getName(), "Project B");

    }

}