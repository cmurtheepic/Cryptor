package spizer.com.cryptor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by connor on 10/13/2015.
 */
public class TextDatabase {
    private DBHelper dbHelper;

    public void TextRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(TextData textData) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TextData.KEY_Txt, textData.txt);

        // Inserting Row
        long text_Id = db.insert(TextData.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) text_Id;
    }

    public void delete(int student_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(TextData.TABLE, TextData.KEY_ID + "= ?", new String[] { String.valueOf(student_Id) });
        db.close(); // Closing database connection
    }

    public void update(TextData text) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TextData.KEY_Txt, text.txt);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(TextData.TABLE, values, TextData.KEY_ID + "= ?", new String[] { String.valueOf(text.id) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getTextList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                TextData.KEY_ID + "," +
                TextData.KEY_Txt + "," +
                " FROM " + TextData.TABLE;

        //TextData student = new TextData();
        ArrayList<HashMap<String, String>> textList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> text = new HashMap<String, String>();
                text.put("id", cursor.getString(cursor.getColumnIndex(TextData.KEY_ID)));
                text.put("text", cursor.getString(cursor.getColumnIndex(TextData.KEY_Txt)));
                textList.add(text);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return textList;

    }

    public TextData getTextById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                TextData.KEY_ID + "," +
                TextData.KEY_Txt + "," +
                " FROM " + TextData.TABLE
                + " WHERE " +
                TextData.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        TextData textData = new TextData();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                textData.id =cursor.getInt(cursor.getColumnIndex(TextData.KEY_ID));
                textData.txt =cursor.getString(cursor.getColumnIndex(TextData.KEY_Txt));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return textData;
    }
}
