package ws.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.BasicAccount;
import model.HistoryRecord;
import ui.History;

/**
 * Created by JiateLi on 15/4/21.
 */
public class JsonUtil {
    public HistoryRecord parseHistoryRecFromJson(String historyString){
        HistoryRecord history = new HistoryRecord();
        JSONParser parser=new JSONParser();
        JSONObject historyJson = null;

        try {
            historyJson = (JSONObject)parser.parse(historyString);

            history.setUserid((String)historyJson.get("userId"));
            history.setTotalTime((String) historyJson.get("totalTime"));
            history.setTotalDistance((String) historyJson.get("totalDistance"));
            history.setLastLocation((String) historyJson.get("lastLocation"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return history;

    }

    public BasicAccount parseBasicAccountFromJson(String basicString){
        BasicAccount basicAccount = new BasicAccount();
        JSONParser parser=new JSONParser();
        JSONObject basicJson = null;

        try {
            basicJson = (JSONObject)parser.parse(basicString);

            basicAccount.setName((String)basicJson.get("id"));
            basicAccount.setAge((String) basicJson.get("age"));
            basicAccount.setGender((String) basicJson.get("gender"));
            String historyString = basicJson.get("historyRecord").toString();
            HistoryRecord history = this.parseHistoryRecFromJson(historyString);
            basicAccount.setHistoryRecord(history);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return basicAccount;

    }

    public Account parseAccountFromJson(String accountString){
        Account account = new Account();
        JSONParser parser=new JSONParser();
        JSONObject accountJson = null;

        try {
            accountJson = (JSONObject)parser.parse(accountString);
            account.setPassword((String)accountJson.get("password"));
            account.setBasicAccount(this.parseBasicAccountFromJson(accountJson.get("account").toString()));
            String activeFri = accountJson.get("activeFriends").toString();
            String pendingFri = accountJson.get("pendingFriends").toString();

            List<BasicAccount> activeFriends = new ArrayList<BasicAccount>();
            List<BasicAccount> pendingFriends = new ArrayList<BasicAccount>();

            Object objActive= JSONValue.parse(activeFri);
            JSONArray arrayActive=(JSONArray)objActive;
            for(int i=0; i < arrayActive.size(); i++){
                activeFriends.add(this.parseBasicAccountFromJson(arrayActive.get(i).toString()));
            }
            account.setActiveFriends(activeFriends);

            Object objPending= JSONValue.parse(pendingFri);
            JSONArray arrayPending=(JSONArray)objPending;
            for(int i=0; i < arrayPending.size(); i++){
                pendingFriends.add(this.parseBasicAccountFromJson(arrayPending.get(i).toString()));
            }
            account.setPendingFriends(pendingFriends);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return account;

    }
}
