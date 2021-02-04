package fi.tuni.saari5.testiroom04_02_2021;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TaskukirjaViewHolder extends RecyclerView.ViewHolder {
    private final TextView taskukirjaItemView;
    private TaskukirjaViewHolder(View itemView){
        super(itemView);
        taskukirjaItemView=itemView.findViewById(R.id.textView);

    }

    public void bind(String text){
        taskukirjaItemView.setText(text);
    }
    static TaskukirjaViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TaskukirjaViewHolder(view);
    }
}
