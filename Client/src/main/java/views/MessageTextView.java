package views;

import controllers.IdController;
import models.Id;
import models.Message;

public class MessageTextView {

    private Message msgToDisplay;
    private Id sender;
    private Id recipient;

    public MessageTextView(Message msgToDisplay) {
        this.msgToDisplay = msgToDisplay;

        this.sender = IdController.getInstance().getId(msgToDisplay.getFromid());
        this.recipient = IdController.getInstance().getId(msgToDisplay.getToid());

    }

    @Override public String toString() {
        String senderName;
        String senderGH;
        String recipientName;
        String recipientGH;

        if (this.sender == null) {
            senderName = "";
            senderGH = "";
        } else {
            senderName = this.sender.getName();
            senderGH = this.sender.getGitHubId();
        }
        if (this.recipient == null) {
            recipientName = "";
            recipientGH = "";
        } else {
            recipientName = this.recipient.getName();
            recipientGH = this.recipient.getGitHubId();
        }
        return String.format("From: %s (%s)\nTo: %s (%s)\n%s\n",
                senderName, senderGH,
                recipientName,recipientGH,
                this.msgToDisplay.getMessage()
        );
    } 
}