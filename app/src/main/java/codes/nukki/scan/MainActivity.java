package codes.nukki.scan;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends Activity {
    HashMap<String, Student> students = new HashMap<>();
    ListView attendance_list;

    // list of NFC technologies detected:
    private final String[][] techList = new String[][] {
            new String[] {
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNewStudent("Aamir Aditya", "89724892", "04265ABAA63780");
        addNewStudent("Henrike Abigaia", "64829482", "04272B63790");
        addNewStudent("Sander Aemilia", "7982132", "04245ABA863180");
        addNewStudent("Temur Czeslaw", "98173893", "04270ABAB63780");
        addNewStudent("Lakeisha Dean", "24984878", "04275AYAB65780");
        addNewStudent("Miroslav Dipak", "18971748", "04275BVAB63780");
        addNewStudent("Barta Ellis", "98726347", "03275ABAB63780");
        addNewStudent("Mattithiah Izem", "87319374", "15275ABAB63780");
        addNewStudent("Nikki Jack", "18397824", "04275ABAB63780");
        addNewStudent("Dandan Lin", "9027389", "04216162583F80");
        addNewStudent("Fritz Sibylle", "81398010", "44274ABAB63780");
        addNewStudent("Esther Song", "8917812", "040E63EA651E80");
        addNewStudent("Weland Sophronia", "80193803", "84275ABAB63780");
        addNewStudent("Sayaka Tamura", "29489244", "84275A3EW03780");
        addNewStudent("Melvyn Teofilo", "08839679", "04275ATAB73780");

        attendance_list = (ListView) findViewById(R.id.attendance_sheet);
        attendance_list.setAdapter(new AttendeeListAdapter(this, students));
    }



    @Override
    protected void onResume() {
        super.onResume();
        // creating pending intent:
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // creating intent receiver for NFC events:
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        // enabling foreground dispatch for getting intent from NFC event:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, this.techList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // disabling foreground dispatch:
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            String tag = ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));

            if (students.containsKey(tag)) {
                //((TextView)findViewById(R.id.text)).setText(students.get(tag).name);
                students.get(tag).status = true;
                attendance_list.setAdapter(new AttendeeListAdapter(this, students));
            }
        }
    }

    private String ByteArrayToHexString(byte [] inarray) {
        int i, j, in;
        String [] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String out= "";

        for(j = 0 ; j < inarray.length ; ++j)
        {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

    private void addNewStudent(String name, String id, String seriaNum){
        Student newStudent = new Student();
        newStudent.name = name;
        newStudent.ID = id;
        newStudent.status = false;

        students.put(seriaNum, newStudent);
    }

}