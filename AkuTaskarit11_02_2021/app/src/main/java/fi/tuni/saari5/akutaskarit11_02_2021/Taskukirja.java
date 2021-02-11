package fi.tuni.saari5.akutaskarit11_02_2021;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "taskukirja_table")
public class Taskukirja {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "numero")
    private int mNumero;

    @NonNull
    @ColumnInfo(name = "nimi")
    private String mNimi;

    public Taskukirja(@NonNull int mNumero, @NonNull String mNimi) {
        this.mNumero = mNumero;
        this.mNimi = mNimi;
    }

    public int getNumero() {
        return mNumero;
    }

    @NonNull
    public String getNimi() {
        return mNimi;
    }

    public void setNumero(int mNumero) {
        this.mNumero = mNumero;
    }

    public void setNimi(@NonNull String mNimi) {
        this.mNimi = mNimi;
    }

    @Override
    public String toString() {
        return "Taskukirja{" +
                "mNumero=" + mNumero +
                ", mNimi='" + mNimi + '\'' +
                '}';
    }
}


