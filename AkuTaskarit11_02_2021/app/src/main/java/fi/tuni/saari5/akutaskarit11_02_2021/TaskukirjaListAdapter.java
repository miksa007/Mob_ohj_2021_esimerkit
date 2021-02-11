package fi.tuni.saari5.akutaskarit11_02_2021;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.google.android.material.snackbar.Snackbar;

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
        holder.bind(current);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("softa", "rivillä "+position+" tapahtui klikki" );
                Snackbar.make(v, "Valittiin:"+current.getNumero(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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