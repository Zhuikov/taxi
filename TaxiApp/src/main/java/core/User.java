package core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

abstract public class User extends TaxiItem {

    protected final String login;
    protected final PersonInfo personInfo;
    protected final UserRole role;
    protected boolean online = false;
    protected List<Message> messages = new ArrayList<>();
    protected Consumer<Message> messageProcessFunction =
            message -> {
                String text = "Message " + message.getType() + " from " + message.getSender();
                System.out.println(text);
    };

    public User(int id, String login, PersonInfo personInfo, UserRole role) {
        super(id);
        this.login = login;
        this.personInfo = personInfo;
        this.role = role;
    }

    public void setMessageProcessFunction(Consumer<Message> messageProcessFunction) {
        this.messageProcessFunction = messageProcessFunction;
    }

    public String getLogin() {
        return login;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void addMessage(Message message) {
        assert (message.getRecipientId() == id);
        messages.add(message);
        messageProcessFunction.accept(message);
    }

    @Override
    public String toString() {
        return role.toString() + " " + personInfo.toString();
    }
}
