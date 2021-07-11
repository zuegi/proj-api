package ch.wesr.projectz.projapi.shared.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public interface Command {

    String getCommandId();
}
