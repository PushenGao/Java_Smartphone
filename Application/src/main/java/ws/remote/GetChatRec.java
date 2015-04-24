package ws.remote;

import java.util.List;

import model.ChatRecord;

/**
 * Created by Michael-Gao on 2015/4/23.
 */
public interface GetChatRec {
    public List<ChatRecord> getChatRecord(String userid);
}
