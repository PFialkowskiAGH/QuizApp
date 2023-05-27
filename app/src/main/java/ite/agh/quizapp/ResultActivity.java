package ite.agh.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ite.agh.quizapp.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityResultBinding binding;

    TextView scoreTextView;
    TextView numberOfQuestion;

    Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreTextView = findViewById(R.id.score);
        numberOfQuestion = findViewById(R.id.numberOfQuestions);
        restartButton = findViewById(R.id.restart_button);

        scoreTextView.setText(String.valueOf(MainActivity.score));
        numberOfQuestion.setText(String.valueOf(QuestionAnswer.questions.length));

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.score = 0;
                MainActivity.currentQuestion = 0;
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}