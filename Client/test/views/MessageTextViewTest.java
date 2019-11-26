package views;

import models.Message;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTextViewTest {

    @Test
    public void testToString() {
        Message message = new Message("blinky","pinky","inky is stinky");
        String expected = "From: blinky\n" +
                "To: pinky\n" +
                "null\n" +
                "inky is stinky\n\n";
        Assert.assertEquals(expected,new MessageTextView(message).toString());
    }
}