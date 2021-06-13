package ch.wesr.projectz.projapi.storage;

import ch.wesr.projectz.projapi.domain.Project;
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

    public void addProject(Project project) {
        DataRoot dataRoot = (DataRoot) storageManager.root();
        List<Project> projectList = dataRoot.getProjectList();
        projectList.add(project);
        storageManager.storeAll(projectList);
        storageManager.storeRoot();
        storageManager.storeAll(dataRoot);
    }

    public List<Project> getProjectList() {
//        DataRoot dataRoot = (DataRoot) storageManager.root();
        return dataRoot.getProjectList();
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        storageManager.shutdown();
        log.info("Spring Container is destroyed!");
    }

    public String getContent() {
//        DataRoot dataRoot = (DataRoot) storageManager.root();
        return dataRoot.getContent();
    }

    public void addContent(String content) {
//        DataRoot dataRoot = (DataRoot) storageManager.root();
        log.info("repo location: " +dataRoot.getLocation());
        dataRoot.setContent(content);
        log.info("dataRoot content: " +dataRoot.getContent());
        storageManager.storeRoot();
    }
}