package fp.dam.pmdm.examen20230316;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private FragmentManager manager;
    private Fragment FragmentoActual, PrimerFragmento, SegundoFragment;
    private float posicionY;

    @SuppressLint("ClickableViewAccessibility")
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
        FrameLayout contenedorFragment = findViewById(R.id.fragment_container);

        PrimerFragmento = new PrimerFragment();
        SegundoFragment = new SegundoFragment();
        FragmentoActual = PrimerFragmento;
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, FragmentoActual).commit();

        contenedorFragment.setOnTouchListener(this::onTouch);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                posicionY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float PosicionActualY = motionEvent.getY();
                if (PosicionActualY > posicionY) {
                    if (FragmentoActual != PrimerFragmento) {
                        FragmentoActual = PrimerFragmento;
                        manager.beginTransaction().replace(R.id.fragment_container, FragmentoActual).commit();
                    }
                } else if (PosicionActualY < posicionY) {
                    if (FragmentoActual != SegundoFragment) {
                        FragmentoActual = SegundoFragment;
                        manager.beginTransaction().replace(R.id.fragment_container, FragmentoActual).commit();
                    }
                }
                break;
        }
        return true;
    }
}