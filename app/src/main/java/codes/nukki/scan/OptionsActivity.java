package codes.nukki.scan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void onStartClicked( View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onHistoryClicked( View v) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);

    }

    public void onExitClicked( View v) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);

    }
}
