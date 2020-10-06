package taxiApp.springapp.services.representations;

import taxiApp.core.Message;

public class MessageRepresentation implements Comparable<MessageRepresentation> {
    private final String from;
    private final String to;
    private final String type;
    private final Long payload;
    private final Long id;

    public MessageRepresentation(Message message) {
        from = message.getSender().getPersonInfo().toString();
        to = message.getRecipient().getPersonInfo().toString();
        type = message.getType().toString();
        payload = message.getPayload();
        id = message.getId();
    }

    public String getFrom() {
        return from;
    }

    public String getType() {
        return type;
    }

    public Long getPayload() {
        return payload;
    }

    public String getTo() {
        return to;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(MessageRepresentation o) {
        return this.id.compareTo(o.getId());
    }

}
