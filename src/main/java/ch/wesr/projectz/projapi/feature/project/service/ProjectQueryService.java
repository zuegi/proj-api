package ch.wesr.projectz.projapi.feature.project.service;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.feature.project.domain.query.ProjectUI;
import ch.wesr.projectz.projapi.feature.project.infrastructure.repository.ProjectRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectQueryService {

    @Autowired
    ProjectRepositoryImpl repository;

    public ProjectUI getProject(String projectId) {
        Project project = repository.findLatestByProjectId(new ProjectId(projectId));
        return new ProjectUI(project.getProjectId().getId(), project.getName(), project.getDescription());
    }
}
