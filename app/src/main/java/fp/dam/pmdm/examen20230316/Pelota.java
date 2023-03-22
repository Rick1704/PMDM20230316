package fp.dam.pmdm.examen20230316;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Pelota {
    private float x;
    private float y;
    private float radio;
    private float vx;
    private float vy;
    private JuegoThread juego;
    private Paint paint;

    public Pelota(float x, float y, float radio, float v, float dir, int color, JuegoThread juego) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        vx = v * (float) Math.cos(dir);
        vy = v * (float) Math.sin(dir);
        this.juego = juego;
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    public void mover(float lapso) {
        x += vx * lapso / 1000000000f;
        if (x + radio >= juego.getWidth()) {
            x -= (x + radio - juego.getWidth()) * 2;
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
