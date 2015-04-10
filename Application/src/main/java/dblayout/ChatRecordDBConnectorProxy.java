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

public abstract class ChatRecordDBConnectorProxy {
    private static final String DATABASE_NAME = "WeRun";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    public ChatRecordDBConnectorProxy(Context context)
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
        newRecord.put("userid", name);
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
        editRecord.put("userid", name);
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

    public String getAllRecords() {
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM chatrecords", null);
        cursor.moveToFirst();
        StringBuilder sb = new StringBuilder();
        for (; !cursor.isAfterLast(); cursor.moveToNext()) {
            sb.append(cursor.getString(cursor.getColumnIndex("_id")));
            sb.append(",");
            sb.append(cursor.getString(cursor.getColumnIndex("userid")));
            sb.append(",");
            sb.append(cursor.getString(cursor.getColumnIndex("withuserid")));
            sb.append(",");
            sb.append(cursor.getString(cursor.getColumnIndex("time")));
            sb.append(",");
            sb.append(cursor.getString(cursor.getColumnIndex("content")));
            sb.append(";");
        }
        cursor.close();
        close();
        sb.setLength(sb.length() - 1);
        return sb.toString();
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
            String createQuery = "CREATE TABLE chatrecords" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, userid TEXT, withuserid TEXT, " +
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
