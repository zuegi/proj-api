package ch.wesr.projectz.projapi.feature.user.infrastructure.repository;

import ch.wesr.projectz.projapi.feature.user.domain.User;
import ch.wesr.projectz.projapi.feature.user.domain.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> userStore = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        List<User> users = Arrays.asList(
                new User("sk160670", "Salvatore", "Kreambole"),
                new User("ap120398", "Anna", "Pinna"),
                new User("kk180958", "Karl", "Kloeti"),
                new User("ev270766", "Epifanio", "Vargas")
                );

        users.stream().forEach(this::add);
    }

    @Override
    public void add(User user) {
        userStore.putIfAbsent(user.getUserId(), user);
    }

    @Override
    public User get(String userId) {
        return userStore.get(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }
}
