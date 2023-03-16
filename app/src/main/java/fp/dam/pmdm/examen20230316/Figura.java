package fp.dam.pmdm.examen20230316;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Figura {

    protected float x;
    protected float y;
    protected final Paint paint = new Paint();
    protected float angulo = 0;
    private float vAngular;

    public Figura(float x, float y, int color, float vAngular) {
        this.x = x;
        this.y = y;
        this.vAngular = vAngular;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(5f);
    }

    public abstract void dibujar(Canvas canvas);
}
