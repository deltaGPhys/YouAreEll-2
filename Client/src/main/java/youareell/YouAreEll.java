package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.*;
import models.Id;
import views.IdTextView;
import views.MessageTextView;

import java.io.IOException;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;
    private TransactionController transCtrl;

    public YouAreEll () throws JsonProcessingException {
        this.transCtrl = new TransactionController();
        this.idCtrl = new IdController(this.transCtrl);
        this.msgCtrl = new MessageController(this.idCtrl, this.transCtrl);
    }

    public MessageController getMsgCtrl() {
        return msgCtrl;
    }

    public IdController getIdCtrl() {
        return idCtrl;
    }

    public TransactionController getTransCtrl() {
        return transCtrl;
    }

    public String setMyId(String gHName) {
        Id thisId = idCtrl.getIdByGH(gHName);
        if (thisId != null) {
            idCtrl.setMyId(thisId);
            return ("Local ID Set");
        } else {
            return ("User ID Not Found");
        }
    }

    public String view_all_ids() throws JsonProcessingException {
        return idCtrl.printIds(idCtrl.getIds());
    }

    public void putOrPostId(String name, String gHname) throws JsonProcessingException {
        if (idCtrl.getIdByGH(gHname) == null) { // add to remote
            Id id = new Id(name,gHname);
            idCtrl.postId(id);
        } else { // already exists - put instead
            Id modId = idCtrl.getIdByGH(gHname);
            modId.setName(name);
            idCtrl.putId(modId);
        }
    }

    public String view_all_messages() throws JsonProcessingException {
        return msgCtrl.printMessages(msgCtrl.getMessages(20,""));
    }

    public String view_messages_to_user(String githubId) throws JsonProcessingException {
        return msgCtrl.printMessages(msgCtrl.getMessages(20,githubId));
    }

    public String get_id(String gHname) {
        Id idFound = idCtrl.getIdByGH(gHname);
        if (idFound != null) {
            return new IdTextView(idFound).toString();
        } else {
            return "ID not found";
        }
    }

    public String sendMessage(String message) throws IOException {
        idCtrl.getIds();
        if (this.idCtrl.getMyId() == null) {
            return "ID not set: use \"ids setCurrent <id>\"";
        } else {
            return sendMessage(this.idCtrl.getMyId().getGitHubId(), message);
        }
    }

    public String sendMessage(String senderId, String message) throws IOException {
        idCtrl.getIds();
        Id idFound = idCtrl.getIdByGH(senderId);
        if (idFound != null) {
            return new MessageTextView(msgCtrl.postMessage(idFound,message)).toString();
        } else {
            return "Sender ID not found";
        }
    }

    public String sendMessage(String senderId, String recipId, String message) throws IOException {
        idCtrl.getIds();
        Id idFound = null;
        if (!senderId.equals("")) {
            idFound = idCtrl.getIdByGH(senderId);
            if (idFound == null) {
                return "Sender ID not found";
            }
        } else {
            if (this.idCtrl.getMyId() == null) {
                return "ID not set: use \"ids setCurrent <id>\"";
            } else {
                idFound = this.idCtrl.getMyId();
            }
        }
        Id idTo = idCtrl.getIdByGH(recipId);
        if (idTo != null) {
            return new MessageTextView(msgCtrl.postMessage(idFound,idTo,message)).toString();
        } else {
            return "Recipient ID not found";
        }
    }
}
