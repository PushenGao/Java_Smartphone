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
UpdateAccountInfo, UpdateRunningRecord, VerifyLoginAccount{

    @Override
    public void reqFriend(FriendReq freq) {

    }

    @Override
    public List<BasicAccount> getRecommend(String userId) {
        return null;
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public void updateHistoryRecord(HistoryRecord history) {

    }

    @Override
    public Account verifyAccount(String userId, String passWord) {
        return null;
    }

    @Override
    public void register(Account newAccount) {

    }
}
