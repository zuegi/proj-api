package ch.wesr.projectz.projapi.storage;

import ch.wesr.projectz.projapi.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataRoot {
    private List<Project> projectList;
    private String content;
    private String location;
}
