package fi.tuni.saari5.akutaskarit11_02_2021;

import android.app.Application;
import android.os.AsyncTask;

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

    public void deleteAll()  {
        new deleteAllTaskukirjasAsyncTask(mTaskukirjaDao).execute();
    }

    private static class deleteAllTaskukirjasAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskukirjaDao mAsyncTaskDao;
        deleteAllTaskukirjasAsyncTask(TaskukirjaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override    protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();        return null;
        }
    }
}
