package com.bleard.geoquiz.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {
    private Button trueButton,falseButton,nextButton;
    private TextView questionText;
    private Question[] questionBank = new Question[]{
                                      new Question(R.string.question_australia,true),
                                      new Question(R.string.question_africa,false),
                                      new Question(R.string.question_americas,true),
                                      new Question(R.string.question_asia,false),
                                      new Question(R.string.question_mideast,true),
                                      new Question(R.string.question_oceans,false)
    };
    private int currentIndex = 0;
    private void setQuestion(){
        int resId = questionBank[currentIndex].getMessageId();
        questionText.setText(resId);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        trueButton =  findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        nextButton = findViewById(R.id.nextButton);
        questionText = findViewById(R.id.questionText);
        setQuestion();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex +1 )% questionBank.length;
                setQuestion();

            }
        });

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }
    private void checkAnswer(boolean userClickedTrue){
        boolean answer = questionBank[currentIndex].isAnswerTrue();
        int messageId = answer == userClickedTrue ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(this,messageId,Toast.LENGTH_SHORT).show();

    }
}
