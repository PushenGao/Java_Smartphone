package model;

import java.util.HashSet;

/**
 * Created by Jackyliz on 4/10/15.
 */
public class Account extends BasicAccount {
    private String userId;
    private String password;
    private String requestType;
    private HashSet<BasicAccount> friendSet;
    private HashSet<BasicAccount> pendingSet;
    private HashSet<BasicAccount> sendingSet;

    public String getUserId() {
        return userId;
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

    public HashSet<BasicAccount> getSendingSet() {
        return sendingSet;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public void setSendingSet(HashSet<BasicAccount> sendingSet) {
        this.sendingSet = sendingSet;
    }

    public void friendRequest(String userid, String friendid, String requestType) {

    }

    public void addFriend() {

    }

    public void acceptFriend() {

    }

    public void rejectFriend() {

    }

    public void deleteFriend() {

    }

    public void blockFriend() {

    }


}
