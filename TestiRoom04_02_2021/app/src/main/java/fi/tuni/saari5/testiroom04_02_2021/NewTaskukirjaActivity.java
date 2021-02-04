package fi.tuni.saari5.testiroom04_02_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskukirjaActivity extends AppCompatActivity {
    public static final String LOG="softa:NewTask..Activity";
    public static final String EXTRA_REPLY = "fi.tuni.saari5.testiroom04_02_2021.REPLY";

    private EditText mEditNumeroView;
    private  EditText mEditNimiView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_taskukirja);
        mEditNumeroView = findViewById(R.id.edit_numero);
        mEditNimiView = findViewById(R.id.edit_nimi);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNumeroView.getText()) || TextUtils.isEmpty(mEditNimiView.getText())) {
                    Log.d(LOG,"ei arvoja");
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    Log.d(LOG,"arvoja");
                    int numero=Integer.parseInt(mEditNumeroView.getText().toString());
                    String nimi = mEditNimiView.getText().toString();
                    Log.d(LOG,""+numero+" "+nimi);
                    replyIntent.putExtra(EXTRA_REPLY, (""+numero+";"+nimi));
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }
}