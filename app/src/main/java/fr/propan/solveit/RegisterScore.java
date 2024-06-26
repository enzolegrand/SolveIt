package fr.propan.solveit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import fr.propan.solveit.database.GameBaseHelper;
import fr.propan.solveit.database.GameHistoryDao;
import fr.propan.solveit.entities.GameHistory;

public class RegisterScore extends AppCompatActivity {

    private TextView score;
    private TextView lifes;
    private Button saveBtn;
    private EditText username;

    private GameHistoryDao gameHistoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_score);

        gameHistoryDao = new GameHistoryDao(new GameBaseHelper(this, "db", 1));

        TextView scoreTextView = findViewById(R.id.score_textview);
        TextView livesTextView = findViewById(R.id.lifes_textview);
        username = findViewById(R.id.plainTextUsername);
        saveBtn = findViewById(R.id.button_save_score);
        livesTextView.setText("0");

        Intent intent = getIntent();
        int scoreValue = intent.getIntExtra("score", 0); // Récupère l'int extra "score", valeur par défaut 0 si non trouvé
        scoreTextView.setText(String.valueOf(scoreValue));

        saveBtn.setOnClickListener(view -> {
            String usernameString = username.getText().toString();
            save(usernameString, scoreValue);
        });
    }

    public void save(String username, Integer score) {
        GameHistory toSave = new GameHistory();
        toSave.setUsername(username);
        toSave.setScore(score);
        toSave.setDate(new Date());
        gameHistoryDao.create(toSave);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
