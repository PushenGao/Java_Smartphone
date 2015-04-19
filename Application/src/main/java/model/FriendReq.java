package model;

/**
 * Created by Michael-Gao on 2015/4/11.
 */
public class FriendReq {
    private String senderId;
    private String receiverId;
    private String action;

    public String getReceiver() {
        return receiverId;
    }

    public void setReceiver(String receiver) {
        this.receiverId = receiver;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSender() {
        return senderId;
    }

    public void setSender(String sender) {
        this.senderId = sender;
    }

}
