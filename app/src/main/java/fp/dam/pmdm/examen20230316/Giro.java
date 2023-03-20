package fp.dam.pmdm.examen20230316;

public enum Giro {
    IZDA(-1), DCHA(1);
    int sentido;

    Giro(int sentido) {
        this.sentido = sentido;
    }

    public int getSentido() {
        return sentido;
    }

    public static Giro aleatorio() {
        return Math.random() < .5 ? Giro.IZDA : Giro.DCHA;
    }
}
