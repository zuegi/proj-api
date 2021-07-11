package ch.wesr.projectz.projapi.shared.command;


import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.User;
import ch.wesr.projectz.projapi.feature.project.domain.command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCommandDispatcherTest {

    @Test
    void project_create_command_is_valid() {

        // there are 2 possibility of Handlers - a specifig AddProjectHandler or
        // a generic ProjectCommandHandler
        InMemoryCommandDispatcher dispatcher = new InMemoryCommandDispatcher();
//        dispatcher.registerHandler(AddProject.class, new AddProjectHandler());
        dispatcher.registerHandler(CreateProject.class, new ProjectCommandHandler());
        // Create Project
        CreateProject createProject = new CreateProject(null, "Project A", "Description of project A");
        Project project = dispatcher.dispatch(createProject);

        // Add projectOwner
        User projectOwner = new User("uex1234","Paul", "King");
        AddProjectOwner addProjectOwner = new AddProjectOwner(project.getProjectId().getId(), "uex1234", projectOwner);
        dispatcher.registerHandler(AddProjectOwner.class, new ProjectCommandHandler(project));
        project = dispatcher.dispatch(addProjectOwner);

        assertNotNull(project.getProjectId());
        assertEquals(project.getName(), createProject.getName());
        assertEquals(project.getDescription(), createProject.getDescription());
        assertEquals(project.getProjectOwner().getUserId(), addProjectOwner.getUserId());
        assertEquals(project.getProjectOwner(), projectOwner);
    }

    @Test
    void project_update_command_is_valid() {
        InMemoryCommandDispatcher dispatcher = new InMemoryCommandDispatcher();
        dispatcher.registerHandler(CreateProject.class, new ProjectCommandHandler());
        // Create Project
        CreateProject createProject = new CreateProject(null, "Project A", "Description of project A");
        Project project = dispatcher.dispatch(createProject);
        // Add projectOwner
        User projectOwner = new User("uex1234","Paul", "King");
        AddProjectOwner addProjectOwner = new AddProjectOwner( project.getProjectId().getId(),"uex1234", projectOwner);
        dispatcher.registerHandler(AddProjectOwner.class, new ProjectCommandHandler(project));
        project = dispatcher.dispatch(addProjectOwner);

        assertNotNull(project.getProjectId());
        assertEquals(project.getName(), createProject.getName());
        assertEquals(project.getDescription(), createProject.getDescription());
        assertEquals(project.getProjectOwner(), projectOwner);


        dispatcher.registerHandler(ChangeProjectName.class, new ProjectCommandHandler(project));
        ChangeProjectName changeProjectName = new ChangeProjectName(project.getProjectId().getId(), project.getName());

        Project updatedProject = dispatcher.dispatch(changeProjectName);

        assertEquals(project.getProjectId().getId(), changeProjectName.getProjectId());
        assertNotEquals(project.getCommitId(), updatedProject.getCommitId());
        assertEquals(updatedProject.getName(), changeProjectName.getName());
        assertEquals(project.getDescription(), updatedProject.getDescription());
        assertEquals(project.getProjectOwner(), updatedProject.getProjectOwner());

    }


}
