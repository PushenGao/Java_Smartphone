package model;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Jackyliz on 4/10/15.
 */
public class Account {
    private String userId;
    private String password;
    private String gender;
    private String name;
    private HashSet<String> friendSet;
    private HashSet<String> pendingSet;
    private HashSet<String> sendingSet;
    private HistoryRecord historyRecord;
    private HashMap<String, ChatRecord> map;

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public HashSet<String> getFriendSet() {
        return friendSet;
    }

    public HashSet<String> getPendingSet() {
        return pendingSet;
    }

    public HashSet<String> getSendingSet() {
        return sendingSet;
    }

    public HistoryRecord getHistoryRecord() {
        return historyRecord;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFriendSet(HashSet<String> friendSet) {
        this.friendSet = friendSet;
    }

    public void setPendingSet(HashSet<String> pendingSet) {
        this.pendingSet = pendingSet;
    }

    public void setSendingSet(HashSet<String> sendingSet) {
        this.sendingSet = sendingSet;
    }

    public void setHistoryRecord(HistoryRecord historyRecord) {
        this.historyRecord = historyRecord;
    }
}
