package ch.wesr.projectz.projapi.shared.persistence;

import ch.wesr.projectz.projapi.feature.project.domain.Project;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Data
public class DataRoot {
    private List<Project> vertraulichkeitsbereiche  = new ArrayList<>();
}
