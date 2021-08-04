package ch.wesr.projectz.projapi.feature.project.infrastructure.rest;

import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.command.PlaceProject;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.query.ProjectOwnerUi;
import ch.wesr.projectz.projapi.feature.project.infrastructure.rest.query.ProjectUI;
import ch.wesr.projectz.projapi.feature.project.service.ProjectCommandService;
import ch.wesr.projectz.projapi.feature.project.service.ProjectQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/project")
public class ProjectResource {

    @Autowired
    ProjectCommandService commandService;

    @Autowired
    ProjectQueryService queryService;

    @PostMapping
    public ResponseEntity placeProject(@RequestBody PlaceProject placeProject) {

        ProjectId projectId = ProjectId.generate();

        placeProject.setProjectId(projectId.getId());
        commandService.placeProject(placeProject);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION, "/api/project");
        responseHeaders.set("projectId", projectId.getId());

        return ResponseEntity.accepted().headers(responseHeaders).build();
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectUI> getProject(@PathVariable String projectId) {
        ProjectUI projectUI = queryService.getProject(projectId);
        return ResponseEntity.ok(projectUI);
    }

    @PutMapping("/{projectId}/owner/")
    public ResponseEntity changeProjectOwner(@PathVariable String projectId, @RequestBody ProjectOwnerUi projectOwnerUi) {

        commandService.changeProjectOwner(projectId, projectOwnerUi);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION, "/api/project");
        responseHeaders.set("projectId", projectId);
        return ResponseEntity.accepted().headers(responseHeaders).build();
    }
}
