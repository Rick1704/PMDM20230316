package fp.dam.pmdm.examen20230316;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SegundoFragment extends Fragment {

    private ListView listView;
    private Button buttonConsulta1;
    private Button buttonGPS;
    private Button buttonConsulta2;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentodos, container, false);

        listView = view.findViewById(R.id.listView);
        buttonConsulta1 = view.findViewById(R.id.button1);
        buttonGPS = view.findViewById(R.id.button2);
        buttonConsulta2 = view.findViewById(R.id.button3);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("ClassicModelsV2/customers/103/customerName");
        buttonConsulta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("ClassicModelsV2");

// Obtener los datos de la base de datos
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Iterar sobre los datos y mostrarlos en el ListView
                        List<Customer> customers = new ArrayList<>();
                        for (DataSnapshot customerSnapshot : snapshot.getChildren()) {
                            Customer customer = customerSnapshot.getValue(Customer.class);
                            customers.add(customer);
                        }
                        CustomerAdapter adapter = new CustomerAdapter(getContext(), customers);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Manejar el error
                    }
                });
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
        return view;
    }

}
