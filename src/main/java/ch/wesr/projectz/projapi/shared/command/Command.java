package ch.wesr.projectz.projapi.shared.command;

import ch.wesr.projectz.projapi.feature.project.domain.command.AddProjectOwner;
import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateProject.class, name = "createProject"),
        @JsonSubTypes.Type(value = AddProjectOwner.class, name = "addProjectOwner")
})
public interface Command {

    String getCommandId();
}
