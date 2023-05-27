package ite.agh.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    public static int score=0;
    int totalQuestion = QuestionAnswer.questions.length;
    public static int currentQuestion = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total Questions: " + totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View v)
    {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if(clickedButton.getId()==R.id.submit_btn)
        {
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestion]))
            {
                score++;
            }
            currentQuestion++;
            loadNewQuestion();
        }
        else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion()
    {
        if(currentQuestion == totalQuestion)
        {
            finishQuiz();
        }
        else {
            questionTextView.setText(QuestionAnswer.questions[currentQuestion]);
            ansA.setText(QuestionAnswer.choices[currentQuestion][0]);
            ansB.setText(QuestionAnswer.choices[currentQuestion][1]);
            ansC.setText(QuestionAnswer.choices[currentQuestion][2]);
            ansD.setText(QuestionAnswer.choices[currentQuestion][3]);
        }
    }

    void finishQuiz()
    {
        //String scoreMessage = "Twój wynik to: " + score + " na " + totalQuestion;

        //new AlertDialog.Builder(this)
                //.setTitle("Wynik")
                //.setMessage(scoreMessage)
                //.setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                //.show();
        startActivity(new Intent(MainActivity.this, ResultActivity.class));
        finish();
    }

    void restartQuiz()
    {
        score = 0;
        currentQuestion = 0;
        loadNewQuestion();
    }
}