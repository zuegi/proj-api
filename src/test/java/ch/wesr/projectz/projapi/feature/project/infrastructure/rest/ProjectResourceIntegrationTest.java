package ch.wesr.projectz.projapi.feature.project.infrastructure.rest;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.query.ProjectUI;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void project_create_is_valid() throws Exception {
        // create project
        ProjectInfo projectInfo = new ProjectInfo(null, "Project A", "Desription of project A", "rw170669");
        MvcResult mvcResult = performPost(projectInfo).andExpect(status().isAccepted()).andReturn();
        assertThat(mvcResult.getResponse().getHeader(HttpHeaders.LOCATION), is("/api/project"));
        String projectId = mvcResult.getResponse().getHeader("projectId");
        assertNotNull(projectId);
        // get the created project
        MvcResult mvcGetResult = performGet(projectId).andExpect(status().isOk()).andReturn();
        ProjectUI projectUI = objectMapper.readValue(mvcGetResult.getResponse().getContentAsString(), ProjectUI.class);
        assertThat(projectUI.getProjectId(), is(projectId));

    }

    private ResultActions performPost(ProjectInfo projectInfo) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/api/project").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(projectInfo)));
    }

    private ResultActions performGet(String projectId) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/api/project/" + projectId).contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}
