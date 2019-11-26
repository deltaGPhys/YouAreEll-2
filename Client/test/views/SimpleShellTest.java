package views;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SimpleShellTest {

    private SimpleShell shell;

    @Before
    public void setUp() throws Exception {
        shell = new SimpleShell();
    }

    @Test
    public void collapseMessageInList() {
        ArrayList<String> commands = new ArrayList<String>(Arrays.asList("send", "'letter", "home'", "to", "gramps"));
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("send", "letter home", "to", "gramps"));
        Assert.assertArrayEquals(expected.toArray(new String[0]), shell.collapseMessageInList(commands).toArray(new String[0]));
    }

    @Test
    public void collapseMessageInList2() {
        ArrayList<String> commands = new ArrayList<String>(Arrays.asList("send", "'letter", "home'"));
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("send", "letter home"));
        Assert.assertArrayEquals(expected.toArray(new String[0]), shell.collapseMessageInList(commands).toArray(new String[0]));
    }

    @Test
    public void collapseMessageInList3() {
        ArrayList<String> commands = new ArrayList<String>(Arrays.asList("send", "'","letter", "home", "'", "to", "gramps"));
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("send", "letter home", "to", "gramps"));
        Assert.assertArrayEquals(expected.toArray(new String[0]), shell.collapseMessageInList(commands).toArray(new String[0]));
    }

    @Test
    public void collapseMessageInList4() {
        ArrayList<String> commands = new ArrayList<String>(Arrays.asList("send", "'letter'", "to", "gramps"));
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("send", "letter", "to", "gramps"));
        Assert.assertArrayEquals(expected.toArray(new String[0]), shell.collapseMessageInList(commands).toArray(new String[0]));
    }
}