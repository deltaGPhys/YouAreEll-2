package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import youareell.YouAreEll;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MessageControllerTest {

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
    public void checkIdTest() {
        Assert.assertNull(msgC.checkId("oclq38ebruydhkqixuknye39"));
        Assert.assertNotNull(msgC.checkId("deltaGPhys"));
    }

    @Test
    public void testGetMessagesAllUsers() throws JsonProcessingException {
        ArrayList<Message> messages = msgC.getAllMessages();
        Assert.assertTrue(messages.size() > 0);

        ArrayList<Message> messages20 = msgC.getMessages(20,"");
        Assert.assertTrue(messages20.size() <= 20 && messages20.size() < messages.size());

        ArrayList<Message> messages21 = msgC.getMessages(-1,"");
        Assert.assertTrue(messages21.size() < messages.size());
    }

    @Test
    public void testGetMessagesUserBad() throws JsonProcessingException {
        ArrayList<Message> messages20 = msgC.getMessages(20,"laskjd02oiehwh2");
        Assert.assertTrue(messages20.size() == 0);
    }

    @Test
    public void testGetMessagesUser() throws JsonProcessingException {
        ArrayList<Message> messages = msgC.getAllMessages();

        ArrayList<Message> messages20 = msgC.getMessages(20,"deltaGPhys");
        Assert.assertTrue(messages20.size() > 0 && messages20.size() <= messages.size());
    }

    @Test
    public void getAllMessages() throws JsonProcessingException {
        Assert.assertTrue(msgC.getAllMessages().size() > 0);
    }

    @Test
    public void printMessages() {
        String expected = "From: jimbo\n" +
                "To: jambo\n" +
                "null\n" +
                "how's it going?\n\n" +
                "From: krusty\n" +
                "To: bob\n" +
                "null\n" +
                "you are in jail!\n\n";
        String actual = msgC.printMessages(new ArrayList<Message>(Arrays.asList(
                new Message("jimbo", "jambo", "how's it going?"),
                new Message ("krusty", "bob","you are in jail!")
        )));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMessagesForId() {
    }

    @Test
    public void getMessageForSequence() {
    }

    @Test
    public void getMessagesFromFriend() {
    }

    @Test
    public void postMessage() {
    }

    @Test
    public void testPostMessage() {
    }
}