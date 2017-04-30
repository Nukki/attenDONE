package codes.nukki.scan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.Map;

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
        Map<String, Integer> pref = (Map<String,Integer>)getIntent().getSerializableExtra("missed");
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra("missed", (Serializable) pref);
        startActivity(intent);

    }

    public void onExitClicked( View v) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);

    }
}
