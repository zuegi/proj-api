package ch.wesr.projectz.projapi.feature.project.service;


import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;
import ch.wesr.projectz.projapi.feature.project.domain.command.ProjectCommandHandler;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.ProjectInfo;
import ch.wesr.projectz.projapi.shared.command.Command;
import ch.wesr.projectz.projapi.shared.command.InMemoryCommandDispatcher;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectCreated;
import ch.wesr.projectz.projapi.shared.exception.BusinessValidationException;
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

        Project project = null;
        for (Command command : commands) {
            if (command.getCommandId() != null) {
                project = findProjectByProjectId(command.getCommandId());
            }
            dispatcher.registerHandler(command.getClass(), new ProjectCommandHandler(project));
            enricher.enrich(command);
            project = dispatcher.dispatch(command);
        }
        repository.add(project);
        return project;
    }


    private Project findProjectByProjectId(String commandId) {
        if (commandId != null) {
            return repository.findLatestByProjectId(new ProjectId(commandId));
        }
        return null;
    }



    public void createProject(ProjectCreated projectCreated) {
        ProjectInfo pinfo = projectCreated.getProjectCreation().getProjectInfo();
        Project project = Project.create(new ProjectId(pinfo.getProjectId()), pinfo.getName(), pinfo.getDescription());
        repository.add(project);
    }
}
