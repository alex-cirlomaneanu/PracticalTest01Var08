package ro.pub.cs.systems.eim.practicaltest01var08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {
    private TextView riddle2TextView;
    private EditText answer2EditText;
    private Button checkButton;
    private Button backButton;
    private ButtonListener buttonListener = new ButtonListener();
    private String answer1Text;

    Boolean result = null;

    private class ButtonListener implements View.OnClickListener {
@Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.check:
                    String answer = answer2EditText.getText().toString();
                    result = answer.equals(answer1Text);
                    if (result) {
                        setResult(1, null);
                    } else {
                        setResult(0, null);
                    }
                    break;
                case R.id.back:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
            intent.putExtra("answer", answer2EditText.getText().toString());

            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        answer2EditText = (EditText)findViewById(R.id.answer2);
        checkButton = (Button)findViewById(R.id.check);
        backButton = (Button)findViewById(R.id.back);
        answer2EditText.setText("");

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("riddle")) {
            String riddle = intent.getStringExtra("riddle");
            riddle2TextView = (TextView)findViewById(R.id.riddle2);
            riddle2TextView.setText(riddle);
            answer1Text = intent.getStringExtra("answer");
        }

        checkButton.setOnClickListener(buttonListener);
        backButton.setOnClickListener(buttonListener);
    }

}