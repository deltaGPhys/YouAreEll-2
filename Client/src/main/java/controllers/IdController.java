package controllers;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import views.IdTextView;
import views.MessageTextView;
import youareell.YouAreEll;

public class IdController {

    private Id myId;
    private ArrayList<Id> storedIds;
    private static final IdController INSTANCE = new IdController();

    private IdController() {
        this.storedIds = new ArrayList<Id>();
        this.myId = null;

    }

    public static IdController getInstance() {
        return INSTANCE;
    }

    public ArrayList<Id> getIds() throws JsonProcessingException {
        String result = YouAreEll.getInstance().MakeURLCall("/ids", "GET", "");
        ObjectMapper mapper = new ObjectMapper();
        Id[] ids = mapper.readValue(result, Id[].class);
        for (Id id : ids) {
            this.storedIds.add(id);
        }
        return new ArrayList<Id>(this.storedIds);
    }

    public void printMessages(ArrayList<Id> ids) {
        for (Id id : ids) {
            System.out.println(new IdTextView(id).toString());
        }
    }

    public Id getIdByGH(String gitHubId) {
        for (Id i : storedIds) {
            if (i.getGitHubId().equals(gitHubId)) {
                return i;
            }
        }
        return null;
    }

    public Id getIdByName(String name) {
        for (Id i : storedIds) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    public void setMyId(Id myId) {
        this.myId = myId;
    }

    public Id getMyId() {
        return myId;
    }

    public Id postId(Id id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(id);
        String response = YouAreEll.getInstance().MakeURLCall("/ids", "POST", jsonData);
        System.out.println("response: "+response);
        getIds();
        return getIdByGH(id.getGitHubId());
    }

    public Id putId(Id id) {
        for (Id i : storedIds) {
            if (i.getGitHubId().equals(id.getGitHubId())) {
                i.setName(id.getName());
                return i;
            }
        }
        return null;
        // need to put to server
    }
 
}