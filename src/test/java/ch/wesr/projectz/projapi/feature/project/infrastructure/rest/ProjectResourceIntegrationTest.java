package ch.wesr.projectz.projapi.feature.project.infrastructure.rest;

import ch.wesr.projectz.projapi.AbstractIntegrationTest;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.ProjectInfo;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.ProjectMembersInfo;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.ProjectOwnerInfo;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.query.ProjectOwnerUi;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.query.ProjectUI;
import ch.wesr.projectz.projapi.feature.user.UserTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ProjectResourceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    void project_create_valid_user() throws Exception {
        // create project
        ProjectInfo projectInfo = new ProjectInfo(null, "Project A", "Desription of project A", new ProjectOwnerInfo(UserTestHelper.PROJECT_MEMBER_EPIFANIO.getUserId()), new ProjectMembersInfo(null));
        MvcResult mvcResult = performPost(projectInfo).andExpect(status().isAccepted()).andReturn();
        assertThat(mvcResult.getResponse().getHeader(HttpHeaders.LOCATION), is("/api/project"));
        String projectId = mvcResult.getResponse().getHeader("projectId");
        assertNotNull(projectId);
        // get the created project
        MvcResult mvcGetResult = performGet(projectId).andExpect(status().isOk()).andReturn();
        ProjectUI projectUI = objectMapper.readValue(mvcGetResult.getResponse().getContentAsString(), ProjectUI.class);
        assertThat(projectUI.getProjectId(), is(projectId));

    }

    @Test
    void project_create_invalid_user() throws Exception {
        // create project
        ProjectInfo projectInfo = new ProjectInfo(null, "Project A", "Desription of project A", new ProjectOwnerInfo("zz120198"), new ProjectMembersInfo(null));
        MvcResult mvcResult = performPost(projectInfo).andExpect(status().isAccepted()).andReturn();
        assertThat(mvcResult.getResponse().getHeader(HttpHeaders.LOCATION), is("/api/project"));
        String projectId = mvcResult.getResponse().getHeader("projectId");
        assertNotNull(projectId);
        // no project found
        performGet(projectId).andExpect(status().isNotFound());

    }

    @Test
    void project_change_project_owner_valid() throws Exception {
        ProjectInfo projectInfo = new ProjectInfo(null, "Project A", "Desription of project A", new ProjectOwnerInfo(UserTestHelper.PROJECT_LEADER.getUserId()), new ProjectMembersInfo(null));
        MvcResult mvcResult = performPost(projectInfo).andExpect(status().isAccepted()).andReturn();
        assertThat(mvcResult.getResponse().getHeader(HttpHeaders.LOCATION), is("/api/project"));
        String projectId = mvcResult.getResponse().getHeader("projectId");
        assertNotNull(projectId);

        // get the created project
        MvcResult mvcGetResult = performGet(projectId).andExpect(status().isOk()).andReturn();
        ProjectUI projectUI = objectMapper.readValue(mvcGetResult.getResponse().getContentAsString(), ProjectUI.class);
        assertThat(projectUI.getProjectId(), is(projectId));
        assertThat(projectUI.getProjectOwnerUi().getLastName(), is(UserTestHelper.PROJECT_LEADER.getLastName()));

        // change owner
        ProjectOwnerUi projectOwnerUi = new ProjectOwnerUi(UserTestHelper.PROJECT_MEMBER_ANNA.getUserId(), UserTestHelper.PROJECT_MEMBER_ANNA.getName(), UserTestHelper.PROJECT_MEMBER_ANNA.getLastName());
        mockMvc.perform(MockMvcRequestBuilders.put("/api/project/" +projectId +"/owner/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(projectOwnerUi)));

        // get Project and expect owner to be changed
        mvcGetResult = performGet(projectId).andExpect(status().isOk()).andReturn();
        ProjectUI changedProjectUI = objectMapper.readValue(mvcGetResult.getResponse().getContentAsString(), ProjectUI.class);
        assertThat(changedProjectUI.getProjectId(), is(projectId));
        assertThat(changedProjectUI.getProjectOwnerUi().getLastName(), is(UserTestHelper.PROJECT_MEMBER_ANNA.getLastName()));


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
