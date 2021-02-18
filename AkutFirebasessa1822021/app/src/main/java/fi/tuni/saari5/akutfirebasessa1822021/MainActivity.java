package fi.tuni.saari5.akutfirebasessa1822021;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "softa";

    private EditText nro;
    private EditText nimi;
    private List<Aku> kaikkiAkut;
    private ListView mAkuListView;

    private Context context;

    private ArrayAdapter mAkuListAdapter;

    //private FirebaseDatabase mFirebaseDatabase;
    //private DatabaseReference mAkussDatabaseReference;

    private FirebaseFirestore mFirestore;
    private CollectionReference akuReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        mFirestore = FirebaseFirestore.getInstance();
        akuReference = mFirestore.collection("torstai_Akut");

        nro = findViewById(R.id.editTextNumero);
        nimi = findViewById(R.id.editTextNimi);
        mAkuListView = (ListView) findViewById(R.id.listView);

        kaikkiAkut = new ArrayList<>();
        muutosKysely();
    }

    public void muutosKysely(){
        mFirestore.collection("torstai_Akut").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                kaikkiAkut.clear();
                Log.d(TAG, "nakyman paivitys?");
                if(queryDocumentSnapshots!=null) {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                        Aku aku = snapshot.toObject(Aku.class);
                        kaikkiAkut.add(aku);

                    }
                    mAkuListAdapter = new AkuArrayAdapter(context, R.layout.item_aku, kaikkiAkut);
                    mAkuListAdapter.notifyDataSetChanged();
                    mAkuListView.setAdapter(mAkuListAdapter);
                }else {
                    Log.d(TAG, "Querysnapshot on null");
                }
            }
        });
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
                akuReference = mFirestore.collection("torstai_Akut");
                akuReference.add(aku);

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