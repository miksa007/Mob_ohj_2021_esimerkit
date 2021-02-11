package fi.tuni.saari5.akutaskarit11_02_2021;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskukirjaRepository {
    private TaskukirjaDao mTaskukirjaDao;
    private LiveData<List<Taskukirja>> mKaikkiTaskukirjat;

    TaskukirjaRepository(Application application){
        TaskukirjaRoomDatabase db=TaskukirjaRoomDatabase.getDatabase(application);
        mTaskukirjaDao=db.taskukirjaDao();
        mKaikkiTaskukirjat=mTaskukirjaDao.getKaikkiTaskukirjat();
    }
    LiveData<List<Taskukirja>> getKaikkiTaskukirjat(){
        return mKaikkiTaskukirjat;
    }
    public void insert(Taskukirja taskukirja){
        TaskukirjaRoomDatabase.databaseWriteExecutor.execute(()->{
            mTaskukirjaDao.insert(taskukirja);
        });
    }

}
