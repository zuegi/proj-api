package ch.wesr.projectz.projapi.web;

import ch.wesr.projectz.projapi.domain.Project;
import ch.wesr.projectz.projapi.domain.User;
import ch.wesr.projectz.projapi.storage.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProjectResource {

    @Autowired
    private DataRepository repository;


    @GetMapping("/projects/content/{content}")
    @ResponseBody
    public String setContent(@PathVariable String content) {
        repository.addContent(content);
       return repository.getContent();

    }

    @GetMapping("/projects/content")
    @ResponseBody
    public String getContent() {
        return repository.getContent();

    }

    @GetMapping("/projects/createSingle")
    @ResponseBody
    public List<Project> createSingleProject() {
        Project project = new Project("projectz", "heimliches Projekt", "2da3-adf2K-12KT", new User("René", "Weishaupt"));
        repository.addProject(project);
        return repository.getProjectList();
    }



    @GetMapping("/projects/all")
    @ResponseBody
    public List<Project> getAllProjects() {
      return repository.getProjectList();
    }



}
