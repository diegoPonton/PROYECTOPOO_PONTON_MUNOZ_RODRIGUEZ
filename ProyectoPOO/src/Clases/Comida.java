package Clases;

public class Comida extends Servicio{

    private String restaurante;
    private double comision; //Numero random entre 0.1 y 0.5

    public Comida (String fecha, String hora, Ruta ruta, String restaurante) {
        super(fecha, hora, ruta);
        this.restaurante = restaurante;
    }

    public String getRestaurante () {
        return restaurante;
    }

    public void setRestaurante (String restaurante) {
        this.restaurante = restaurante;
    }

    public double getComision () {
        return comision;
    }

    public void setComision (double comision) {
        this.comision = comision;
    }

    @Override
    public void calcularPrecioAPagar(boolean tarjeta) {
        setTotal(tarjeta ? getPrecio() + (getPrecio() * 0.10) + (getPrecio() * comision): getPrecio() + (getPrecio() * comision));
    }

    @Override
    public void calcularTotal() {
        double comision = (double) getRuta().getDistancia()/10;
        setComision(comision);

        System.out.println("Los kilometros para entregar su pedido son: " + comision);
        double costoRecorrido = 0.5;
        System.out.println("El costo por kilometro es: " + costoRecorrido);

        setPrecio((getRuta().getDistancia() * costoRecorrido) + ((getRuta().getDistancia() * costoRecorrido)*comision));

        System.out.println("El subtotal a pagar es: " + getPrecio());
    }

    @Override
    public String toString () {
        return "===================================\n"
                + "Servicio de Comida\n"
                + "===================================\n"
                + "Codigo: " + getCodigo() + "\n"
                + "Fecha: " + getFecha() + "\n"
                + "Hora: " + getHora() + "\n"
                + "Ruta: " + "\n" + getRuta().toString() + "\n\n"
                + "Restaurante: " + getRestaurante() + "\n"
                + "Subtotal: " + getTotal() + "\n"
                + "===================================\n";
    }
}
