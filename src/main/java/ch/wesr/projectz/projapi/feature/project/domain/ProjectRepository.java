package ch.wesr.projectz.projapi.feature.project.domain;

import java.util.List;

public interface ProjectRepository {

    void add(Project project);

    void persist(Project project);

    Project get(ProjectId projectId);

    Project findLatestByProjectId(ProjectId projectId);

    List<Project> findAll();
}
