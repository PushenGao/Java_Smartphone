package local;

import java.util.HashMap;

/**
 * Created by JiateLi on 15/4/11.
 */
public interface IDAO {
    public HashMap accountMap = new HashMap();

    public void insertAccount();

    public void deleteAccount();

    public void updateAccount();

    public void getAccount();
}
