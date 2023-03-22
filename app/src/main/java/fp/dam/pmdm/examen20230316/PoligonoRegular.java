package fp.dam.pmdm.examen20230316;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class PoligonoRegular {

    private Path path = new Path();
    private float x;
    private float y;
    private float radio;
    private float vx;
    private JuegoThread juegoThread;
    private Paint paint;

    public PoligonoRegular(float x, float y, int lados, float radio, int color, float vx, JuegoThread juegoThread) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.juegoThread = juegoThread;
        this.radio = radio;
        if (lados < 3)
            throw new IllegalArgumentException("número de lados incorrecto");
        float xIni = radio;
        float yIni = 0;
        float angulo = 2 * (float) Math.PI / lados;
        path.moveTo(xIni, yIni);
        for (int i=1; i<lados; i++)
            path.lineTo(radio * (float) Math.cos(i * angulo), radio * (float) Math.sin(i * angulo));
        path.lineTo(xIni, yIni);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
    }

    public void dibujar(Canvas canvas) {
        canvas.save();
        canvas.translate(x, y);
        canvas.drawPath(path, paint);
        canvas.restore();
    }

    public void mover(float lapso) {
        x += vx * lapso / 1000000000f;
        if (x + radio >= juegoThread.getWidth()) {
            x -= (x + radio - juegoThread.getWidth()) * 2;
            vx = -vx;
        } else if (x - radio <= 0) {
            x += (radio - x) * 2;
            vx = -vx;
        }
    }
}
