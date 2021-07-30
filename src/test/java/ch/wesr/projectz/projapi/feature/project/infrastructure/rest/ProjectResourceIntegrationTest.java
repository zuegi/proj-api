package ch.wesr.projectz.projapi.feature.project.infrastructure.rest;

import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;
import ch.wesr.projectz.projapi.shared.command.Command;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"integrationtest", "local"})
public class ProjectResourceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


    @Test
    void testPostProject() throws Exception {
        CreateProject createProject = new CreateProject(null, "Project A", "Desription of project A", "createProject");
        List<Command> commands = Arrays.asList(createProject);
        MvcResult mvcResult = performPost(commands).andExpect(status().isCreated()).andReturn();

    }

    @Test
    void project_create_is_valid() throws Exception {
        ProjectInfo projectInfo = new ProjectInfo(null, "Project A", "Desription of project A", "usd2835");
        MvcResult mvcResult = performPost(projectInfo).andExpect(status().isAccepted()).andReturn();
        assertThat(mvcResult.getResponse().getHeader(HttpHeaders.LOCATION), is("/api/project"));
        assertNotNull(mvcResult.getResponse().getHeader("projectId"));
    }

    private ResultActions performPost(ProjectInfo projectInfo) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/api/project").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(projectInfo)));
    }

    private ResultActions performPost(List<Command> commands) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/api/project").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(commands)));
    }
}
