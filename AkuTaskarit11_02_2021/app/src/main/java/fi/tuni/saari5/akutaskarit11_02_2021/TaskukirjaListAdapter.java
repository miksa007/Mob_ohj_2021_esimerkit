package fi.tuni.saari5.akutaskarit11_02_2021;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class TaskukirjaListAdapter extends ArrayAdapter<Taskukirja> {
    private final static String TAG="AkuArrayAdapter";
    public TaskukirjaListAdapter(Context context, int resource, List<Taskukirja> objects) {
        super(context, resource, objects);
        Log.d("nyt", " ollaan taalla adapterin konstruktorissa");
    }

    /**
     * Rakennetaan yhden rivin näkymä ListView:iin...
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.recyclerview_item, parent, false);
        }
        TextView numeroTextView = (TextView) convertView.findViewById(R.id.numero_textView);
        TextView nimiTextView = (TextView) convertView.findViewById(R.id.nimi_textView);
        Taskukirja taskukirja=getItem(position);

        numeroTextView.setVisibility(View.VISIBLE);
        numeroTextView.setText(""+taskukirja.getNumero());
        nimiTextView.setVisibility(View.VISIBLE);
        nimiTextView.setText(taskukirja.getNimi());

        return convertView;
    }

}
/*
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
*/
