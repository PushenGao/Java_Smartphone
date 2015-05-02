package dblayout;

/**
 * Created by JiateLi on 15/4/9.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.util.ArrayList;

import model.ChatRecord;

public abstract class DBConnectorProxy {
    private static final String DATABASE_NAME = "WeRun";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    public DBConnectorProxy(Context context)
    {
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }

    public void open() throws SQLException
    {
        database = databaseOpenHelper.getWritableDatabase();
    }

    public void close()
    {
        if (database != null)
            database.close();
    }

    public void insertRecord(String name, String withuser, String time, String content)
    {
        ContentValues newRecord = new ContentValues();
        newRecord.put("senderuserid", name);
        newRecord.put("withuserid", withuser);
        newRecord.put("time", time);
        newRecord.put("content", content);

        open();
        database.insert("chatrecords", null, newRecord);
        close();
    }

    public void updateRecord(long id, String name, String withuser, String time, String content)
    {
        ContentValues editRecord = new ContentValues();
        editRecord.put("senderuserid", name);
        editRecord.put("withuserid", withuser);
        editRecord.put("time", time);
        editRecord.put("content", content);

        open();
        database.update("chatrecords", editRecord, "_id=" + id, null);
        close();
    }

    public Cursor getRecord(long id)
    {
        return database.query(
                "chatrecords", null, "_id=" + id, null, null, null, null);
    }

    //needs test
    public long getRecordId(String senderuserid, String withuserid, String time){
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM chatrecords where senderuserid = " + senderuserid +
                ", withuserid = " + withuserid + ", time = " + time, null);
        cursor.moveToFirst();
        long rst =  cursor.getLong(cursor.getColumnIndex("_id"));
        cursor.close();
        close();
        return rst;
    }


    public ChatRecord getRecord(String senderuserid, String withuserid, String time){
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM chatrecords where senderuserid = " + senderuserid +
                ", withuserid = " + withuserid + ", time = " + time, null);
        cursor.moveToFirst();
        String sender = cursor.getString(cursor.getColumnIndex("senderuserid"));
        String receiver = cursor.getString(cursor.getColumnIndex("withuserid"));
        String sendTime = cursor.getString(cursor.getColumnIndex("time"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        ChatRecord chatRecord = new ChatRecord(sender, receiver, time, content);
        cursor.close();
        close();
        return chatRecord;
    }


    public ArrayList<ChatRecord> getAllRecords(String senderuserid, String withuserid) {
        ArrayList<ChatRecord> list = new ArrayList<ChatRecord>();
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM chatrecords where senderuserid = '" + senderuserid +
                "' and withuserid = '" + withuserid + "'", null);
        cursor.moveToFirst();
        for (; !cursor.isAfterLast(); cursor.moveToNext()) {

            String sender = cursor.getString(cursor.getColumnIndex("senderuserid"));
            String receiver = cursor.getString(cursor.getColumnIndex("withuserid"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            ChatRecord chatRecord = new ChatRecord(sender, receiver, time, content);
            list.add(chatRecord);
        }
        cursor.close();
        close();
        return list;
    }

    public ArrayList<ChatRecord> getAllRecentRecords(String senderuserid) {
        ArrayList<ChatRecord> list = new ArrayList<ChatRecord>();
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM chatrecords where senderuserid = " + senderuserid, null);
        cursor.moveToFirst();
        for (; !cursor.isAfterLast(); cursor.moveToNext()) {

            String sender = cursor.getString(cursor.getColumnIndex("senderuserid"));
            String receiver = cursor.getString(cursor.getColumnIndex("withuserid"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            ChatRecord chatRecord = new ChatRecord(sender, receiver, time, content);
            list.add(chatRecord);
        }
        cursor.close();
        close();
        return list;
    }

    public void deleteRecord(long id)
    {
        open();
        database.delete("chatrecords", "_id=" + id, null);
        close();
    }

    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        public DatabaseOpenHelper(Context context, String name,
                                  CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {

            String createQuery = "CREATE TABLE if not exists chatrecords" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, senderuserid TEXT, withuserid TEXT, " +
                    "time TEXT, content TEXT);";
            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }
    }

}
