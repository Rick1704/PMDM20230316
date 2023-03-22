package fp.dam.pmdm.examen20230316;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private FragmentManager fragmentManager;
    private FrameLayout fragmentContainer;
    private Fragment currentFragment;
    private Fragment fragmentOne;
    private Fragment fragmentTwo;
    private Button button;
    private float initialTouchY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("ClassicModelsV2/customers/103/customerName");
        TextView textView = findViewById(R.id.textView);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textView.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        fragmentContainer = findViewById(R.id.fragment_container);

        // Obtiene una referencia al botón
        button = findViewById(R.id.button);

        // Obtiene una instancia del FragmentManager
        fragmentManager = getSupportFragmentManager();

        // Crea las instancias de tus dos Fragments
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();

        // Muestra el primer Fragment en el contenedor
        currentFragment = fragmentOne;
        fragmentManager.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();

        // Configura el OnClickListener del botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFragment == fragmentOne) {
                    currentFragment = fragmentTwo;
                } else {
                    currentFragment = fragmentOne;
                }
                fragmentManager.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
            }
        });

        fragmentContainer.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Guarda la posición vertical inicial del toque
                    initialTouchY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float currentTouchY = event.getY();
                    if (currentTouchY > initialTouchY) {
                        // El usuario ha desplazado el dedo hacia abajo
                        if (currentFragment != fragmentOne) {
                            // Cambia al Fragment anterior
                            currentFragment = fragmentOne;
                            fragmentManager.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
                        }
                    } else if (currentTouchY < initialTouchY) {
                        // El usuario ha desplazado el dedo hacia arriba
                        if (currentFragment != fragmentTwo) {
                            // Cambia al siguiente Fragment
                            currentFragment = fragmentTwo;
                            fragmentManager.beginTransaction().replace(R.id.fragment_container, currentFragment).commit();
                        }
                    }
                    break;
            }
            return true;
        });
    }


}