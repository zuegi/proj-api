package ch.wesr.projectz.projapi.storage;

import ch.wesr.projectz.projapi.domain.Project;
import lombok.extern.slf4j.Slf4j;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Component
public class DataRepository {

    @Autowired
    private DataRoot dataRoot;

    @Autowired
    private EmbeddedStorageManager storageManager;

    public void addProject(Project project) {
        dataRoot.getProjectList().add(project);
        storageManager.storeAll(dataRoot.getProjectList());
    }

    public List<Project> getProjectList() {
        return dataRoot.getProjectList();
    }

    @PreDestroy
    public void onDestroy() throws Exception {
        storageManager.shutdown();
        log.info("Spring Container is destroyed!");
    }
}
