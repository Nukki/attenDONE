package codes.nukki.scan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Map;

public class HistoryActivity extends AppCompatActivity {
    TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        label = (TextView) findViewById(R.id.text);
        label.setText("YOOOO");
//        Map<String, ?> pref = (Map<String, ?>)getIntent().getSerializableExtra("missed");
//
//        Log.e("all prefs"," " + pref.toString());
//        String output = "";
//        for (Map.Entry<String, ?> entry : pref.entrySet()) {
//            output += entry.getKey() + "  " + entry.getValue() + "\n";
//        }
//        label.setText(output);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Map<String, ?> pref = (Map<String,Integer>)getIntent().getSerializableExtra("missed");

        if( pref != null)
        Log.e("all prefs"," " + pref.toString());

        String output = "";
        if (pref!= null) {
            for (Map.Entry<String, ?> entry : pref.entrySet()) {
                output += entry.getKey() + "  \t  " + entry.getValue() + "\n";
            }
        }
        label.setText(output);
    }

    public void onBack(View v) {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }
}
