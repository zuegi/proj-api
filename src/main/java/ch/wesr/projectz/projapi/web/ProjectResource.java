package ch.wesr.projectz.projapi.web;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.User;
import ch.wesr.projectz.projapi.storage.DataRepository;
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
    private DataRepository repository;


    @GetMapping("/content/{content}")
    @ResponseBody
    public String setContent(@PathVariable String content) {
        repository.addContent(content);
       return repository.getContent();

    }

    @GetMapping("/content")
    @ResponseBody
    public String getContent() {
        return repository.getContent();

    }

    @GetMapping("/createSingle")
    @ResponseBody
    public List<Project> createSingleProject() {
//        Project project = new Project("projectz", "heimliches Projekt", "2da3-adf2K-12KT", new User("René", "Weishaupt"));
//        repository.addProject(project);
        return repository.getProjectList();
    }



    @GetMapping("/all")
    @ResponseBody
    public List<Project> getAllProjects() {
        List<Project> projectList = repository.getProjectList();
        projectList.stream().forEach(p -> log.info(p.toString()));
        return projectList;
    }



}
