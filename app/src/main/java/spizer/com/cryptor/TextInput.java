package spizer.com.cryptor;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TextInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);

        DBOpenHelper helper = new DBOpenHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        insertText("New text");
    }

    private void insertText(String userText) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.USER_TEXT, "New text");
        Uri textUri = getContentResolver().insert(TextProvider.CONTENT_URI,
                values);
        Log.d("MainActivity.java", "Inserted text " + textUri.getLastPathSegment());
    }
}
