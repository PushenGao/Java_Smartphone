package ws.remote;

import model.Account;

/**
 * Created by JiateLi on 15/4/11.
 */
public interface VerifyLoginAccount {
    public Account verifyAccount(String userId, String passWord);
}
