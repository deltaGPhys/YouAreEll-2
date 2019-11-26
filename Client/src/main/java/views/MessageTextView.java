package views;

import models.Message;

public class MessageTextView {

    private Message msgToDisplay;

    public MessageTextView(Message msgToDisplay) {
        this.msgToDisplay = msgToDisplay;
    }

    @Override public String toString() {

        return String.format("From: %s\nTo: %s\n%s\n%s\n\n",
                this.msgToDisplay.getFromid(),
                this.msgToDisplay.getToid(),
                this.msgToDisplay.getTimestamp(),
                this.msgToDisplay.getMessage()
        );
    } 
}