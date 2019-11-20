package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import views.MessageTextView;
import youareell.YouAreEll;

public class MessageController {

    private HashSet<Message> messagesSeen;
    // why a HashSet?? ...so that we don't store messages multiple times
    private static final MessageController INSTANCE = new MessageController();

    private MessageController() {
        this.messagesSeen = new HashSet<Message>();
    }

    public static MessageController getInstance() {
        return INSTANCE;
    }

    public ArrayList<Message> getMessages() throws JsonProcessingException {
        String result = YouAreEll.getInstance().MakeURLCall("/messages", "GET", "");
        ObjectMapper mapper = new ObjectMapper();
        Message[] messages = mapper.readValue(result,Message[].class);
        for (Message message: messages) {
            this.messagesSeen.add(message);
        }
        return new ArrayList<Message>(this.messagesSeen);
    }

    public void printMessages(ArrayList<Message> messages) {
        for (Message message: messages) {
            System.out.println(new MessageTextView(message).toString());
        }
    }

    public ArrayList<Message> getMessagesForId(Id id) {
        ArrayList<Message> messagesFound = new ArrayList<>();
        for (Message m : messagesSeen) {
            if (m.getToid().equals(id)) {
                messagesFound.add(m);
            }
        }
        return messagesFound;
    }
    public Message getMessageForSequence(String seq) {
        for (Message m : messagesSeen) {
            if (m.getSequence().equals(seq)) {
                return m;
            }
        }
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        ArrayList<Message> messagesFound = new ArrayList<>();
        for (Message m : messagesSeen) {
            if (m.getToid().equals(myId) && m.getFromid().equals(friendId)) {
                messagesFound.add(m);
            }
        }
        return messagesFound;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}