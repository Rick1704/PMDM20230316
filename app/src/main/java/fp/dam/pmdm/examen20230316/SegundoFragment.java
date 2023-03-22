package fp.dam.pmdm.examen20230316;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SegundoFragment extends Fragment {


    private ListView listView;
    private Button buttonConsulta1;
    private Button buttonGPS;
    private Button buttonConsulta2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragmentodos, container, false);

        // Obtener una referencia a los elementos de la interfaz de usuario
        listView = rootView.findViewById(R.id.listView);
        buttonConsulta1 = rootView.findViewById(R.id.button1);
        buttonGPS = rootView.findViewById(R.id.button2);
        buttonConsulta2 = rootView.findViewById(R.id.button3);

        // Agregar un OnClickListener a los botones
        buttonConsulta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Manejar el evento onClick
            }
        });

        buttonGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Manejar el evento onClick
            }
        });

        buttonConsulta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Manejar el evento onClick
            }
        });

        // Obtener una referencia a la base de datos
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("ClassicModelsV2");

        return rootView;

    }
}
