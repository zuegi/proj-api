package ch.wesr.projectz.projapi.feature.user.service;

import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.ChangeProjectOwnerFound;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.ChangeProjectOwnerNotFound;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.UserFound;
import ch.wesr.projectz.projapi.feature.user.infrastructure.event.UserNotFound;
import ch.wesr.projectz.projapi.feature.user.infrastructure.repository.InMemoryUserRepository;
import ch.wesr.projectz.projapi.shared.eventbus.ProjectEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ProjectEventPublisher projectEventPublisher;

    @Autowired
    private InMemoryUserRepository userRepository;

    public void publishUserById(String projectId, String userId) {

        User user = userRepository.get(userId);
        if (user != null) {
            projectEventPublisher.publish(new UserFound(projectId, user));
        } else {
            projectEventPublisher.publish(new UserNotFound(projectId, userId));

        }
    }

    public void publishChangeProjectOwnerById(String projectId, String userId) {
        User user = userRepository.get(userId);
        if (user != null) {
            projectEventPublisher.publish(new ChangeProjectOwnerFound(projectId, user));
        } else {
            projectEventPublisher.publish(new ChangeProjectOwnerNotFound(projectId, userId));

        }
    }
}
