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

public abstract class AccountDBConnectorProxy {
    private static final String DATABASE_NAME = "WeRun";
    private SQLiteDatabase database;
    private DatabaseOpenHelper databaseOpenHelper;

    public AccountDBConnectorProxy(Context context)
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

    public void insertAccount(String id, String name)
    {
        ContentValues newAccount = new ContentValues();
        newAccount.put("userid", id);
        newAccount.put("name", name);

        open();
        database.insert("account", null, newAccount);
        close();
    }

    public void updateAccount(String id, String name)
    {
        ContentValues editAccount = new ContentValues();
        editAccount.put("name", name);

        open();
        database.update("account", editAccount, "userid=" + id, null);
        close();
    }

    public Cursor getAccount(String id)
    {
        return database.query(
                "account", null, "userid=" + id, null, null, null, null);
    }

    public String getDataInfo() {
        open();
        Cursor cursor = database.rawQuery("SELECT * FROM account", null);
        cursor.moveToFirst();
        StringBuilder sb = new StringBuilder();
        for (; !cursor.isAfterLast(); cursor.moveToNext()) {
            sb.append(cursor.getString(cursor.getColumnIndex("userid")));
            sb.append(",");
            sb.append(cursor.getString(cursor.getColumnIndex("name")));
            sb.append(";");
        }
        cursor.close();
        close();
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public void deleteAccount(String id)
    {
        open();
        database.delete("account", "userid=" + id, null);
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
            String createQuery = "CREATE TABLE account" +
                    "(userid TEXT PRIMARY KEY, name TEXT);";

            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }
    }

}
