package fi.tuni.saari5.akutaskarit11_02_2021;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Taskukirja.class}, version = 3, exportSchema = false)
public abstract class TaskukirjaRoomDatabase extends RoomDatabase {
    public abstract TaskukirjaDao taskukirjaDao();
    private static volatile  TaskukirjaRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseWriteExecutor=
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static TaskukirjaRoomDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (TaskukirjaRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            TaskukirjaRoomDatabase.class,"taskukirja_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }

            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() ->{
                TaskukirjaDao dao= INSTANCE.taskukirjaDao();
                dao.deleteAll();

                Taskukirja taskukirja = new Taskukirja(1, "Mikki kiipelissä");
                dao.insert(taskukirja);
            });
        }
    };


}
