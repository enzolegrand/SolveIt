package fr.propan.solveit;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.propan.solveit.database.GameBaseHelper;
import fr.propan.solveit.database.GameHistoryDao;
import fr.propan.solveit.entities.GameHistory;

public class HistoriqueActivity extends AppCompatActivity {

    private static final String TAG = "HistoriqueActivity";
    private GameHistoryDao gameHistoryDao;
    private TextView last_history;
    private LinearLayout layout;
    private ScrollView scroll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        last_history = findViewById(R.id.last_history);
        layout = findViewById(R.id.layout_history);
        gameHistoryDao = new GameHistoryDao(new GameBaseHelper(this, "db", 1));
        List<GameHistory> gameHistories = gameHistoryDao.getAllSortedByScore();

        if (gameHistories.size() > 0) {
            gameHistories.forEach(gameHistory -> {
                TextView textView = new TextView(this);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = gameHistory.getDate();
                String formattedDate = dateFormat.format(date);
                textView.setText(gameHistory.getUsername() + " - " + gameHistory.getScore() + " - " + formattedDate);
                textView.setTextColor(Color.WHITE);

                layout.addView(textView);
            });
        } else {
            last_history.setText("No history yet");
        }
    }
}
