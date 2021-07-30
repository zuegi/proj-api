package ch.wesr.projectz.projapi.feature.user.service;

import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.shared.eventbus.ProjectEventPublisher;
import ch.wesr.projectz.projapi.shared.eventbus.event.UserFound;
import ch.wesr.projectz.projapi.shared.eventbus.event.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ProjectEventPublisher projectEventPublisher;

    public void publishUserById(String projectId, String userId) {
        // TODO Replace hard coded usd2835 with repo call
        if (userId.equals("usd2835")) {
            User usd2835 = new User("usd2835", "Max", "Smith");
            projectEventPublisher.publish(new UserFound(projectId, usd2835));
        } else {
            projectEventPublisher.publish(new UserNotFound(userId));

        }
    }
}
