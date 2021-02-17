package fi.tuni.saari5.akutfirebasessa1822021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "softa";

    private EditText nro;
    private EditText nimi;
    private List<Aku> kaikkiAkut;
    private ListView mAkuListView;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        nro = findViewById(R.id.editTextNumero);
        nimi = findViewById(R.id.editTextNimi);
        mAkuListView = (ListView) findViewById(R.id.listView);

    }

    /**
     * Metodi hoitaa kaikki OnClic - tapahtumat kayttoliittymassa
     *
     * @param view
     */
    public void onClick(View view) {
        Aku aku=null;

        switch (view.getId()) {
            case R.id.add:
                // save the new Aku to the database
                aku = new Aku();
                aku.setKirjanNumero(nro.getText().toString());
                aku.setKirjanNimi(nimi.getText().toString());

                //TODO DatabaseReferenceen tuupataan yllä luotu aku


                tyhjenna();


                //TODO korjaa ongelma, jossa toast jaa nappaimiston taakse piiloon
                //Viestia ruudulle tahtumasta
                //https://developer.android.com/guide/topics/ui/notifiers/toasts
                CharSequence text = aku.toString();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Log.d(TAG, "data: "+aku);
                break;
            case R.id.delete:
                Log.d("aku", "delete tuli");

                break;
        }
    }

    /**
     * Tyhjentaa tekstikentat
     */
    public void tyhjenna() {
        //tyhjennys
        nro.setText("");
        nimi.setText("");
    }
}