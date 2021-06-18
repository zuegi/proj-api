package ch.wesr.projectz.projapi.storage;

import ch.wesr.projectz.projapi.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataRoot {

    private List<Project> projectList = new ArrayList<>();
    private String content;
    private String location;

    public void printAllMyProjects() {
        log.info("Printing all my projects:");
        this.getProjectList().forEach(p -> log.info("\t" +p.toString()));
    }
}
