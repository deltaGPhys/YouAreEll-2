package controllers;

import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import views.IdTextView;

public class IdController {

    private Id myId;
    private ArrayList<Id> storedIds;
    private TransactionController transactionController;

    public IdController(TransactionController transactionController) throws JsonProcessingException {
        this.storedIds = new ArrayList<Id>();
        this.myId = null;
        this.transactionController = transactionController;
        getIds();
    }

    public ArrayList<Id> getIds() throws JsonProcessingException {
        String result = transactionController.MakeURLCall("/ids", "GET", "");
        ObjectMapper mapper = new ObjectMapper();
        this.storedIds = mapper.readValue(result, new TypeReference<ArrayList<Id>>() {});
        return this.storedIds;
    }

    public String printIds(ArrayList<Id> ids) {
        String output = "";
        for (Id id : ids) {
            output += new IdTextView(id).toString();
        }
        return output;
    }

    public Id getIdByGH(String gitHubId) {
        for (Id i : storedIds) {
            if (i.getGitHubId().equals(gitHubId)) {
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
        String response = transactionController.MakeURLCall("/ids", "POST", jsonData);
        System.out.println("response: "+response);
        getIds();
        return getIdByGH(id.getGitHubId());
    }

    public Id putId(Id id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(id);
        String response = transactionController.MakeURLCall("/ids", "PUT", jsonData);
        System.out.println("response: "+response);
        getIds();
        return getIdByGH(id.getGitHubId());
    }
 
}