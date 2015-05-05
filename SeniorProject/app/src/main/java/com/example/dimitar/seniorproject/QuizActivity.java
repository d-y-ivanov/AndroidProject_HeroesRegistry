package com.example.dimitar.seniorproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Enumeration;
import java.util.Hashtable;


public class QuizActivity extends ActionBarActivity {
    private Hashtable<String, String[]> questions = new Hashtable<>();
    private Enumeration Qs;
    private String[] rightAnswers = new String[]{"Krypton", "Maybe"};
    private RadioGroup rdGrp;
    private boolean correct = false;
    private String questionsContainer;
    private String[] answers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        rdGrp = (RadioGroup) findViewById(R.id.quizRadioGrp);

        initializeValues();

        if(Qs.hasMoreElements()) {
            questionsContainer = (String) Qs.nextElement();
            TextView question = (TextView) findViewById(R.id.txtQuestion);
            question.setText(questionsContainer);
            answers = questions.get(questionsContainer);
            RadioButton btnAnswer = (RadioButton) findViewById(R.id.btnOne);
            btnAnswer.setText(answers[0]);
            btnAnswer = (RadioButton) findViewById(R.id.btnTwo);
            btnAnswer.setText(answers[1]);
            btnAnswer = (RadioButton) findViewById(R.id.btnThree);
            btnAnswer.setText(answers[2]);
            btnAnswer = (RadioButton) findViewById(R.id.btnFour);
            btnAnswer.setText(answers[3]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    private void initializeValues() {
        questions.put("Which is the birth planet of Superman?", new String[] {"Jupiter","Mars","Omega","Krypton"});
        questions.put("Harvey Dent, can he be trusted?", new String[] {"Yes","No","Maybe","Nobody knows"});

        Qs = questions.keys();
    }

    public void checkAnswer(View view) {
        int selectedId = rdGrp.getCheckedRadioButtonId();
        RadioButton rdBtn = (RadioButton) findViewById(selectedId);

        for (String rightAnswer : rightAnswers) {
            if (rdBtn.getText() == rightAnswer) {
                if (!Qs.hasMoreElements()) {
                    Toast.makeText(getApplicationContext(), "You've successfully completed the quiz!", Toast.LENGTH_LONG).show();
                    correct=true;
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(QuizActivity.this, "You got it right!", Toast.LENGTH_SHORT).show();
                    correct = true;
                }
                break;
            } else {
                correct = false;
            }
        }
        if(!correct){
            Toast.makeText(QuizActivity.this, "That's not correct!", Toast.LENGTH_SHORT).show();
        }
        if(Qs.hasMoreElements() && (correct)) {
            questionsContainer = (String) Qs.nextElement();
            TextView question = (TextView) findViewById(R.id.txtQuestion);
            question.setText(questionsContainer);
            answers = questions.get(questionsContainer);
            RadioButton btnAnswer = (RadioButton) findViewById(R.id.btnOne);
            btnAnswer.setText(answers[0]);
            btnAnswer = (RadioButton) findViewById(R.id.btnTwo);
            btnAnswer.setText(answers[1]);
            btnAnswer = (RadioButton) findViewById(R.id.btnThree);
            btnAnswer.setText(answers[2]);
            btnAnswer = (RadioButton) findViewById(R.id.btnFour);
            btnAnswer.setText(answers[3]);
        }
    }
}
