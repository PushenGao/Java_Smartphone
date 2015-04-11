package model;

/**
 * Created by Jackyliz on 4/10/15.
 */
public class ChatRecord {
    private String friendName;
    private String timeStamp;
    private String chatContent;

    public String getFriendName() {
        return friendName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }
}
