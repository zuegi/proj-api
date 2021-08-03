package ch.wesr.projectz.projapi;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"integrationtest"})
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
@Import(MicrostreamTestConfig.class)
public class AbstractIntegrationTest {
}
