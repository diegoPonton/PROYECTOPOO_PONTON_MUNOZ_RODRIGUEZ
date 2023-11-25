package Clases;

abstract public class Servicio {
    
    private String fecha;
    private String hora;
    private double precio; //Precio por cada servicio. Subtotal
    private double total; //Total con porcentaje de tarjeta
    private Conductor conductor;
    private Cliente cliente;
    private Ruta ruta;
    private String codigo;

    public Servicio (String fecha, String hora, Ruta ruta) {
        this.fecha = fecha;
        this.hora = hora;
        this.ruta = ruta;
    }
    public String getFecha () {
        return fecha;
    }
    public Conductor getConductor() {
        return conductor;
    }
    public Ruta getRuta() {
        return ruta;
    }
    public void setRuta (Ruta ruta) {this.ruta = ruta;}
    public void setConductor (Conductor conductor) {this.conductor = conductor;}
    public void setFecha (String fecha) {
        this.fecha = fecha;
    }
    public double getPrecio () {
        return precio;
    }
    public void setPrecio (double precio) {
        this.precio = precio;
    }
    public double getTotal () {
        return total;
    }
    public void setTotal (double total) {
        this.total = total;
    }
    public String getHora () {
        return hora;
    }
    public void setHora (String hora) {
        this.hora = hora;
    }
    public Cliente getCliente () {
        return cliente;
    }
    public void setCliente (Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCodigo () {
        return codigo;
    }

    public void setCodigo (String codigo) {
        this.codigo = codigo;
    }

    abstract void calcularPrecioAPagar(boolean tarjeta);
    abstract void calcularTotal();

    @Override
    public String toString () {
        return "Servicio{" + "fecha=" + fecha + ", precio=" + precio + ", total=" + total + ", conductor=" + conductor + ", ruta=" + ruta + '}';
    }


}
