package ch.wesr.projectz.projapi.shared.command;


import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.User;
import ch.wesr.projectz.projapi.feature.project.domain.command.AddProject;
import ch.wesr.projectz.projapi.feature.project.domain.command.AddProjectHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InMemoryCommandDispatcherTest {

    @Test
    void project_with_command_is_valid() {
        InMemoryCommandDispatcher dispatcher = new InMemoryCommandDispatcher();
        dispatcher.registerHandler(AddProject.class, new AddProjectHandler());

        User paul = new User("Paul", "King");

        AddProject addProject = new AddProject("Project A", "Description of project A", paul);
        Project project = dispatcher.dispatch(addProject);
        assertNotNull(project.getProjectId());
        assertEquals(project.getName(), addProject.getName());
        assertEquals(project.getDescription(), addProject.getDescription());
        assertEquals(project.getProjectOwner(), paul);
    }
}
