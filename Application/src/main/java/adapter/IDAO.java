package adapter;

import java.util.HashMap;

import model.Account;

/**
 * Created by JiateLi on 15/4/11.
 */
public interface IDAO {
    public HashMap<String, Account> accountMap = new HashMap<String, Account>();

    public void insertAccount(Account account);

    public void deleteAccount(Account account);

    public void updateAccount(Account account);

    public void getAccount(String userid);
}
