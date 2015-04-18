package dblayout;

import java.util.ArrayList;

import model.ChatRecord;

/**
 * Created by JiateLi on 15/4/17.
 */
public class ChatRecordDAO implements IChatRecordDAO {
    @Override
    public void insertRecord(ChatRecord chatRecord) {

    }

    @Override
    public void deleteRecord(ChatRecord chatRecord) {

    }

    @Override
    public void updateRecord(ChatRecord chatRecord) {

    }

    @Override
    public ArrayList<ChatRecord> getAllRecord(String userid, String withUserid) {
        return null;
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
