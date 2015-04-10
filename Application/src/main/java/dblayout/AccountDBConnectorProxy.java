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

    public void insertAccount(String id, String name, int runtime, double rundistance, int totalenergy,
                              String gender, int age)
    {
        ContentValues newAccount = new ContentValues();
        newAccount.put("userid", id);
        newAccount.put("name", name);
        newAccount.put("runtime", runtime);
        newAccount.put("rundistance", rundistance);
        newAccount.put("totalenergy", totalenergy);
        newAccount.put("gender", gender);
        newAccount.put("age", age);

        open();
        database.insert("account", null, newAccount);
        close();
    }

    public void updateAccount(String id, String name, int runtime, double rundistance, int totalenergy,
                              String gender, int age)
    {
        ContentValues editAccount = new ContentValues();
        editAccount.put("name", name);
        editAccount.put("runtime", runtime);
        editAccount.put("rundistance", rundistance);
        editAccount.put("totalenergy", totalenergy);
        editAccount.put("gender", gender);
        editAccount.put("age", age);

        open();
        database.update("account", editAccount, "_id=" + id, null);
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
            sb.append(",");
            sb.append(cursor.getInt(cursor.getColumnIndex("runtime")));
            sb.append(",");
            sb.append(cursor.getDouble(cursor.getColumnIndex("rundistance")));
            sb.append(",");
            sb.append(cursor.getInt(cursor.getColumnIndex("totalenergy")));
            sb.append(",");
            sb.append(cursor.getString(cursor.getColumnIndex("gender")));
            sb.append(",");
            sb.append(cursor.getInt(cursor.getColumnIndex("age")));
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
                    "(userid TEXT PRIMARY KEY, name TEXT, " +
                    "runtime INTEGER, rundistance REAL, totalenergy INTEGER," +
                    "gender TEXT, age INTEGER);";

            db.execSQL(createQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }
    }

}
