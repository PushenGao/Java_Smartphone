package model;

/**
 * Created by Michael-Gao on 2015/4/11.
 */
public class FriendReq {
    private String sender;
    private String receiver;
    private String action;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
