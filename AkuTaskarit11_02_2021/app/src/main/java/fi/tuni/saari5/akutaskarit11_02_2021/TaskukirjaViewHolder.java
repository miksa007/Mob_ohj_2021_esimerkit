package fi.tuni.saari5.akutaskarit11_02_2021;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TaskukirjaViewHolder extends RecyclerView.ViewHolder {
    private final TextView taskukirjaNroItemView;
    private final TextView taskukirjaNimiItemView;
    private TaskukirjaViewHolder(View itemView){
        super(itemView);
        taskukirjaNroItemView=itemView.findViewById(R.id.numero_textView);
        taskukirjaNimiItemView=itemView.findViewById(R.id.nimi_textView);

    }

    public void bind(Taskukirja taskukirja){

        taskukirjaNroItemView.setText(""+taskukirja.getNumero());
        taskukirjaNimiItemView.setText(taskukirja.getNimi());
    }
    static TaskukirjaViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TaskukirjaViewHolder(view);
    }
}
