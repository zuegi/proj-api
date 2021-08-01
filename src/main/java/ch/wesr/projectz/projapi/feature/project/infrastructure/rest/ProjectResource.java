package ch.wesr.projectz.projapi.feature.project.infrastructure.rest;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectId;
import ch.wesr.projectz.projapi.feature.project.domain.query.ProjectUI;
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
    public ResponseEntity placeProject(@RequestBody ProjectInfo projectInfo) {

        ProjectId projectId = ProjectId.generate();

        projectInfo.setProjectId(projectId.getId());
        commandService.placeProject(projectInfo);
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
}
