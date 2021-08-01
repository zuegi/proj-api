package ch.wesr.projectz.projapi.feature.project.service;


import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.ProjectInfo;
import ch.wesr.projectz.projapi.shared.eventbus.event.ProjectCreated;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ProjectApplicationService {

    ProjectRepository repository;

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
