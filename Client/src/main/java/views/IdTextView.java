package views;

import models.Id;

public class IdTextView {

    private Id idToDisplay;

    public IdTextView(Id idToDisplay) {
        this.idToDisplay = idToDisplay;
    }

    @Override public String toString() {
        return String.format("Name: %s\nGitHub ID: %s\n",idToDisplay.getName(),idToDisplay.getGitHubId());
    } 
}