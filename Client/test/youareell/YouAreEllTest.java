package youareell;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.IdController;
import controllers.MessageController;
import controllers.TransactionController;
import models.Id;
import models.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class YouAreEllTest {


    YouAreEll youAreEll;
    IdController idC;
    MessageController msgC;
    TransactionController transC;

    @Before
    public void setUp() throws Exception {
        youAreEll = new YouAreEll();
        idC = youAreEll.getIdCtrl();
        msgC = youAreEll.getMsgCtrl();
        transC = youAreEll.getTransCtrl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMsgCtrl() {
        Assert.assertNotNull(msgC);
    }

    @Test
    public void getIdCtrl() {
        Assert.assertNotNull(idC);
    }

    @Test
    public void getTransCtrl() {
        Assert.assertNotNull(transC);
    }

    @Test
    public void setMyId() {
        Assert.assertEquals("User ID Not Found",youAreEll.setMyId("kuashjdjsahdcajhsda"));
        Assert.assertEquals("Local ID Set",youAreEll.setMyId("deltaGPhys"));
        Assert.assertEquals("deltaGPhys",idC.getMyId().getGitHubId());
    }

    @Test
    public void view_all_ids() throws JsonProcessingException {
        ArrayList<Id> ids = idC.getIds();
        Assert.assertTrue(ids.size() > 0);
    }

    @Test
    public void view_all_messages() throws JsonProcessingException {
        ArrayList<Message> messages = msgC.getAllMessages();
        Assert.assertTrue(messages.size() > 0);
    }

    @Test
    public void view_messages_to_user() throws JsonProcessingException {
        ArrayList<Message> messages = msgC.getAllMessages();
        Assert.assertTrue(messages.size() > 0);

        ArrayList<Message> messages20 = msgC.getMessages(20,"deltaGPhys");
        Assert.assertTrue(messages20.size() > 0 && messages20.size() <= messages.size());
    }

    @Test
    public void getIdBad() {
        Assert.assertEquals("ID not found", youAreEll.get_id("kjashdkjashd"));
    }

    @Test
    public void sendMessageMyIdNotSet() throws IOException {
        Assert.assertEquals("ID not set: use \"ids setCurrent <id>\"", youAreEll.sendMessage("blarp"));
        Assert.assertEquals("ID not set: use \"ids setCurrent <id>\"", youAreEll.sendMessage("","timmy","blarp"));
    }

    @Test
    public void sendMessageBadID() throws IOException {
        Assert.assertEquals("Sender ID not found",youAreEll.sendMessage("kjashdjkashd","blarp"));
    }

    @Test
    public void sendMessageBadRecipID() throws IOException {
        Assert.assertEquals("Recipient ID not found",youAreEll.sendMessage("deltaGPhys","laksjdlkasjd","blarp"));
    }
}