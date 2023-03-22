package fp.dam.pmdm.examen20230316;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class JuegoThread implements Runnable {
    private static final float FPS = 60;
    private static final float NPF = 1000000000F / FPS;
    private PoligonoRegular poligono;
    private SurfaceHolder holder;
    private float width;
    private float height;
    private Pelota pelota;
    private Paint paint;
    private volatile boolean fin;
    private Thread gameLoop;
    private float vHorizontal = 200; // velocidad horizontal del polígono
    private float lineaY; // posición vertical de la línea

    // Constructor de la clase
    public JuegoThread(SurfaceHolder holder, int width, int height) {
        this.holder = holder;
        this.width = width;
        this.height = height;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        lineaY = height / 2f;
        poligono = new PoligonoRegular(width / 2f,(height / 2f)-300, 5, 300, Color.BLACK, 200,this);
        pelota = new Pelota(width / 2f,(height / 2f)-300,300,100,-23,Color.BLACK,this);
    }

    // Método que inicia el hilo
    public void iniciar() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    // Método que se ejecuta en el hilo
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

    // Métodos auxiliares
    private void siguiente(float lapso) {
        pelota.mover(lapso);
        poligono.mover(lapso);
        }

        private void pintar (Canvas canvas){
            canvas.drawColor(Color.BLUE);
            // Dibuja la línea horizontal en el centro de la pantalla
            Paint paint2 = new Paint();
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setStrokeWidth(10);
            paint2.setColor(Color.WHITE);
            canvas.drawLine(0, lineaY, width, lineaY, paint2);
            // Dibuja el polígono
            pelota.paint(canvas);
            poligono.dibujar(canvas);
        }

        private void pintar () {
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                synchronized (holder) {
                    pintar(canvas);
                }
            } catch (Exception e) {
            } finally {
                if (canvas != null)
                    holder.unlockCanvasAndPost(canvas);
            }
        }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
