package ch.wesr.projectz.projapi.shared.eventbus;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StringEvent {
    private String message;

    public StringEvent(String message) {
        this.message = message;
    }
}
