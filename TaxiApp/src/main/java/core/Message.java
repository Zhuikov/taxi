package core;

public class Message extends TaxiItem {

    private final int senderId;
    private final UserRole senderRole;
    private final Integer recipientId;
    private final UserRole recipientRole;
    private final MessageType type;
    private final int dataId;
    private boolean read = false;

    public Message(int id, int senderId, UserRole senderRole, Integer recipientId,
                   UserRole recipientRole, MessageType type, int dataId) {
//        super(id);
        this.senderId = senderId;
        this.senderRole = senderRole;
        this.recipientRole = recipientRole;
        this.recipientId = recipientId;
        this.type = type;
        this.dataId = dataId;
    }

    public int getSenderId() {
        return senderId;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public UserRole getRecipientRole() {
        return recipientRole;
    }

    public MessageType getType() {
        return type;
    }

    public int getDataId() {
        return dataId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean value) {
        read = value;
    }

    public UserRole getSenderRole() {
        return senderRole;
    }
}
