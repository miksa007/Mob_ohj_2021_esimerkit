package fi.tuni.saari5.akutfirebasessa1822021;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AkuArrayAdapter extends ArrayAdapter<Aku> {
    private final static String TAG="softa - AkuArrayAdapter";

    //TODO constructor
    public AkuArrayAdapter(Context context, int resource, List<Aku> objects) {
        super(context, resource, objects);
        Log.d("nyt", " ollaan taalla adapterin konstruktorissa");
    }

    //TODO getView
    //Rakennetaan yhden rivin näkymä ListView:iin...
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_aku, parent, false);
        }
        TextView numeroTextView = (TextView) convertView.findViewById(R.id.numeroTextView);
        TextView nimiTextView = (TextView) convertView.findViewById(R.id.nimiTextView);
        Aku taskukirja=getItem(position);

        numeroTextView.setVisibility(View.VISIBLE);
        numeroTextView.setText(""+taskukirja.getKirjanNumero());
        nimiTextView.setVisibility(View.VISIBLE);
        nimiTextView.setText(taskukirja.getKirjanNimi());

        return convertView;
    }

}
