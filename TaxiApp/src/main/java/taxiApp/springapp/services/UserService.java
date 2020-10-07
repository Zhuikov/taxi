package taxiApp.springapp.services;

import taxiApp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taxiApp.core.User;
import taxiApp.springapp.repos.MessageRepository;
import taxiApp.springapp.services.representations.MessageRepresentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    protected final MessageRepository messageRepository;

    public UserService(@Autowired MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(User user) {
        List<Message> messages = messageRepository.findBySender(user);
        messages.addAll(messageRepository.findByRecipient(user));
        return messages;
    }

    public List<MessageRepresentation> getMessagesRepr(User user) {
        List <MessageRepresentation> res = new ArrayList<>();
        for (Message m : getMessages(user)) {
            res.add(new MessageRepresentation(m));
        }
        Collections.sort(res);
        return res;
    }
}
