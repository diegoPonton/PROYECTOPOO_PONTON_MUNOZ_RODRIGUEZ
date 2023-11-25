package Clases;

public class Ruta {

    private String desde;
    private String hasta;
    private int distancia;

    public Ruta (String desde, String hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    public String getDesde () {
        return desde;
    }

    public void setDesde (String desde) {
        this.desde = desde;
    }

    public String getHasta () {
        return hasta;
    }

    public void setHasta (String hasta) {
        this.hasta = hasta;
    }

    public int getDistancia () {
        return distancia;
    }

    public void setDistancia (int distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString () {
        return "------------------" +
                "---- RUTA -------" +
                "-----------------" +
                "\nDesde: " + desde +
                "\nHasta: " + hasta +
                "\n---------------";
    }
}
