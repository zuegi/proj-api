package ch.wesr.projectz.projapi.feature.project.service;


import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.feature.project.infrastructure.event.ProjectCreated;
import ch.wesr.projectz.projapi.feature.project.infrastructure.event.ProjectOwnerChangeCreated;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.ProjectInfo;
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
        Project project = Project.create(new ProjectId(pinfo.getProjectId()), pinfo.getName(), pinfo.getDescription(), projectCreated.getProjectCreation().getUser(), null);
        repository.add(project);
    }

    public void saveProject(ProjectOwnerChangeCreated projectOwnerChangeCreated) {
        ProjectId projectId = new ProjectId(projectOwnerChangeCreated.getProjectCreation().getProjectInfo().getProjectId());
        Project project = repository.findLatestByProjectId(projectId);
        Project changedProject = project.changeProjectOwner(projectOwnerChangeCreated.getProjectCreation().getUser());
        repository.persist(project); // because changeProjectOwner changes project as well
        repository.add(changedProject);
    }
}
