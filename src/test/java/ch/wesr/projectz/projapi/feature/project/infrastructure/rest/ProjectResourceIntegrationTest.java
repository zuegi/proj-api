package ch.wesr.projectz.projapi.feature.project.infrastructure.rest;

import ch.wesr.projectz.projapi.AbstractIntegrationTest;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.PlaceProject;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.PlaceProjectMembers;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.PlaceProjectOwner;
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
        PlaceProject placeProject = new PlaceProject(null, "Project A", "Desription of project A", new PlaceProjectOwner(UserTestHelper.PROJECT_MEMBER_EPIFANIO.getUserId()), new PlaceProjectMembers(null));
        MvcResult mvcResult = performPost(placeProject).andExpect(status().isAccepted()).andReturn();
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
        PlaceProject placeProject = new PlaceProject(null, "Project A", "Desription of project A", new PlaceProjectOwner("zz120198"), new PlaceProjectMembers(null));
        MvcResult mvcResult = performPost(placeProject).andExpect(status().isAccepted()).andReturn();
        assertThat(mvcResult.getResponse().getHeader(HttpHeaders.LOCATION), is("/api/project"));
        String projectId = mvcResult.getResponse().getHeader("projectId");
        assertNotNull(projectId);
        // no project found
        performGet(projectId).andExpect(status().isNotFound());

    }

    @Test
    void project_change_project_owner_valid() throws Exception {
        PlaceProject placeProject = new PlaceProject(null, "Project A", "Desription of project A", new PlaceProjectOwner(UserTestHelper.PROJECT_LEADER.getUserId()), new PlaceProjectMembers(null));
        MvcResult mvcResult = performPost(placeProject).andExpect(status().isAccepted()).andReturn();
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

    private ResultActions performPost(PlaceProject placeProject) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/api/project").
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(placeProject)));
    }

    private ResultActions performGet(String projectId) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get("/api/project/" + projectId).contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}
