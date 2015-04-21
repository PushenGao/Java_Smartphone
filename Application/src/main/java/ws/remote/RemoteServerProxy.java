package ws.remote;

import java.util.List;

import model.Account;
import model.BasicAccount;
import model.FriendReq;
import model.HistoryRecord;

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

        return null;
    }


    @Override
    public String updateHistoryRecord(HistoryRecord history) {
        String targetURL = "http://localhost:8080/Jersey/rest/werun/record";
        String response = RestfulPOST.restPOST(history.toString(),targetURL);
        return response;
    }

    @Override
    public Account verifyAccount(String userId, String passWord) {

        return new Account();
    }

    @Override
    public String register(Account newAccount) {
        String targetURL = "http://localhost:8080/Jersey/rest/werun/register";
        String response = RestfulPOST.restPOST(newAccount.toString(),targetURL);
        return response;
    }

    @Override
    public BasicAccount searchAccount(String userid) {
        return null;
    }
}
