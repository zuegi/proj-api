package ch.wesr.projectz.projapi.feature.project.infrastructure.repository;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.shared.persistence.Persistence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private Persistence persistence;

    @Override
    public void add(Project project) {
        persistence.dataRoot().getProjectList().add(project);
        persistence.store(persistence.dataRoot().getProjectList());
    }

    @Override
    public void persist(Project project) {
        persistence.store(project);
    }

    @Override
    public Project get(ProjectId projectId) {
        return null;
    }

    @Override
    public Project findLatestByProjectId(ProjectId projectId) {
        return persistence.dataRoot().getProjectList().stream()
                .filter(project -> project.isLatest() && project.getProjectId().getId().equals(projectId.getId()))
                .findFirst()
                .orElse(null);

    }

    @Override
    public List<Project> findAll() {
        return persistence.dataRoot().getProjectList();
    }


}
