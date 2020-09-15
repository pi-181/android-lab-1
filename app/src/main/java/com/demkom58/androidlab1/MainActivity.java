package com.demkom58.androidlab1;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private TextView infoText;
    private EditText inputField;
    private Button controlButton;

    private int guess;
    private boolean finished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoText = findViewById(R.id.infoView);
        inputField = findViewById(R.id.numberField);
        controlButton = findViewById(R.id.controlButton);

        restartGame();
    }

    public void onControlClick(View view) {
        if (finished) {
            restartGame();
            return;
        }

        final Resources resources = getResources();
        final String numberString = inputField.getText().toString().trim();
        if (numberString.isEmpty()) {
            infoText.setText(resources.getString(R.string.error));
            return;
        }

        final int number;
        try {
            number = Integer.parseInt(numberString);
        } catch (NumberFormatException e) {
            infoText.setText(resources.getString(R.string.error));
            return;
        }

        if (number < 1 || number > 99) {
            infoText.setText(resources.getString(R.string.out_of_range));
            return;
        }

        if (number == guess) {
            infoText.setText(resources.getString(R.string.hit));
            controlButton.setText(resources.getString(R.string.play_more));
            finished = true;
            return;
        }

        infoText.setText(resources.getString(number > guess ? R.string.ahead : R.string.behind));
    }

    private void restartGame() {
        final Resources resources = getResources();
        controlButton.setTag(resources.getString(R.string.input_value));
        infoText.setText(resources.getString(R.string.try_to_guess));
        guess = (int) (Math.random() * 100);
        finished = false;
    }

}


