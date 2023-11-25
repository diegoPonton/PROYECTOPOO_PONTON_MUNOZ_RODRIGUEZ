package Clases;

public class Vehiculo {

    public enum TipoVehiculo{
        AUTO,
        MOTO,
        OTRO
    }
    private String codigo;
    private String placa;
    private String modelo;
    private String marca;

    private TipoVehiculo tipoVehiculo;



    public Vehiculo (String codigo, String placa, String modelo, String marca, TipoVehiculo tipo) {
        this.codigo = codigo;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.tipoVehiculo = tipo;
    }

    //Getters

    public String getCodigo () {
        return codigo;
    }

    public String getPlaca () {
        return placa;
    }

    public String getModelo () {
        return modelo;
    }

    public String getMarca () {
        return marca;
    }

    public TipoVehiculo getTipo () {
        return tipoVehiculo;
    }

    //Setters

    public void setCodigo (String codigo) {
        this.codigo = codigo;
    }

    public void setPlaca (String placa) {
        this.placa = placa;
    }

    public void setModelo (String modelo) {
        this.modelo = modelo;
    }

    public void setTipo (TipoVehiculo tipo) {
        this.tipoVehiculo = tipo;
    }

    public void setMarca (String marca) {
        this.marca = marca;
    }


    @Override
    public String toString () {
        return "---------------------------------" +
                "------------ VEHICULO -----------" +
                "---------------------------------" +
                "\nCodigo: " + codigo +
                "\nPlaca: " + placa +
                "\nModelo: " + modelo +
                "\nMarca: " + marca +
                "\nTipo: " + tipoVehiculo +
                "\n---------------------------------";
    }

}
