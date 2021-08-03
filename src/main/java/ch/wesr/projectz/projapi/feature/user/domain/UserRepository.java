package ch.wesr.projectz.projapi.feature.user.domain;

import java.util.List;

public interface UserRepository {

    void add(User user);

    User get(String userId);

    List<User> findAll();

}
