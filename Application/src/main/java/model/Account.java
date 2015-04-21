package model;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;

import ui.History;

/**
 * Created by Jackyliz on 4/10/15.
 */
public class Account extends BasicAccount {
    private String password;
    private HashSet<BasicAccount> friendSet;
    private HashSet<BasicAccount> pendingSet;

    // empty constructor
    public Account() {

    }

    // register constructor
    public Account(String name, String age, String gender, String userid, String password) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.password = password;
    }

    // complete constructor
    public Account(String name, String age, String gender, HistoryRecord historyRecord, HashMap<String, ChatRecord> chatRecordMap,
                   String userid, String password, HashSet<BasicAccount> friendSet, HashSet<BasicAccount> pendingSet) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setHistoryRecord(historyRecord);
        this.password = password;
        this.friendSet = friendSet;
        this.pendingSet = pendingSet;
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }



}
