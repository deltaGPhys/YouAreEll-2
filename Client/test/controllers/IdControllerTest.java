package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Id;
import models.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import youareell.YouAreEll;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class IdControllerTest {

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
    public void getIds() throws JsonProcessingException {
        ArrayList<Id>ids = idC.getIds();
        Assert.assertTrue(ids.size() > 0);
    }

    @Test
    public void printIds() {
        String expected = "Name: null\n" +
                "GitHub ID: null\n" +
                "Name: john\n" +
                "GitHub ID: johnnyL\n";
        String actual = idC.printIds(new ArrayList<Id>(Arrays.asList(
                new Id (),
                new Id ("john", "johnnyL")
        )));
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getIdByGH() {
    }

    @Test
    public void getIdByName() {
    }

    @Test
    public void setMyId() {
        Id me = new Id();
        idC.setMyId(me);
        Assert.assertEquals(me,idC.getMyId());
    }

}