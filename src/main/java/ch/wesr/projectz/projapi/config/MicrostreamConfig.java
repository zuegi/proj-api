package ch.wesr.projectz.projapi.config;


import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.User;
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
            dataRoot.setLocation(location);
            dataRoot.setContent("Avatar");
            storageManager.storeRoot();
        }

        return storageManager;
    }

    @Bean
    public DataRoot dataRoot() {
        return (DataRoot) storageManager().root();
    }
}

