package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {

    private Message message1;
    private Message message2;

    @Before
    public void setUp() throws Exception {
        message1 = new Message("jimbo", "", "hello there people");
        message2 = new Message("jimbo2", "jimbo", "up with purple");
    }

    @Test
    public void getMessage() {
        Assert.assertEquals("hello there people",message1.getMessage());
    }

    @Test
    public void getFromid() {
        Assert.assertEquals("jimbo2",message2.getFromid());
    }

    @Test
    public void getToid() {
        Assert.assertEquals("jimbo",message2.getToid());
        Assert.assertEquals("",message1.getToid());
    }

    @Test
    public void getSequence() {
        Assert.assertEquals("-",message1.getSequence());
    }

    @Test
    public void getTimestamp() {
        Assert.assertEquals(null,message2.getTimestamp());
    }

    @Test
    public void testToString() {
        String expected = "Message{sequence='-', timestamp=null, fromid='jimbo2', toid='jimbo', message='up with purple'}";
        Assert.assertEquals(expected, message2.toString());
    }

    @Test
    public void compareTo() {
    }
}