package codes.nukki.scan;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        TextView label = (TextView) findViewById(R.id.text);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        Map<String, ?> pref = sharedPref.getAll();
        String output = "";
        for (Map.Entry<String, ?> entry : pref.entrySet()) {
            //System.out.println(“Key = ” + entry.getKey() + “, Value = ” + entry.getValue());
            output += entry.getKey() + " " + entry.getValue() + "\n";
        }
        Log.e("test"," " + sharedPref.getInt("Nikki Jack",0));
        label.setText(output);


    }
}
