package ch.wesr.projectz.projapi.feature.project.domain;

import ch.wesr.projectz.projapi.shared.exception.BusinessValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectUnitTest {


    @Test
    void project_without_name_invalid() {
        User paul = new User("Paul", "King");
        String description = "This is a project named A";
        Assertions.assertThrows(BusinessValidationException.class, () -> {
            final String name = "";
            Project.create(ProjectId.generate(), name, description, paul );
        });
        Assertions.assertThrows(BusinessValidationException.class, () -> {
            Project.create(ProjectId.generate(), null, description, paul );
        });
    }
    @Test
    void project_is_valid() {
        User paul = new User("Paul", "King");

        String name = "Project A";
        String description = "This is a project named A";
        Project project = Project.create(ProjectId.generate(), name, description, paul );
        assertNotNull(project.getProjectId());
        assertEquals(project.getName(), name);
        assertEquals(project.getDescription(), description);
        assertEquals(project.getProjectOwner(), paul);
    }
}
