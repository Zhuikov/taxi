package taxiApp.core;

import javax.persistence.*;

@Entity
@Table(name = "Messages")
public class Message extends TaxiItem {

    @ManyToOne
    @JoinColumn(name="id_sender", nullable=false)
    private final User sender;
    @ManyToOne
    @JoinColumn(name="id_recipient", nullable=false)
    private final User recipient;
    @Enumerated
    @Column(name = "type", columnDefinition = "smallint")
    private final MessageType type;
    private final Long payload;

    public Message(User sender, User recipient, MessageType type, Long payload) {
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.payload = payload;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public MessageType getType() {
        return type;
    }

    public Long getPayload() {
        return payload;
    }

}
