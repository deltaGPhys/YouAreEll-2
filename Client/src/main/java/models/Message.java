package models;

import java.util.Date;

/*
 * POJO for an Message object
 */
public class Message {
    private String sequence;
    private Date timestamp;
    private String fromid;
    private String toid;
    private String message;

    public Message() {
    }

    public Message (String message, String fromid, String toId, String sequence, Date timestamp) {
        this.message = message;
        this.fromid = fromid;
        this.toid = toId;
        this.sequence = sequence;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sequence='" + sequence + '\'' +
                ", timestamp=" + timestamp +
                ", fromid='" + fromid + '\'' +
                ", toid='" + toid + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}