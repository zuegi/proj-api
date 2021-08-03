package ch.wesr.projectz.projapi.config;


import ch.wesr.projectz.projapi.shared.persistence.DataRoot;
import lombok.extern.slf4j.Slf4j;
import one.microstream.afs.nio.NioFileSystem;
import one.microstream.reflect.ClassLoaderProvider;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("!integrationtest")
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
            storageManager.setRoot(new DataRoot());
            storageManager.storeRoot();
        }

        return storageManager;
    }

}

