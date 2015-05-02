package adapter;

import android.content.Context;

import java.util.LinkedHashMap;

import dblayout.ChatRecordDAO;
import model.ChatRecord;
import ui.ChatWindow;


/**
 * Created by JiateLi on 15/4/11.
 */
public abstract class RecentChatRecordProxy {
    public static LinkedHashMap<String, ChatRecord> chatrecordMap = new LinkedHashMap<String, ChatRecord>();

    public void addRecentChatRecord(ChatRecord chatRecord){
        ChatRecordDAO chatRecordDAO = new ChatRecordDAO(ChatWindow.appContext);
        chatRecordDAO.insertRecord(chatRecord);
    };

    public void deleteRecentChatRecord(ChatRecord chatRecord){
        ChatRecordDAO chatRecordDAO = new ChatRecordDAO(ChatWindow.appContext);
        chatRecordDAO.deleteRecord(chatRecord);
    };

    public void updateRecentChatRecord(ChatRecord chatRecord){
        ChatRecordDAO chatRecordDAO = new ChatRecordDAO(ChatWindow.appContext);
        chatRecordDAO.updateRecord(chatRecord);
    };
}
