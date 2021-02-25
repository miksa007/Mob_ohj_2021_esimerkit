package fi.tuni.saari5.autentikointi25_02_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private final String TAG = "softa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //osa 1
    public void kirjauduSisaan(View view) {
        Log.d(TAG, "kirjaudu nappulaa painettu");
        createSignInIntent();
    }

    //osa 1
    //Kutsutaan kirjautumisikkunaa
    public void createSignInIntent() {
        Log.d(TAG, "autetikointia alkoi");
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());
//                new AuthUI.IdpConfig.PhoneBuilder().build(),
//                new AuthUI.IdpConfig.GoogleBuilder().build(),
//                new AuthUI.IdpConfig.FacebookBuilder().build(),
//                new AuthUI.IdpConfig.TwitterBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_create_intent]
    }

    @Override
    //Otetaan vastaus kirjaumisikkunasta
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "resulttia pukkaa");
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "resulttia pukkaa RC_SIGN_IN ok");
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                Log.d(TAG, "resulttia pukkaa RESULT ok");
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                Log.d(TAG, "resulttia pukkaa - ei onnistunut");
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    //Nappulalle toimintaa
    public void kirjautumisenTila(View view) {
        Log.d(TAG, "tilakysely");
        checkCurrentUser();
    }
    //Kirjautumisen tilan tarkastelu
    public void checkCurrentUser() {
        // [START check_current_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView textView = findViewById(R.id.textView);
        if (user != null) {
            textView.setText("sisällä "+user.getEmail()+" "+user.getUid());
            Log.d(TAG, user.getEmail());
            Log.d(TAG, user.getUid());
            // User is signed in
        } else {
            textView.setText("Ei kirjautunut");
            // No user is signed in
        }
        // [END check_current_user]
    }

    // Log out nappulan toiminta
    public void kirjauduUlos(View view) {
        Log.d(TAG, "kirjaudu ulos nappulaa painettu");
        signOut();

    }
    //uloskirjautuminen
    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Log.d(TAG, "uloskirjauduttu");
                    }
                });
        // [END auth_fui_signout]
    }

    //lifecycle juttu, eli kun ohjelma siirtyy taustalle, niin se samalla uloskirjautuu
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop metodissa");
        signOut();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Ondestroy metodissa");
        //Tälle ei ollut tarvetta, kun uloskirjautuminen tehdään jo OnStop -metodissa
        //signOut();
    }

    /**
     * Testataan firestoren rules säännön toimivuutta kirjoittamalla data olemassa olevaan tietokantaan
     * Jos kirjoitus ei onnistu, niin Logcatistä löytyy ilmoituksia...
     * @param view
     */
    public void LisaaDataa(View view) {
        Log.d(TAG, "lisaa tietoa");
        //datan lisays kokeilu
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        CollectionReference akuReference = mFirestore.collection("torstai_Akut");
        akuReference.add(new Aku("113","Torstai testi 2"));
    }
}
class Aku{
    private String kirjanNumero;
    private String kirjanNimi;

    public Aku(String kirjanNumero, String kirjanNimi) {
        this.kirjanNumero = kirjanNumero;
        this.kirjanNimi = kirjanNimi;
    }
    public String getKirjanNimi() {
        return kirjanNimi;
    }

    public void setKirjanNimi(String kirjanNimi) {
        this.kirjanNimi = kirjanNimi;
    }

    public String getKirjanNumero() {
        return kirjanNumero;
    }

    public void setKirjanNumero(String kirjanNumero) {
        this.kirjanNumero = kirjanNumero;
    }
}
