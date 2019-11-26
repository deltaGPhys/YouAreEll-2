package views;

import models.Id;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdTextViewTest {

    @Test
    public void testToString() {
        Id id = new Id("1234","jimjam","jimbo");

        String expected = "Name: jimjam\n" +
                "GitHub ID: jimbo\n";
        Assert.assertEquals(expected, new IdTextView(id).toString());
    }
}