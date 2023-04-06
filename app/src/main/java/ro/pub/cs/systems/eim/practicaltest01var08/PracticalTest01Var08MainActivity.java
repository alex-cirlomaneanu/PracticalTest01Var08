package ro.pub.cs.systems.eim.practicaltest01var08;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var08MainActivity extends AppCompatActivity {
    private EditText riddleEditText;
    private EditText answerEditText;
    private Button playButton;
    private ButtonListener buttonListener = new ButtonListener();


    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.play:
                    String riddle = riddleEditText.getText().toString();
                    String answer = answerEditText.getText().toString();
                    if (riddle.isEmpty() || answer.isEmpty()) {
                        break;
                    }

                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                    intent.putExtra("riddle", riddle);
                    intent.putExtra("answer", answer);
                    startActivityForResult(intent, 1);
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);

        riddleEditText = (EditText)findViewById(R.id.riddle);
        answerEditText = (EditText)findViewById(R.id.answer);
        playButton = (Button)findViewById(R.id.play);
        playButton.setOnClickListener(buttonListener);

        riddleEditText.setText("");
        answerEditText.setText("");

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("riddle")) {
                riddleEditText.setText(savedInstanceState.getString("riddle"));
            }
            if (savedInstanceState.containsKey("answer")) {
                answerEditText.setText(savedInstanceState.getString("answer"));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult", "onActivityResult: " + requestCode + " " + resultCode);
        if (requestCode == 1) {
            Toast.makeText(this, "Rezultatul este: " + resultCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("riddle", riddleEditText.getText().toString());
        outState.putString("answer", answerEditText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("riddle")) {
            riddleEditText.setText(savedInstanceState.getString("riddle"));
        }
        if (savedInstanceState.containsKey("answer")) {
            answerEditText.setText(savedInstanceState.getString("answer"));
        }
    }
}