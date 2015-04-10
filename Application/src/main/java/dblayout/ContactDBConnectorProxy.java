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

public abstract class ContactDBConnectorProxy {
    private static final String DATABASE_NAME = "WeRun";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    public ContactDBConnectorProxy(Context context)
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

    public void insertContact(String name, String gender, int age, int type)
    {
        ContentValues newContact = new ContentValues();
        newContact.put("name", name);
        newContact.put("gender", gender);
        newContact.put("age", age);
        newContact.put("type", type);

        open();
        database.insert("contacts", null, newContact);
        close();
    }

    public void updateContact(long id, String name, String gender, int age, int type)
    {
        ContentValues editContact = new ContentValues();
        editContact.put("name", name);
        editContact.put("gender", gender);
        editContact.put("age", age);
        editContact.put("type", type);

        open();
        database.update("contacts", editContact, "_id=" + id, null);
        close();
    }

    public Cursor getContact(long id)
    {
        return database.query(
                "contacts", null, "_id=" + id, null, null, null, null);
    }

    public String getAllContact() {
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM contacts", null);
        cursor.moveToFirst();
        StringBuilder sb = new StringBuilder();
        for (; !cursor.isAfterLast(); cursor.moveToNext()) {
            sb.append(cursor.getString(cursor.getColumnIndex("_id")));
            sb.append(",");
            sb.append(cursor.getString(cursor.getColumnIndex("name")));
            sb.append(",");
            sb.append(cursor.getString(cursor.getColumnIndex("gender")));
            sb.append(",");
            sb.append(cursor.getInt(cursor.getColumnIndex("age")));
            sb.append(",");
            sb.append(cursor.getInt(cursor.getColumnIndex("type")));
            sb.append(";");
        }
        cursor.close();
        close();
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public void deleteContact(long id)
    {
        open();
        database.delete("contacts", "_id=" + id, null);
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
            String createQuery = "CREATE TABLE contacts" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, " +
                    "gender TEXT, age INTEGER, type INTEGER);";
            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }
    }
}
