package fi.tuni.saari5.testiroom04_02_2021;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")

    private String mWord;

    public Word(@NonNull String word) {this.mWord = word;
        Log.d("softa","13");
    }

    public String getWord(){return this.mWord;}
}


