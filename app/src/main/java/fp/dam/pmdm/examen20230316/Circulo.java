package fp.dam.pmdm.examen20230316;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Circulo {
    private float x;
    private float y;
    private float radio;
    private float vx;
    private float vy;
    private HiloFiguras juego;
    private Paint paint;

    public Circulo(float x, float y, float radio, float v, float dir, int color, HiloFiguras juego) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        vx = v * (float) Math.cos(dir);
        vy = v * (float) Math.sin(dir);
        this.juego = juego;
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
    }

    public void mover(float lapso) {
        x += vx * lapso / 1000000000f;
        if (x + radio >= juego.getAncho()) {
            x -= (x + radio - juego.getAncho()) * 2;
            vx = -vx;
        } else if (x - radio <= 0) {
            x += (radio - x) * 2;
            vx = -vx;
        }
    }

    public void paint(Canvas canvas) {
        canvas.drawCircle(x, y, radio, paint);
    }
}
