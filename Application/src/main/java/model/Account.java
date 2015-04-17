package model;

import java.util.HashMap;
import java.util.HashSet;

import ui.History;

/**
 * Created by Jackyliz on 4/10/15.
 */
public class Account extends BasicAccount {
    private String userid;
    private String password;
    //    private String requestType;
    private HashSet<BasicAccount> friendSet;
    private HashSet<BasicAccount> pendingSet;

    public Account() {

    }

    public Account(String name, String age, String gender, HistoryRecord historyRecord, HashMap<String, ChatRecord> chatRecordMap,
                   String userid, String password, HashSet<BasicAccount> friendSet, HashSet<BasicAccount> pendingSet) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setHistoryRecord(historyRecord);
        this.setChatMap(chatRecordMap);
        this.userid = userid;
        this.password = password;
        this.friendSet = friendSet;
        this.pendingSet = pendingSet;
    }

    public String getUserId() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public HashSet<BasicAccount> getFriendSet() {
        return friendSet;
    }

    public HashSet<BasicAccount> getPendingSet() {
        return pendingSet;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFriendSet(HashSet<BasicAccount> friendSet) {
        this.friendSet = friendSet;
    }

    public void setPendingSet(HashSet<BasicAccount> pendingSet) {
        this.pendingSet = pendingSet;
    }

    public void friendRequest(String userid, String friendid, String requestType) {

    }

    public void addFriend() {

    }

    public void acceptFriend() {

    }

    public void rejectFriend() {

    }

    public void blockFriend() {

    }

    public void deleteFriend() {

    }

//    private HashSet<BasicAccount> sendingSet;
//    public HashSet<BasicAccount> getSendingSet() {
//        return sendingSet;
//    }
//    public void setSendingSet(HashSet<BasicAccount> sendingSet) {
//        this.sendingSet = sendingSet;
//    }
}
