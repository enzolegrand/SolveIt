package fr.propan.solveit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toolbar;

import fr.propan.solveit.database.GameBaseHelper;
import fr.propan.solveit.database.GameHistoryDao;

public class GameActivity extends AppCompatActivity {

    private int nombreVies = 3;
    private int score = 0;

    private Toolbar toolbar;
    private TextView calculMentalTextView, reponseTextView;
    private TextView viesTextView, scoreTextView;
    private String calculMental;
    private int calculMentalReponse;
    private LinearLayout layout_center_calcul;

    private Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_delete, button_clear, button_verify;

    private void clearAnswer() {
        reponseTextView.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        viesTextView = findViewById(R.id.lifes_textview);
        scoreTextView = findViewById(R.id.score_textview);

        layout_center_calcul = findViewById(R.id.layout_center_calcul);

        toolbar = findViewById(R.id.gameToolBar);

        calculMental = generateCalcul();
        calculMentalTextView = findViewById(R.id.calcul_mental_textview);
        calculMentalTextView.setText(calculMental);

        calculMentalReponse = checkAnswer(calculMental);

        reponseTextView = findViewById(R.id.reponse_textview);

        setLives(3);
        setScore(0);

        button_0 = findViewById(R.id.Bouton_0);
        button_1 = findViewById(R.id.Bouton_1);
        button_2 = findViewById(R.id.Bouton_2);
        button_3 = findViewById(R.id.Bouton_3);
        button_4 = findViewById(R.id.Bouton_4);
        button_5 = findViewById(R.id.Bouton_5);
        button_6 = findViewById(R.id.Bouton_6);
        button_7 = findViewById(R.id.Bouton_7);
        button_8 = findViewById(R.id.Bouton_8);
        button_9 = findViewById(R.id.Bouton_9);

        button_0.setOnClickListener(view -> addNumberToAnswer("0"));
        button_1.setOnClickListener(view -> addNumberToAnswer("1"));
        button_2.setOnClickListener(view -> addNumberToAnswer("2"));
        button_3.setOnClickListener(view -> addNumberToAnswer("3"));
        button_4.setOnClickListener(view -> addNumberToAnswer("4"));
        button_5.setOnClickListener(view -> addNumberToAnswer("5"));
        button_6.setOnClickListener(view -> addNumberToAnswer("6"));
        button_7.setOnClickListener(view -> addNumberToAnswer("7"));
        button_8.setOnClickListener(view -> addNumberToAnswer("8"));
        button_9.setOnClickListener(view -> addNumberToAnswer("9"));

        button_delete = findViewById(R.id.Bouton_Supprimer);
        button_clear = findViewById(R.id.Bouton_Effacer);
        button_verify = findViewById(R.id.Bouton_Verifier);

        button_delete.setOnClickListener(view -> deleteLastNumber());
        button_clear.setOnClickListener(view -> clearAnswer());
        button_verify.setOnClickListener(view -> {
            verify();
        });
    }

    public void setLives(int lives) {
        viesTextView.setText(String.valueOf(lives));
    }

    public void setScore(int score) {
        scoreTextView.setText(String.valueOf(score));
    }

    private void setSupportActionBar() {
    }

    private String generateCalcul() {
        int firstInt = (int) (Math.random() * 10);
        int secondInt = (int) (Math.random() * 10);

        String[] listOperators = {"+", "-", "*"};

        String randomOperator = listOperators[(int) (Math.random() * listOperators.length)];

        // Assurer que le calcul donne un résultat positif
        switch (randomOperator) {
            case "-":
                // S'assurer que firstInt est toujours supérieur ou égal à secondInt pour éviter les négatifs
                if (firstInt < secondInt) {
                    int temp = firstInt;
                    firstInt = secondInt;
                    secondInt = temp;
                }
                break;
            // Pas besoin de changer pour + et * car ils produisent toujours des résultats positifs avec des entiers positifs
        }

        String calcul = firstInt + " " + randomOperator + " " + secondInt;

        return calcul;
    }


    private int checkAnswer(String calculus) {
        String[] parts = calculus.split(" ");
        int firstInt = Integer.parseInt(parts[0]);
        int secondInt = Integer.parseInt(parts[2]);
        String operator = parts[1];
        int result = 0;

        switch (operator) {
            case "+":
                result = firstInt + secondInt;
                break;
            case "-":
                result = firstInt - secondInt;
                break;
            case "*":
                result = firstInt * secondInt;
                break;
        }

        return result;
    }

    private void addNumberToAnswer(String number) {
        if (reponseTextView.getText().toString().length() >= 2) {
            return;
        }
        String currentAnswer = reponseTextView.getText().toString();
        reponseTextView.setText(currentAnswer + number);
    }

    private void deleteLastNumber() {
        String currentAnswer = reponseTextView.getText().toString();
        if (currentAnswer.length() > 0) {
            reponseTextView.setText(currentAnswer.substring(0, currentAnswer.length() - 1));
        }
    }

    private void verify() {
        if (reponseTextView.getText().toString().length() == 0) {
            return;
        }

        if (nombreVies == 0) {
            return;
        }

        int reponse = Integer.parseInt(reponseTextView.getText().toString());
        if (reponse == calculMentalReponse) {
            score++;
            setScore(score);
            clearAnswer();
            calculMental = generateCalcul();
            calculMentalTextView.setText(calculMental);
            calculMentalReponse = checkAnswer(calculMental);
        } else {
            nombreVies--;
            setLives(nombreVies);
            if (nombreVies == 0) {
                Intent intent = new Intent(this, RegisterScore.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
            clearAnswer();
        }
    }
}
