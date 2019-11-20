package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.*;
import models.Id;
import models.Message;
import views.IdTextView;
import views.MessageTextView;

import java.io.IOException;
import java.util.ArrayList;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;
    private static final YouAreEll INSTANCE = new YouAreEll(MessageController.getInstance(), IdController.getInstance());

    private YouAreEll (MessageController m, IdController j) {
        // used j because i seems awkward
        this.msgCtrl = m;
        this.idCtrl = j;
    }

    public static void main(String[] args) throws JsonProcessingException {
        YouAreEll urlhandler = YouAreEll.getInstance();

        ArrayList<Id> ids = urlhandler.idCtrl.getIds();
        for (Id id: ids) {
            System.out.println(new IdTextView(id).toString());
        }
        ArrayList<Message> messages = urlhandler.msgCtrl.getMessages();
        for (Message message: messages) {
            System.out.println(new MessageTextView(message).toString());
        }
    }

    public static YouAreEll getInstance() {
        return INSTANCE;
    }

    public void view_all_ids() throws JsonProcessingException {
        idCtrl.printMessages(idCtrl.getIds());
    }

    public void view_all_messages() throws JsonProcessingException {
        msgCtrl.printMessages(msgCtrl.getMessages());
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        try {
            switch (method) {
                case "GET":
                    System.out.println("********************" + mainurl + "********************");
                    return TransactionController.getInstance().get(mainurl);
            }
        } catch (IOException e) {
            return e.getMessage();
        }
        return "Nothing returned";
    }
}
