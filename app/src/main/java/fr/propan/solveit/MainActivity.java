package fr.propan.solveit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button_jouer, button_historique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Remove the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // flag fullscreen
        getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN, android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        button_historique = findViewById(R.id.bouton_historique);
        button_jouer = findViewById(R.id.bouton_jouer);

        button_jouer.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        button_historique.setOnClickListener(view -> {
            Intent intent = new Intent(this, HistoriqueActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
