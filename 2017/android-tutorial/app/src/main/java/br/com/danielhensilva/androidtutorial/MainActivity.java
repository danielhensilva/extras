package br.com.danielhensilva.androidtutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "br.com.danielhensilva.androidtutorial.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendButtonClick(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, this.getText());
        startActivity(intent);
    }

    private String getText() {
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        return message;
    }
}
