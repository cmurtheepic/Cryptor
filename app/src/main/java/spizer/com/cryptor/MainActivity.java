package spizer.com.cryptor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextDatabase textDatabase = new TextDatabase();

    TextView input;

    private String text1;

    public String getText1() {
        return text1;
    }
    public void setText1(String text) {
        this.text1 = text1;
    }

    private int _TextData_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (TextView) findViewById(R.id.editText);

        final TextData textData = new TextData();

        _TextData_Id=0;

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                TextDatabase repo = new TextDatabase();

                Intent intent = getIntent();
                _TextData_Id =intent.getIntExtra("textData_Id", 0);

                setText1(input.toString());
                textData.txt=input.getText().toString();

                _TextData_Id = repo.insert(textData);
            return false;
            }
        });

    }

    private void cont() {
        Intent cont = new Intent(this, MethodActivity.class);
        startActivity(cont);
    }
}
