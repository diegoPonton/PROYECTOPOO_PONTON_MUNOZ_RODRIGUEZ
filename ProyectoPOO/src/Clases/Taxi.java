package Clases;

public class Taxi extends Servicio{

    private int noPersonas;

    public Taxi (String fecha, String hora, Ruta ruta, int noPersonas) {
        super(fecha, hora, ruta);
        this.noPersonas = noPersonas;
    }

    public int getNoPersonas () {
        return noPersonas;
    }

    public void setNoPersonas (int noPersonas) {
        this.noPersonas = noPersonas;
    }


    @Override
    public void calcularPrecioAPagar(boolean tarjeta) {
        setTotal(tarjeta ? getPrecio() + (getPrecio() * 0.15) : getPrecio());
    }

    @Override
    public void calcularTotal() {
        int valor = getRuta().getDistancia();
        System.out.println("Los kilometros para entregar su pedido son: " + valor);
        double costoRecorrido = 0.5;
        System.out.println("El costo por kilometro es: " + costoRecorrido);

        setPrecio(valor * costoRecorrido);

        System.out.println("El subtotal a pagar es: " + getPrecio());
    }

    @Override
    public String toString () {
        return "\n===================================\n"
                + "======== Servicio de Taxi=========\n"
                + "===================================\n"
                + "Codigo: " + getCodigo() + "\n"
                + "Fecha: " + getFecha() + "\n"
                + "Hora: " + getHora() + "\n"
                + "Ruta: " + "\n" + getRuta().toString() + "\n\n"
                + "No. Personas: " + getNoPersonas() + "\n"
                + "Subtotal: " + getPrecio() + "\n"
                + "Total: " + getTotal() + "\n"
                + "===================================\n";
    }
}
