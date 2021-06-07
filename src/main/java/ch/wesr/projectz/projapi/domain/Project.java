package ch.wesr.projectz.projapi.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private String name;
    private String description;
    private String identifier;
    private User projectOwner;

}
