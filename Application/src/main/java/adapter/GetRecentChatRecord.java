package adapter;

import model.ChatRecord;

/**
 * Created by JiateLi on 15/4/11.
 */
public interface GetRecentChatRecord {
    public ChatRecord getChatRecord(String userid, String withUserid, String time);
}
