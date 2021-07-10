package ch.wesr.projectz.projapi.feature.project.domain.command;

import ch.wesr.projectz.projapi.feature.project.domain.User;
import ch.wesr.projectz.projapi.shared.command.Command;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProjectOwner implements Command {
    String userId;

    @JsonIgnore
    User projectOwner;

    public AddProjectOwner(String userId) {
        this.userId = userId;
    }
}
