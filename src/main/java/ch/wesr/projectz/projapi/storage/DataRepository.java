package ch.wesr.projectz.projapi.storage;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import lombok.extern.slf4j.Slf4j;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
@Component
public class DataRepository {

    @Autowired
    private EmbeddedStorageManager storageManager;

    @Autowired
    private DataRoot dataRoot;

    public List<Project> addProject(Project project) {
        List<Project> projectList = dataRoot.getProjectList();
        projectList.add(project);
        storageManager.store(dataRoot.getProjectList());
        return dataRoot.getProjectList();
    }

    public List<Project> getProjectList() {
        log.info("storageManager: " + storageManager.toString());
        List<Project> projects = dataRoot.getProjectList();
        projects.forEach(System.out::println);
        return projects;
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        storageManager.shutdown();
        log.info("Spring Container is destroyed!");
    }

    public String getContent() {
        return dataRoot.getContent();
    }

    public void addContent(String content) {
        log.info("repo location: " + dataRoot.getLocation());
        dataRoot.setContent(content);
        log.info("dataRoot content: " + dataRoot.getContent());
        storageManager.storeRoot();
    }
}
