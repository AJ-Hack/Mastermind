package ie.corktrainingcentre.mastermind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void btnNewGameClicked(View v) {
        startActivity(new Intent(this, NewGameActivity.class));
    }

    public void btnHighScoresClicked(View v) {
        Log.d("btnHighScoresClicked", "CLICKED");
    }

    public void btnSettingsClicked(View v) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void btnHowToPlayClicked(View v) {
        Log.d("btnHowToPlayClicked", "CLICKED");
    }

    public void btnExitClicked(View v) {
        System.exit(0);
    }
}
