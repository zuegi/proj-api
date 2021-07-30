package ch.wesr.projectz.projapi.feature.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String userId;
    private String name;
    private String lastName;

}
