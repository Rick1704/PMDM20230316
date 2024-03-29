package fp.dam.pmdm.examen20230316;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;


class MoverFiguras extends SurfaceView implements SurfaceHolder.Callback{

    public MoverFiguras(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        HiloFiguras hiloFiguras = new HiloFiguras(holder, getWidth(), getHeight());
        hiloFiguras.iniciar();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

}

