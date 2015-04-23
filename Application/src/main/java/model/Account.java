package model;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ui.History;

/**
 * Created by Jackyliz on 4/10/15.
 */
public class Account{
    private String password;
    private BasicAccount basicAccount;
    private List<BasicAccount> activeFriends;
    private List<BasicAccount> pendingFriends;



    public BasicAccount getBasicAccount() {
        return basicAccount;
    }

    public void setBasicAccount(BasicAccount basicAccount) {
        this.basicAccount = basicAccount;
    }

    public List<BasicAccount> getActiveFriends() {
        return activeFriends;
    }

    public void setActiveFriends(List<BasicAccount> activeFriends) {
        this.activeFriends = activeFriends;
    }

    public List<BasicAccount> getPendingFriends() {
        return pendingFriends;
    }

    public void setPendingFriends(List<BasicAccount> pendingFriends) {
        this.pendingFriends = pendingFriends;
    }

    // empty constructor
    public Account() {
    }

    // register constructor
    public Account(String password) {
        this.password = password;
    }



    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }



    public void friendRequest(String userid, String friendid, String requestType) {

    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }



}
