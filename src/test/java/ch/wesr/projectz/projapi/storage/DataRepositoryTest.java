package ch.wesr.projectz.projapi.storage;

import ch.wesr.projectz.projapi.AbstractTest;
import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class DataRepositoryTest extends AbstractTest {

    @Autowired
    private DataRepository repository;

    @Test
    public void test() {


//        assertTrue(repository.getProjectList().isEmpty());
//        repository.addProject(new Project("projectz", "heimliches Projekt", "2da3-adf2K-12KT", new User("René", "Weishaupt")));
//
//        Collection<Project> projectList = repository.getProjectList();
//        assertEquals(1, projectList.size());
//
//        Project project1 = repository.getProjectList().stream().filter(project -> "2da3-adf2K-12KT".equals(project.getIdentifier())).findFirst().orElseGet(null);
//        assertTrue("2da3-adf2K-12KT".equals(project1.getIdentifier()));
    }

}
