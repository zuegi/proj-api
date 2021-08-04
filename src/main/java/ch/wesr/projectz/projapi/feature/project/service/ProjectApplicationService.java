package ch.wesr.projectz.projapi.feature.project.service;


import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.feature.project.infrastructure.event.action.ProjectCreated;
import ch.wesr.projectz.projapi.feature.project.infrastructure.event.action.ProjectOwnerChangeCreated;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.PlaceProject;
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
        PlaceProject pinfo = projectCreated.getProjectLifecycle().getPlaceProject();
        Project project = Project.create(new ProjectId(pinfo.getProjectId()), pinfo.getName(), pinfo.getDescription(), projectCreated.getProjectLifecycle().getUser(), null);
        repository.add(project);
    }

    public void saveProject(ProjectOwnerChangeCreated projectOwnerChangeCreated) {
        ProjectId projectId = new ProjectId(projectOwnerChangeCreated.getProjectLifecycle().getPlaceProject().getProjectId());
        Project project = repository.findLatestByProjectId(projectId);
        Project changedProject = project.changeProjectOwner(projectOwnerChangeCreated.getProjectLifecycle().getUser());
        repository.persist(project); // because changeProjectOwner changes project as well
        repository.add(changedProject);
    }
}
