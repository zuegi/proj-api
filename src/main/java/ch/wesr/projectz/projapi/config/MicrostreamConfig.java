package ch.wesr.projectz.projapi.config;


import ch.wesr.projectz.projapi.storage.DataRoot;
import one.microstream.reference.Lazy;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;
import java.util.ArrayList;

@Configuration
public class MicrostreamConfig {

    @Value("${microstream.store.location}")
    String location;

    @Bean
    DataRoot dataRoot() {
        DataRoot dataRoot = new DataRoot();
        dataRoot.setProjectList(new ArrayList<>());
        return dataRoot;
    }

    @Bean
    public EmbeddedStorageManager storageManager() {

        EmbeddedStorageManager storageManager = EmbeddedStorage.start(
                dataRoot(),          // root object
                Paths.get(location) // storage directory
        );
        return storageManager;
    }
}
