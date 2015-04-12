package adapter;

import model.ChatRecord;

/**
 * Created by JiateLi on 15/4/11.
 */
public interface CreateChatRecord {

    public ChatRecord createChatRecord(String userid, String withUserid, String time, String content);
}
