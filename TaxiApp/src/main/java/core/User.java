package core;

import repository.MessageRepository;

import java.util.List;
import java.util.function.Consumer;

abstract public class User extends TaxiItem {

    protected final String login;
    protected final PersonInfo personInfo;
    protected final UserRole role;
    protected final MessageRepository messageRepository = MessageRepository.getSingleton();
    protected Consumer<Message> messageProcessFunction =
            message -> {
                String text = "Message " + message.getType() + " from " + message.getSenderRole();
                System.out.println(text);
                message.setRead(true);
    };

    public User(int id, String login, PersonInfo personInfo, UserRole role) {
        super(id);
        this.login = login;
        this.personInfo = personInfo;
        this.role = role;
    }

    /**
     * Returns all 'withUnread' messages for user using user.id
     */
    public List<Message> getMessagesByUser(boolean withUnread) {
        return messageRepository.getUserMessages(id, role, withUnread);
    }

    /**
     * Returns all 'isRead' messages for user using user.role
     */
    public List<Message> getMessagesByRole(boolean isRead) {
        return messageRepository.getRoleMessages(role, isRead);
    }

    public String getLogin() {
        return login;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void processMessage(Message message) {
        messageProcessFunction.accept(message);
    }

    public void setMessageProcessFunction(Consumer<Message> messageProcessFunction) {
        this.messageProcessFunction = messageProcessFunction;
    }

    @Override
    public String toString() {
        return role.toString() + " " + personInfo.toString();
    }
}
