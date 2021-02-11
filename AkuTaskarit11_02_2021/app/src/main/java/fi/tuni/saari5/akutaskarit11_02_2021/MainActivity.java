package fi.tuni.saari5.akutaskarit11_02_2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
//import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_TASKUKIRJA_ACTIVITY_REQUEST_CODE = 1;

    private TaskukirjaViewModel mTaskukirjaViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("softa", "Alkoi");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TaskukirjaListAdapter adapter = new TaskukirjaListAdapter(new TaskukirjaListAdapter.TaskukirjaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("softa", "Alkoi 2");

        //Gradle versioissa vanha toteutus toimii, mutta allaoleva uusi ei
        //mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        //Ohjeen alkuperäinen ei toimi
        //mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        //Uusin toimii (issues 155)
        mTaskukirjaViewModel = new ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(this.getApplication())).get(TaskukirjaViewModel.class);

        // Update the cached copy of the words in the adapter.
        mTaskukirjaViewModel.getKaikkiTaskukirjat().observe(this, taskukirjas -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(taskukirjas);
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewTaskukirjaActivity.class);

            startActivityForResult(intent, NEW_TASKUKIRJA_ACTIVITY_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TASKUKIRJA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String arvot=data.getStringExtra(NewTaskukirjaActivity.EXTRA_REPLY);
            String[] osa=arvot.split(";");
            int numero=Integer.parseInt(osa[0]);

            Taskukirja taskukirja=new Taskukirja(numero, osa[1]);
            mTaskukirjaViewModel.insert(taskukirja);



        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}