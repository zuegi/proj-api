package ch.wesr.projectz.projapi.feature.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String userId;
    private String name;
    private String lastName;

}
