package fp.dam.pmdm.examen20230316;

import android.graphics.Paint;
import android.view.SurfaceHolder;

public class MoverFigura implements Runnable{

    private SurfaceHolder holder;
    private float width;
    private float height;
    private volatile boolean fin;
    private float vAngular = 35;
    private float angulo = 0;
    private float px;
    private float py;
    static float FPS = 60;
    static float NPF = 1000000000F / FPS;

    public void run() {
        boolean fin = false;
        long t0 = System.nanoTime(), t1, lapso;
        float nanos = 0;
        while (!fin) {
            t1 = System.nanoTime();
            lapso = t1 - t0;
            t0 = t1;
            nanos += lapso;
            if (nanos >= NPF) {
                nanos -= NPF;
                siguiente(NPF);
                pintar();
            }
        }
    }

    private void pintar() {
    }


    private void siguiente(float lapso) {

    }
}
