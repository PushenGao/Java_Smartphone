package adapter;

import java.util.LinkedHashMap;
import model.ChatRecord;


/**
 * Created by JiateLi on 15/4/11.
 */
public abstract class RecentChatRecordProxy {
    public static LinkedHashMap<String, ChatRecord> chatrecordMap = new LinkedHashMap<String, ChatRecord>();

    public void addRecentChatRecord(ChatRecord chatRecord){

    };

    public void deleteRecentChatRecord(int chatRecordID){

    };

    public void updateRecentChatRecord(ChatRecord chatRecord){

    };
}
