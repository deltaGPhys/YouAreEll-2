package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdTest {

    private Id idnull;
    private Id idbefore;
    private Id idafter;

    @Before
    public void setUp() throws Exception {
        idnull = new Id();
        idbefore = new Id("beth","l33t");
        idafter = new Id("123456", "joan", "s0m3rs");
    }

    @Test
    public void getName() {
        Assert.assertEquals(null, idnull.getName());
        Assert.assertEquals("beth", idbefore.getName());
        Assert.assertEquals("joan", idafter.getName());
    }

    @Test
    public void setName() {
        idnull.setName("fred");
        Assert.assertEquals("fred", idnull.getName());
        idbefore.setName("freddy");
        Assert.assertEquals("freddy", idbefore.getName());
        idafter.setName("fredo");
        Assert.assertEquals("fredo", idafter.getName());
    }

    @Test
    public void getGitHubId() {
        Assert.assertEquals(null, idnull.getGitHubId());
        Assert.assertEquals("l33t", idbefore.getGitHubId());
        Assert.assertEquals("s0m3rs", idafter.getGitHubId());
    }

    @Test
    public void setGitHubId() {
        idnull.setGitHubId("rope");
        Assert.assertEquals("rope", idnull.getGitHubId());
        idbefore.setGitHubId("tope");
        Assert.assertEquals("tope", idbefore.getGitHubId());
    }

    @Test
    public void getUserId() {
        Assert.assertEquals(null, idnull.getUserId());
        Assert.assertEquals("-", idbefore.getUserId());
        Assert.assertEquals("123456", idafter.getUserId());
    }

    @Test
    public void setUserId() {
        idnull.setUserId("blargh");
        Assert.assertEquals("blargh", idnull.getUserId());
    }
}