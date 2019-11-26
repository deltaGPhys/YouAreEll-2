package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import views.MessageTextView;
import youareell.YouAreEll;

public class MessageController {

    private HashSet<Message> messagesSeen;
    private ArrayList<Message> allMessages;
    private IdController idController;
    private TransactionController transactionController;

    public MessageController(IdController idController, TransactionController transactionController) {
        this.messagesSeen = new HashSet<Message>();
        this.idController = idController;
        this.transactionController = transactionController;
        this.allMessages = new ArrayList<>();
    }

    public ArrayList<Message> getMessages() throws JsonProcessingException {
        return getMessages(-1, ""); // for all msgs
    }

    public ArrayList<Message> getMessages(String gitHubId) throws JsonProcessingException {
        return getMessages(-1,gitHubId);
    }

    public ArrayList<Message> getMessages(int numMsgs, String githubId) throws JsonProcessingException {
        getAllMessages();

        Id toId = null;
        if (!githubId.equals("")) {
            toId = idController.getIdByGH(githubId);
            if (toId == null) {
                return new ArrayList<Message>();
            }
        }

        ArrayList<Message> results = new ArrayList<>();

        for (Message message : this.allMessages) {
            if (githubId.equals("") || toId.getGitHubId().equals(message.getToid())) {
                results.add(message);
            }
        }
        Collections.sort(results);

        if (numMsgs > 0) {
            return new ArrayList<Message>(results.subList(0, Math.min(results.size(),numMsgs)));
        } else {
            return results;
        }
    }

    public void getAllMessages() throws JsonProcessingException {
        idController.getIds();
        this.allMessages.clear();

        String result = transactionController.MakeURLCall("/messages", "GET", "");
        ObjectMapper mapper = new ObjectMapper();
        Message[] messages = mapper.readValue(result,Message[].class);

        for (Message message : messages) {
            this.allMessages.add(message);
        }
    }

    public void printMessages(ArrayList<Message> messages) {
        for (Message message: messages) {
            System.out.println(new MessageTextView(message).toString());
        }
    }

    public ArrayList<Message> getMessagesForId(Id id) {
        ArrayList<Message> messagesFound = new ArrayList<>();
        for (Message m : this.allMessages) {
            if (m.getToid().equals(id)) {
                messagesFound.add(m);
            }
        }
        return messagesFound;
    }
    public Message getMessageForSequence(String seq) {
        for (Message m : this.allMessages) {
            if (m.getSequence().equals(seq)) {
                return m;
            }
        }
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        ArrayList<Message> messagesFound = new ArrayList<>();
        for (Message m : this.allMessages) {
            if (m.getToid().equals(myId) && m.getFromid().equals(friendId)) {
                messagesFound.add(m);
            }
        }
        return messagesFound;
    }

    public Message postMessage(Id myId, Id toId, String msg) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String to = (toId != null) ? toId.getGitHubId() : "";
        String payload = mapper.writeValueAsString(new Message(myId.getGitHubId(),to,msg));

        String result = transactionController.post(String.format("/ids/%s/messages",myId.getGitHubId()),payload);
        System.out.println(result);
        return mapper.readValue(result,Message.class);
    }

    public Message postMessage(Id myId, String msg) throws IOException {
        return postMessage(myId, null, msg);
    }
 
}