package spizer.com.cryptor;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.lang.annotation.Target;

public class TextInput extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);

        DBOpenHelper helper = new DBOpenHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        insertText("New text");

        Cursor cursor = getContentResolver().query(TextProvider.CONTENT_URI,
                DBOpenHelper.ALL_COLUMNS, null, null, null, null);
        String[] from = {DBOpenHelper.USER_TEXT};
        int[] to = {android.R.id.text1};
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor, from, to, 0);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

    }

    private void insertText(String userText) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.USER_TEXT, "New text");
        Uri textUri = getContentResolver().insert(TextProvider.CONTENT_URI,
                values);
        Log.d("MainActivity.java", "Inserted text " + textUri.getLastPathSegment());
    }
}
