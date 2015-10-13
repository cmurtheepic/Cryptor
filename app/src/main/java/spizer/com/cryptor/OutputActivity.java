package spizer.com.cryptor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this);

    TextDatabase textDatabase = new TextDatabase();

    TextView BeforeView;
    TextView OutputView;

    private String BeforeText;

    public String getBeforeText() {
        return BeforeText;
    }
    public void setBeforeText(String text) {
        this.BeforeText = BeforeText;
    }

    private String AfterText;

    public String getAfterText() {
        return AfterText;
    }
    public void setAfterText(String afterText) {
        AfterText = afterText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        setBeforeText(String.valueOf(textDatabase.getTextList()));

        BeforeView = (TextView) findViewById(R.id.textView3);
        OutputView = (TextView) findViewById(R.id.textView5);

        BeforeView.setText(getBeforeText());

    }

    // encrypts the user's text
    private void encoder() {

        byte[] bytes = BeforeText.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }

        setAfterText(String.valueOf(binary));

    }

    // sets the output text
    private void SetText() {
        OutputView.setText(getAfterText());
    }
}
