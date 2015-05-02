package dblayout;

import android.content.Context;

import java.util.ArrayList;

import model.ChatRecord;

/**
 * Created by JiateLi on 15/4/17.
 */
public class ChatRecordDAO implements IChatRecordDAO {
    private Context context;
    private DBConnector connector;
    public ChatRecordDAO(Context context) {
        this.context = context;
        connector = new DBConnector(context);
    }
    @Override
    public void insertRecord(ChatRecord chatRecord) {
        String myName = chatRecord.getMyName();
        String friendName = chatRecord.getFriendName();
        String time = chatRecord.getTimeStamp();
        String content = chatRecord.getChatContent();
        connector.insertRecord(myName,friendName,time,content);
    }

    @Override
    public void deleteRecord(ChatRecord chatRecord) {
        String myName = chatRecord.getMyName();
        String friendName = chatRecord.getFriendName();
        String time = chatRecord.getTimeStamp();
        String content = chatRecord.getChatContent();
        long id = connector.getRecordId(myName,friendName,time);
        connector.deleteRecord(id);
    }

    @Override
    public void updateRecord(ChatRecord chatRecord) {
        String myName = chatRecord.getMyName();
        String friendName = chatRecord.getFriendName();
        String time = chatRecord.getTimeStamp();
        String content = chatRecord.getChatContent();
        long id = connector.getRecordId(myName,friendName,time);
        connector.updateRecord(id,myName,friendName,time,content);
    }

    @Override
    public ArrayList<ChatRecord> getAllRecord(String userid, String withUserid) {
        return connector.getAllRecords(userid, withUserid);
    }

    @Override
    public ChatRecord getRecord(String userid, String withUserid, String time) {
        return null;
    }

    @Override
    public ArrayList<ChatRecord> getAllRecentRecord(String userid) {
        return null;
    }
}
