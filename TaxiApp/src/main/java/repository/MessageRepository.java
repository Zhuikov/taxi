package repository;

import core.Message;
import core.UserRole;

import java.util.List;
import java.util.stream.Collectors;

// singleton
public class MessageRepository extends TaxiItemRepository<Message>{

    private static MessageRepository messageRepository = null;
    private MessageRepository() {}

    public static MessageRepository getSingleton() {
        if (messageRepository == null)
            messageRepository = new MessageRepository();
        return messageRepository;
    }

    public List<Message> getUserMessages(int userId, boolean isRead) {
        return entities
                .stream()
                .filter(message -> message.getRecipientId() == userId && message.isRead() == isRead)
                .collect(Collectors.toList());
    }

    public List<Message> getRoleMessages(UserRole role, boolean isRead) {
        return entities
                .stream()
                .filter(message ->  message.getRecipientRole() == role && message.isRead() == isRead && message.getRecipientId() == null)
                .collect(Collectors.toList());
    }

//    @Override
//    public Message getById(int id) {
//        return null;
//    }
//
//    @Override
//    public void addNew(Message item) {
//        entities.add(item);
//        // todo go to DB
//    }
//
//    @Override
//    public int getUnusedId() {
//        return 0;
//    }
}
