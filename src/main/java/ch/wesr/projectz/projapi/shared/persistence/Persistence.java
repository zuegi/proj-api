package ch.wesr.projectz.projapi.shared.persistence;

import lombok.AllArgsConstructor;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Persistence {

    private EmbeddedStorageManager storageManager;

    public DataRoot dataRoot() {
        return (DataRoot)storageManager.root();
    }

    public long store(Object obj) {
        return this.storageManager.store(obj);
    }

}
