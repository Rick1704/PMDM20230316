package fp.dam.pmdm.examen20230316;

public class MoverFigura implements Runnable{

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
