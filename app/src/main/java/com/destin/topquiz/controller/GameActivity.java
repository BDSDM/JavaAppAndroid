package com.destin.topquiz.controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.destin.topquiz.R;
import com.destin.topquiz.model.Question;
import com.destin.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView activity_textview_question;
    private Button activity_button_1;
    private Button activity_button_2;
    private Button activity_button_3;
    private Button activity_button_4;
    QuestionBank mQuestionBank=generateQuestions();
    Question mCurrentQuestion;
    private int mRemainingQuestionCount;
    private int mScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        activity_textview_question=findViewById(R.id.game_activity_textview_question);
        activity_button_1=findViewById(R.id.game_activity_button_1);
        activity_button_2=findViewById(R.id.game_activity_button_2);
        activity_button_3=findViewById(R.id.game_activity_button_3);
        activity_button_4=findViewById(R.id.game_activity_button_4);

        activity_button_1.setOnClickListener(this);
        activity_button_2.setOnClickListener(this);
        activity_button_3.setOnClickListener(this);
        activity_button_4.setOnClickListener(this);
        mCurrentQuestion=mQuestionBank.getCurrentQuestion();

        displayQuestion(mCurrentQuestion);

        mRemainingQuestionCount=3;
        mScore=0;



    }
    private void displayQuestion(final Question question){
        System.out.println(question.getQuestion());
        activity_textview_question.setText(question.getQuestion());
        activity_button_1.setText(question.getChoiceList().get(0));
        activity_button_2.setText(question.getChoiceList().get(1));
        activity_button_3.setText(question.getChoiceList().get(2));
        activity_button_4.setText(question.getChoiceList().get(3));

    }





    private QuestionBank generateQuestions(){

    Question question1 = new Question(
            "Who is the creator of Android?",
            Arrays.asList(
                    "Andy Rubin",
                    "Steve Wozniak",
                    "Jake Wharton",
                    "Paul Smith"
            ),
            0
    );

    Question question2 = new Question(
            "When did the first man land on the moon?",
            Arrays.asList(
                    "1958",
                    "1962",
                    "1967",
                    "1969"
            ),
            3
    );

    Question question3 = new Question(
            "What is the house number of The Simpsons?",
            Arrays.asList(
                    "42",
                    "101",
                    "666",
                    "742"
            ),
            3
    );

return new QuestionBank(Arrays.asList(question1, question2, question3));

}

    @Override
    public void onClick(View view) {
        System.out.println("ça marche");
        int index;

        if (view == activity_button_1) {
            index = 0;
        } else if (view == activity_button_2) {
            index = 1;
        } else if (view == activity_button_3) {
            index = 2;
        } else if (view == activity_button_4) {
            index = 3;
        } else {
            throw new IllegalStateException("Unknown clicked view : " + view);

        }
        if (index==mQuestionBank.getCurrentQuestion().getAnswerIndex()){
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
            mScore++;

        }else {
            Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();
        }

        mRemainingQuestionCount--;
        if(mRemainingQuestionCount>0){
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Well done!")
                    .setMessage("Your score is " +mScore)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            finish();
                        }
                    }).create()
                    .show();
        }

    }
}