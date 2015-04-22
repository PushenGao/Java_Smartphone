package ws.remote;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.BasicAccount;
import model.FriendReq;
import model.HistoryRecord;
import ws.util.JsonUtil;

/**
 * Created by JiateLi on 15/4/17.
 */
public class RemoteServerProxy implements FriendRequest, RecommendFriend, RegisterAccountToServer,
 UpdateRunningRecord, VerifyLoginAccount, SearchAccount{

    @Override
    public String reqFriend(FriendReq freq) {
        String targetURL = "http://localhost:8080/Jersey/rest/werun/friquest";
        String response = RestfulPOST.restPOST(freq.toString(),targetURL);
        return response;
    }

    @Override
    public List<BasicAccount> getRecommend(String userId) {
        JsonUtil jsonUtil = new JsonUtil();
        String targetURL = "http://localhost:8080/Jersey/rest/werun/recommend/" + userId;
        String resp = RestfulGET.restGET(targetURL);

        List<BasicAccount> recommendFriends = new ArrayList<BasicAccount>();
        Object objRecommend= JSONValue.parse(resp);
        JSONArray arrayRecommend=(JSONArray)objRecommend;
        for(int i=0; i < arrayRecommend.size(); i++){
            recommendFriends.add(jsonUtil.parseBasicAccountFromJson(arrayRecommend.get(i).toString()));
        }
        return  recommendFriends;
    }


    @Override
    public String updateHistoryRecord(HistoryRecord history) {
        String targetURL = "http://localhost:8080/Jersey/rest/werun/record";
        String response = RestfulPOST.restPOST(history.toString(),targetURL);
        return response;
    }

    @Override
    public Account verifyAccount(String userId, String passWord) {
        JsonUtil jsonUtil = new JsonUtil();
        String targetURL = "http://localhost:8080/Jersey/rest/werun/login/" + userId + "/" + passWord;
        String resp = RestfulGET.restGET(targetURL);
        return jsonUtil.parseAccountFromJson(resp);
    }

    @Override
    public String register(Account newAccount) {
        String targetURL = "http://localhost:8080/Jersey/rest/werun/register";
        String response = RestfulPOST.restPOST(newAccount.toString(),targetURL);
        return response;
    }

    @Override
    public BasicAccount searchAccount(String userid) {
        JsonUtil jsonUtil = new JsonUtil();
        String targetURL = "http://localhost:8080/Jersey/rest/werun/search/" + userid;
        String resp = RestfulGET.restGET(targetURL);
        return jsonUtil.parseBasicAccountFromJson(resp);
    }
}
