package ch.wesr.projectz.projapi.config;


import ch.wesr.projectz.projapi.domain.Project;
import ch.wesr.projectz.projapi.domain.User;
import ch.wesr.projectz.projapi.storage.DataRoot;
import lombok.extern.slf4j.Slf4j;
import one.microstream.afs.nio.NioFileSystem;
import one.microstream.reflect.ClassLoaderProvider;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MicrostreamConfig {

    @Value("${microstream.store.location}")
    String location;

    private DataRoot dataRoot;

    @Bean
    public EmbeddedStorageManager storageManager() {

        NioFileSystem fileSystem = NioFileSystem.New();
        EmbeddedStorageManager storageManager = EmbeddedStorage.Foundation(fileSystem.ensureDirectoryPath(location))
                .onConnectionFoundation(cf -> cf.setClassLoaderProvider(ClassLoaderProvider.New(
                        Thread.currentThread().getContextClassLoader())))
                .start();


        if (storageManager.root() == null) {
            log.info("No database found  - creating a new one");
            DataRoot dataRoot = new DataRoot();
            storageManager.setRoot(dataRoot);
            dataRoot.getProjectList().add(new Project("return of the avatar", "Avator", "123d4-sdfaf-adf2K", new User("Ang", "Avatar")));
            dataRoot.getProjectList().add(new Project("return of the jedi", "Jedi", "123d4-jedi-adf2K", new User("Master", "Jedi")));
            dataRoot.setLocation(location);
            dataRoot.setContent("Avatar");
            storageManager.storeRoot();
        } else {
            DataRoot dataRoot = (DataRoot) storageManager.root();
            if (dataRoot != null) {
                log.info("Database found");
                log.info("location: " + dataRoot.getLocation());
                log.info("content: " + dataRoot.getContent());
                dataRoot.printAllMyProjects();
            } else {
                log.info("Where is my database?");
            }
        }
        log.info("storageManager: " + storageManager.toString());
        return storageManager;
    }
}

