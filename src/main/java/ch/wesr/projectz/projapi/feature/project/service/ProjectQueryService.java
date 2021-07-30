package ch.wesr.projectz.projapi.feature.project.service;

import ch.wesr.projectz.projapi.feature.project.domain.query.ProjectUI;
import org.springframework.stereotype.Service;

@Service
public class ProjectQueryService {

    public ProjectUI getProject(String projectId) {
        return new ProjectUI();
    }
}
