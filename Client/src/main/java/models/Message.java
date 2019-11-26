package models;

import java.util.Date;

/*
 * POJO for an Message object
 */
public class Message implements Comparable<Message>{
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

    public Message (String fromid, String toId, String message) {
        this.message = message;
        this.fromid = fromid;
        this.toid = toId;
        this.sequence = "-";
        this.timestamp = null;
    }

    public String getMessage() {
        return message;
    }

    public String getFromid() {
        return fromid;
    }

    public String getToid() {
        return toid;
    }

    public String getSequence() {
        return sequence;
    }

    public Date getTimestamp() {
        return timestamp;
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

    public int compareTo(Message message2) {
        return this.timestamp.compareTo(message2.timestamp);
    }
}