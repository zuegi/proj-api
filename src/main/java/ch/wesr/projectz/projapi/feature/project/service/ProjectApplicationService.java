package ch.wesr.projectz.projapi.feature.project.service;


import ch.wesr.projectz.projapi.feature.project.domain.Project;
import ch.wesr.projectz.projapi.feature.project.domain.ProjectCommitId;
import ch.wesr.projectz.projapi.feature.project.domain.command.ProjectCommandHandler;
import ch.wesr.projectz.projapi.shared.command.Command;
import ch.wesr.projectz.projapi.shared.command.InMemoryCommandDispatcher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@Service
public class ProjectApplicationService {

    InMemoryCommandDispatcher dispatcher;
    ProjectCommandEnricher enricher;

    public Project execute(List<Command> commands) {

        commands.stream().forEach(command -> {

            enricher.enrich(command);
            dispatcher.registerHandler(command.getClass(), new ProjectCommandHandler());
           // und wie mache ich hier, sodass ich ein Project zurückgeben kann??
        });

        return null;
    }

}
