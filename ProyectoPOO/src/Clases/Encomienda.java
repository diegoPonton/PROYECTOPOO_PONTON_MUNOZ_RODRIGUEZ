package Clases;

public class Encomienda extends Servicio{

    private double peso;
    private int cantidadProductos;
    private TipoEncomienda tipo;

    //Enum para los tipos de encomienda
    public enum TipoEncomienda {
        DOCUMENTOS, MEDICINA, OTROS
    }

    public Encomienda (String fecha, String hora, Ruta ruta, double peso, int cantidadProductos, TipoEncomienda tipo) {
        super(fecha, hora, ruta);
        this.peso = peso;
        this.cantidadProductos = cantidadProductos;
        this.tipo = tipo;
    }

    public double getPeso () {
        return peso;
    }

    public void setPeso (double peso) {
        this.peso = peso;
    }
    public int getCantidadProductos () {
        return cantidadProductos;
    }

    public void setCantidadProductos (int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public TipoEncomienda getTipo () {
        return tipo;
    }

    public void setTipo (TipoEncomienda tipo) {
        this.tipo = tipo;
    }


    @Override
    public void calcularPrecioAPagar(boolean tarjeta){

    }

    @Override
    public void calcularTotal() {

        System.out.println("La cantidad de encomiendas son: " + cantidadProductos);
        int costoPorEncomienda = 1;
        System.out.println("El costo por encomienda es: " + costoPorEncomienda);
        int costoPorViaje = 4;
        System.out.println("El costo por viaje es: " + costoPorViaje);

        setPrecio((cantidadProductos * costoPorEncomienda) + costoPorViaje);
    }

    @Override
    public String toString () {
        return "==================================" +
                "\nEncomienda" +
                "\n==================================" +
                "\nCodigo: " + getCodigo() +
                "\nFecha: " + getFecha() +
                "\nHora: " + getHora() +
                "Ruta: " + "\n" + getRuta().toString() + "\n\n" +
                "\nTipo de encomienda: " + tipo +
                "\nPeso: " + peso +
                "\nCantidad de productos: " + cantidadProductos +
                "\nSubtotal: " + getPrecio() +
                "\n==================================";
    }
}
