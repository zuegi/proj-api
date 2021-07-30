package ch.wesr.projectz.projapi.feature.project.service;

import ch.wesr.projectz.projapi.feature.project.domain.command.CreateProject;

public interface EventProducer {
    void publish(CreateProject createProject);
}
