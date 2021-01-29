package fi.tuni.saari5.testilogcat28_01_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButtonToggleGroup;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    private final String LOG="Miksansovellus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG, "Sovellus käynnistyy (v2)");

        EditText nimi = findViewById(R.id.NimieditText);
        TextView tervehdys=findViewById(R.id.ViestitextView);

        Button nappula = findViewById(R.id.buttonnappula);
        nappula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG,"Nappulaa painettu");
                Editable arvo = nimi.getText();
                tervehdys.setText("Terve "+ arvo.toString());

            }
        });

        Button siirto=findViewById(R.id.button2);
        siirto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, toinenActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        String message = "Terve"; //editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG,"ollaan onStart() -metodissa");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG, "Ollaan onResume() -metodissa");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG,"Ollaan onStop() -metodissa");
    }
}