package ch.wesr.projectz.projapi;

import lombok.extern.slf4j.Slf4j;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.junit.AfterClass;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.walk;
import static java.util.Collections.reverseOrder;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"integrationtest"})
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
@Import(MicrostreamTestConfig.class)
@Slf4j
@DirtiesContext
public class AbstractIntegrationTest {

}
