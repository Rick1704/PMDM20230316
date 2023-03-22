package fp.dam.pmdm.examen20230316;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOne extends Fragment {
    private MySurfaceView surfaceView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, container, false);
        surfaceView = new MySurfaceView(requireContext());
        FrameLayout frameLayout = view.findViewById(R.id.frame);
        frameLayout.addView(surfaceView);
        return view;
    }
}

