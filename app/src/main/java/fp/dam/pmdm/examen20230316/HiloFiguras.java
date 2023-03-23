package fp.dam.pmdm.examen20230316;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class HiloFiguras implements Runnable {
    private static final float FPS = 60;
    private static final float NPF = 1000000000F / FPS;
    private final SurfaceHolder holder;
    private final float ancho, radio, alto, posicionY, posicionX,velocidadAngular;
    private final Paint paint;
    private final int velocidad;
    private PoligonoRegular poligono;
    private Circulo circulo;
    private volatile boolean fin;
    private Thread gameLoop;


    public HiloFiguras(SurfaceHolder holder, int ancho, int alto) {
        this.holder = holder;
        this.ancho = ancho;
        this.alto = alto;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        posicionY = alto / 2f;
        posicionX = ancho / 2f;
        radio = 3579 * ancho/57931;
        velocidad = 17313 * ancho/57931;
        velocidadAngular = velocidad/radio;
    }

    public void iniciar() {
        gameLoop = new Thread(this);
        poligono = new PoligonoRegular(posicionX, posicionY - radio, 5, radio, velocidad, this);
        circulo = new Circulo(posicionX, posicionY - radio, radio, velocidad, 0, Color.BLACK, this);
        gameLoop.start();
    }

    @Override
    public void run() {
        fin = false;
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

    private void siguiente(float lapso) {
        circulo.mover(lapso);
        poligono.mover(lapso, velocidadAngular);
    }

    private void pintar(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint paintLinea = new Paint();
        paintLinea.setStyle(Paint.Style.STROKE);
        paintLinea.setStrokeWidth(6);
        paintLinea.setColor(Color.BLUE);
        canvas.drawLine(0, posicionY, ancho, posicionY, paintLinea);
        circulo.paint(canvas);
        poligono.dibujar(canvas);
    }

    private void pintar() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            synchronized (holder) {
                pintar(canvas);
            }
        } catch (Exception e) {
        } finally {
            if (canvas != null) holder.unlockCanvasAndPost(canvas);
        }
    }

    public float getAncho() {
        return ancho;
    }

}
