package spizer.com.cryptor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by connor on 10/3/2015.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    //Constants for db name and version
    private static final String DATABASE_NAME = "Text.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_TEXTS = "texts";
    public static final String TEXT_ID = "_id";
    public static final String USER_TEXT = "userText";
    public static final String TEXT_CREATED = "noteCreated";

    public static final String[] ALL_COLUMNS =
            {TEXT_ID, USER_TEXT, TEXT_CREATED};

    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_TEXTS + " (" +
                    TEXT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_TEXT + " TEXT, " +
                    TEXT_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_TEXTS);
        onCreate(db);
    }
}
