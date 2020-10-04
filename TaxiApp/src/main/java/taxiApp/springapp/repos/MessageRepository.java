package taxiApp.springapp.repos;

import taxiApp.core.Message;
import org.springframework.data.repository.CrudRepository;
import taxiApp.core.User;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findBySender(User user);
}
