package ch.wesr.projectz.projapi.feature.project.domain;

import ch.wesr.projectz.projapi.shared.exception.BusinessValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectUnitTest {


    @Test
    void project_without_name_invalid() {
        String description = "This is a project named A";
        Assertions.assertThrows(BusinessValidationException.class, () -> {
            final String name = "";
            Project.create(ProjectId.generate(), name, description);
        });
        Assertions.assertThrows(BusinessValidationException.class, () -> {
            Project.create(ProjectId.generate(), null, description);
        });
    }
    @Test
    void project_is_valid() {
        User paul = new User("uex1234", "Paul", "King");

        String name = "Project A";
        String description = "This is a project named A";
        Project project = Project.create(ProjectId.generate(), name, description);
        project.changeProjectOwner(paul);
        assertNotNull(project.getProjectId());
        assertEquals(project.getName(), name);
        assertEquals(project.getDescription(), description);
        assertEquals(project.getProjectOwner(), paul);
    }
}
