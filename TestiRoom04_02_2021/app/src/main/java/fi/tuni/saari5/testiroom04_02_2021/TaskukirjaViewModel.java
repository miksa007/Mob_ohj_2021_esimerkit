package fi.tuni.saari5.testiroom04_02_2021;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskukirjaViewModel extends AndroidViewModel {
    private TaskukirjaRepository mRepository;

    private final LiveData<List<Taskukirja>> mKaikkiTaskukirjat;

    public TaskukirjaViewModel(@NonNull Application application) {
        super(application);
        mRepository=new TaskukirjaRepository(application);
        mKaikkiTaskukirjat=mRepository.getKaikkiTaskukirjat();
    }
    LiveData<List<Taskukirja>> getKaikkiTaskukirjat(){

        return mKaikkiTaskukirjat;
    }
    public void insert(Taskukirja taskukirja){

        mRepository.insert(taskukirja);
    }
}
