package fp.dam.pmdm.examen20230316;

public class Datos {
    String nombreCustomer;
    int importe;

    public Datos(String nombreCustomer, int importe) {
        this.nombreCustomer = nombreCustomer;
        this.importe = importe;
    }


    public String getNombreCustomer() {
        return nombreCustomer;
    }

    public void setNombreCustomer(String nombreCustomer) {
        this.nombreCustomer = nombreCustomer;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }
}
