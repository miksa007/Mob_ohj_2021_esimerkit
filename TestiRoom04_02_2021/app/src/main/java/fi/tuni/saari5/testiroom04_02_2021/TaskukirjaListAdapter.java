package fi.tuni.saari5.testiroom04_02_2021;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class TaskukirjaListAdapter extends ListAdapter<Taskukirja, TaskukirjaViewHolder> {
    public TaskukirjaListAdapter(@NonNull DiffUtil.ItemCallback<Taskukirja> diffcalback){
        super(diffcalback);
    }

    @Override
    public TaskukirjaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TaskukirjaViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(TaskukirjaViewHolder holder, int position) {
        Taskukirja current =getItem(position);
        holder.bind(current.getNumero()+"."+current.getNimi());

    }
    static class TaskukirjaDiff extends DiffUtil.ItemCallback<Taskukirja>{
        @Override
        public boolean areItemsTheSame(@NonNull Taskukirja oldItem, @NonNull Taskukirja newItem) {
            return oldItem==newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Taskukirja oldItem, @NonNull Taskukirja newItem) {
            return oldItem.getNimi().equals(newItem.getNimi());
        }
    }
}