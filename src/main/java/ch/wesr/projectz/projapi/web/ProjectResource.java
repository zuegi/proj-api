package ch.wesr.projectz.projapi.web;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/projects")
public class ProjectResource {

    @Autowired
    private ProjectRepository repository;


    @GetMapping("/content/{content}")
    @ResponseBody
    public String setContent(@PathVariable String content) {
        return null;
    }

    @GetMapping("/content")
    @ResponseBody
    public String getContent() {
      return null;
    }

    @GetMapping("/createSingle")
    @ResponseBody
    public List<Project> createSingleProject() {
//
        return null;
    }



    @GetMapping("/all")
    @ResponseBody
    public List<Project> getAllProjects() {
      return null;
    }



}
