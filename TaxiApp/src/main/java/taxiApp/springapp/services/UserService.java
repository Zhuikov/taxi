package taxiApp.springapp.services;

import taxiApp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.core.User;
import taxiApp.springapp.repos.MessageRepository;

import java.util.List;

@Service
public class UserService {

    protected final MessageRepository messageRepository;

    public UserService(@Autowired MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(User user) {
        return messageRepository.findBySender(user);
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }
}
