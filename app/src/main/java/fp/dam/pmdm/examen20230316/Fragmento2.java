package fp.dam.pmdm.examen20230316;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragmento2 extends Fragment {
    Button consulta1;
    Button consulta2;
    Button gps;
    public Fragmento2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento2, container, false);
        consulta1 = (Button) v.findViewById(R.id.consulta1);
        consulta2 = (Button) v.findViewById(R.id.consulta2);
        gps = (Button) v.findViewById(R.id.gps);


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
