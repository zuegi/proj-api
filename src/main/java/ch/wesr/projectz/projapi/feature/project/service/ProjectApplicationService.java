package ch.wesr.projectz.projapi.feature.project.service;


import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;
import ch.wesr.projectz.projapi.feature.project.domain.command.ProjectCommandHandler;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.shared.command.Command;
import ch.wesr.projectz.projapi.shared.command.InMemoryCommandDispatcher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ProjectApplicationService {

    InMemoryCommandDispatcher dispatcher;
    ProjectCommandEnricher enricher;
    ProjectRepository repository;

    public Project execute(List<Command> commands) {

        List<Project> projects = commands.stream().map(command -> {
            Project project = findProjectByProjectId(command.getCommandId());
            enricher.enrich(command);
            dispatcher.registerHandler(command.getClass(), new ProjectCommandHandler(project));

            return save(dispatcher.dispatch(command));
            // und wie mache ich hier, sodass ich ein Project zurückgeben kann??
        }).collect(Collectors.toList());

        // and return project to query oder so etwas
        return projects.get(projects.size() - 1);
    }

    private Project save(Project project) {
        log.info("save project; " + project);
        repository.add(project);
        return project;
    }

    private Project findProjectByProjectId(String commandId) {
        if (commandId != null) {
            CreateProject createProject = new CreateProject("Project A", "Description of project A");
            return Project.create(new ProjectId("12234"), createProject.getName(), createProject.getDescription());
        }
        CreateProject createProject = new CreateProject("Project A", "Description of project A");
        return Project.create(new ProjectId("12234"), createProject.getName(), createProject.getDescription());
    }

}
