package core;

public class Message {

    private final User sender;
    private final UserRole recipientRole;
    private final Integer recipientId;
    private final MessageType type;
    private final int dataId;
    private boolean processed = false;

    public Message(User sender, UserRole recipientRole, Integer recipientId, MessageType type, int dataId) {
        this.sender = sender;
        this.recipientRole = recipientRole;
        this.recipientId = recipientId;
        this.type = type;
        this.dataId = dataId;
    }

    public User getSender() {
        return sender;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public UserRole getRecipientRole() {
        return recipientRole;
    }

    public MessageType getType() {
        return type;
    }

    public Object getDataId() {
        return dataId;
    }
}
