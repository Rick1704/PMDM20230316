package fp.dam.pmdm.examen20230316;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Fragmento2 extends Fragment implements LocationListener {
     private Button consulta1;
     private Button consulta2;
    private Button gps;

    private LocationManager manager;
    private Geocoder geocoder;

    public Fragmento2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento2, container, false);
        consulta1 = (Button) v.findViewById(R.id.consulta1);
        consulta2 = (Button) v.findViewById(R.id.consulta2);
        gps = (Button) v.findViewById(R.id.gps);

        consulta1.setOnClickListener(this::consulta1);
        consulta2.setOnClickListener(this::consulta2);
        gps.setOnClickListener(this::consultagps);


        return v;
    }

    public void consulta1(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference name = database.getReference("ClassicModelsV2/customers/103/customerName");
        Datos datos = new Datos();

        name.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //textView.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        List<Datos> da = Arrays.asList(new Datos(name.toString(),0));
        AdaptadorLista li = new AdaptadorLista(getContext(),R.layout.contenidolist,da);

    }

    public void consulta2(View v) {

    }

    public void consultagps(View v) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1)
            if (grantResults.length != 2 || grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                //coordenadas.setText("Sin permiso para usar el GPS");
            } else checkProvider();
    }

    private void checkProvider() {
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) iniciarListener();
        else {
            new AlertDialog.Builder(getContext()).setTitle("Ubicación deshabilitada").setMessage("¿Desea habilitar ubicación?").setPositiveButton("Aceptar", (iface, id) -> {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(i, 2);
            }).setNegativeButton("Cancelar", (iface, id) -> {
               // coordenadas.setText("Ubicación deshabilitada");
            }).show();
        }
    }





    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        double alt = location.getAltitude();

        new GeocoderTask().execute(lat, lon);
    }

    @SuppressLint("MissingPermission")
    private void iniciarListener() {
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) onLocationChanged(location);
        //else coordenadas.setText("Esperando coordenadas");
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        manager.removeUpdates(this);
    }

    private class GeocoderTask extends AsyncTask<Double, Void, List<Address>> {
        @Override
        protected List<Address> doInBackground(Double... coord) {
            try {
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                return geocoder.getFromLocation(coord[0], coord[1], 5);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {
            super.onPostExecute(addresses);
            if (addresses == null || addresses.isEmpty())
                Toast.makeText(getContext(), "Error obteniendo dirección: ", Toast.LENGTH_LONG).show();
            else {
               // direcciones.setText("Direcciones (");
               // direcciones.append(String.valueOf(addresses.size()));
               // direcciones.append(")");
               // adapter.actualizar(addresses);
            }
        }
    }
}

