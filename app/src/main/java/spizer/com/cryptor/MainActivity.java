package spizer.com.cryptor;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBOpenHelper helper = new DBOpenHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();
    }

    /** called when the user clicks the start button **/
    public void TextInput (View view) {
        Intent TextIn = new Intent(this, TextInput.class);
        startActivity(TextIn);
    }
}
