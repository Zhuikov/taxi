package taxiApp.springapp.repos.impls;

import taxiApp.core.Message;
import org.springframework.data.repository.NoRepositoryBean;
import taxiApp.core.User;
import taxiApp.springapp.repos.MessageRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoRepositoryBean
public class MessageRepositoryImpl extends CrudRepositoryImpl<Message> implements MessageRepository {
    @Override
    public List<Message> findBySender(User user) {
        return items.stream().
                filter(message -> Objects.equals(message.getSender().getId(), user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> findByRecipient(User user) {
        return items.stream().
                filter(message -> Objects.equals(message.getRecipient().getId(), user.getId()))
                .collect(Collectors.toList());
    }
}
