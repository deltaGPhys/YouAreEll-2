package views;

import models.Id;

import java.util.ArrayList;

public class IdTextView {

    private Id idToDisplay;

    public IdTextView(Id idToDisplay) {
        this.idToDisplay = idToDisplay;
    }

    @Override public String toString() {
        return String.format("Name: %s\nGitHub ID: %s\n",idToDisplay.getName(),idToDisplay.getGitHubId());
    }

    public static String printIds(ArrayList<Id> ids) {
        String output = "";
        for (Id id : ids) {
            output += new IdTextView(id).toString();
        }
        return output;
    }
}