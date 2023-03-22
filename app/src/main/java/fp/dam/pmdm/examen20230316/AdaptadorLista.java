package fp.dam.pmdm.examen20230316;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorLista extends ArrayAdapter<Datos> {

    public AdaptadorLista(@NonNull Context context, int resource, @NonNull List<Datos> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Datos d = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.contenidolist, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.textView)).setText(d.getNombreCustomer());
        ((TextView) convertView.findViewById(R.id.nombre)).setText(d.getImporte());
        return convertView;
    }
}
