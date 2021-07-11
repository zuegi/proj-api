package ch.wesr.projectz.projapi.feature.project.infrastructure;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import ch.wesr.projectz.projapi.shared.persistence.Persistence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    private Persistence persistence;

    @Override
    public void add(Project project) {
        persistence.dataRoot().getProjectList().add(project);
        persistence.store(persistence.dataRoot().getProjectList());
    }
}
