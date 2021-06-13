package ch.wesr.projectz.projapi.config;


import ch.wesr.projectz.projapi.domain.Project;
import ch.wesr.projectz.projapi.domain.User;
import ch.wesr.projectz.projapi.storage.DataRoot;
import lombok.extern.slf4j.Slf4j;
import one.microstream.afs.nio.NioFileSystem;
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

//        EmbeddedStorageManager storageManager = EmbeddedStorage.Foundation(Paths.get(location))
//                .onConnectionFoundation(cf ->
//                        cf.setClassLoaderProvider(ClassLoaderProvider.New(
//                                Thread.currentThread().getContextClassLoader()
//                        ))
//                )
//                .start();

        NioFileSystem fileSystem = NioFileSystem.New();
        EmbeddedStorageManager storageManager = EmbeddedStorage.start(fileSystem.ensureDirectoryPath(location));
        if (storageManager.root() == null) {
            log.info("No database found  - creating a new one");
            dataRoot = new DataRoot();
            dataRoot.getProjectList().add(new Project("return of the avatar", "Avator", "123d4-sdfaf-adf2K", new User("Ang", "Avatar")));
            storageManager.setRoot(dataRoot);
            dataRoot.setLocation(location);
            storageManager.storeRoot();
        } else {
            log.info("Database found");
            dataRoot = (DataRoot) storageManager.root();
            System.out.println("location: " +dataRoot.getLocation());
            System.out.println("content: " +dataRoot.getContent());
            dataRoot.printAllMyProjects();
        }

        return storageManager;
    }

    @Bean
    DataRoot dataRoot() {
        return dataRoot;
    }
}

