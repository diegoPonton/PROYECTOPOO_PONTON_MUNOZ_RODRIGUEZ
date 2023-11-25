package Clases;

import java.util.ArrayList;

public abstract class Usuario {

    private String cedula;
    private String nombres;
    private String apellidos;
    private String usuario;
    private String clave;
    private String telefono;
    private int edad;

    public Usuario(String cedula, String nombres, String apellidos, String usuario, String clave, String telefono, int edad) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.clave = clave;
        this.telefono = telefono;
        this.edad = edad;
    }

    //Getters
    public String getCedula () {
        return cedula;
    }

    public String getNombres () {
        return nombres;
    }

    public String getApellidos () {
        return apellidos;
    }

    public String getUsuario () {
        return usuario;
    }

    public String getClave () {
        return clave;
    }

    public String getTelefono () {
        return telefono;
    }

    public int getEdad () {
        return edad;
    }

    //Setters

    public void setCedula (String cedula) {
        this.cedula = cedula;
    }

    public void setNombres (String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos (String apellidos) {
        this.apellidos = apellidos;
    }

    public void setUsuario (String usuario) {
        this.usuario = usuario;
    }

    public void setClave (String clave) {
        this.clave = clave;
    }

    public void setTelefono (String telefono) {
        this.telefono = telefono;
    }

    public void setEdad (int edad) {
        this.edad = edad;
    }

    public abstract ArrayList<Servicio> verServicios();

    @Override
    public String toString () {
        return "Usuario{" + "cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", usuario=" + usuario + ", clave=" + clave + ", telefono=" + telefono + '}';
    }
}
