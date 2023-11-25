package Clases;

import Programa.ManejoArchivo;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Usuario{

    private String tarjetaCredito;

    public Cliente (String cedula, String nombres, String apellidos, String usuario, String clave, String telefono, int edad, String tarjetaCredito) {
        super(cedula, nombres, apellidos, usuario, clave, telefono, edad);
        this.tarjetaCredito = tarjetaCredito;
    }

    //Getters

    public String getTarjetaCredito () {
        return tarjetaCredito;
    }

    public void setTarjetaCredito (String tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    @Override
    public ArrayList<Servicio> verServicios() {
        //Obtengo los servicios que coincidan con el nombre del cliente
        //Formato de servicio

        //1. Obtengo los servicios
        ArrayList<String> servicios = ManejoArchivo.LeeFichero("recursos/servicios.txt");

        //2. Obtengo los servicios que coincidan con la cedula del cliente
        // Formato de servicios.txt
        //numeroServicio, tipoServicio, cedulaCliente, nombreConductor, desde, hasta, Fecha, hora

        ArrayList<ArrayList<String>> serviciosCliente = new ArrayList<>();

        //3. Obtengo los numeroServicio cuyas cedulaCliente coincidan con la cedula del cliente
        // En servicios cliente anoto [numeroServicio, tipoServicio]

        for (String servicio : servicios) {
            String[] datosServicio = servicio.split(",");
            if (datosServicio[2].equals(this.getCedula())) {
                ArrayList<String> servicioCliente = new ArrayList<>();
                servicioCliente.add(datosServicio[0]);
                servicioCliente.add(datosServicio[1]);
                serviciosCliente.add(servicioCliente);
            }
        }

        //4. Construyo el objeto Servicio con los datos obtenidos

        //a. Itero por servicios cliente
        ArrayList<Servicio> serviciosClienteObjeto = new ArrayList<>();

        for (ArrayList<String> servicioCliente : serviciosCliente) {
            //b. Obtengo el tipo de servicio
            String tipoServicio = servicioCliente.get(1);
            //c. Obtengo el numeroServicio
            String numeroServicio = servicioCliente.get(0);

            //switch case para tipo de servicio
            //T de Taxi, E de encomienda, C de comida

            switch (tipoServicio) {
                case "T" -> {

                    //Si se trata de un servicio de taxi. Obtengo la informacion que pueda de viajes.txt
                    //Formato de viajes.txt
                    //numeroServicio, numeroPasajeros, distanciaKM, subtotal
                    //Formato servicios.txt
                    //numeroServicio, tipoServicio, cedulaCliente, nombreConductor,
                    // desde, hasta, Fecha, hora


                    //Datos necesarios para construir el objeto Taxi
                    //String fecha, String hora, Ruta ruta, int noPersonas

                    //1. Obtengo numeroPasajeros, distanciaKM y subtotal de viajes.txt
                    //Asumo que existe el codigo y los datos corresponden al tipo del mismo
                    int numeroPasajeros = 0;
                    int distanciaKM = 0;
                    double subtotal = 0;

                    ArrayList<String> viajes = ManejoArchivo.LeeFichero("recursos/viajes.txt");

                    for (String viaje : viajes) {
                        String[] datosViaje = viaje.split(",");
                        if (datosViaje[0].equals(numeroServicio)) {
                            numeroPasajeros = Integer.parseInt(datosViaje[1]);
                            distanciaKM = Integer.parseInt(datosViaje[2]);
                            subtotal = Double.parseDouble(datosViaje[3]);
                        }
                    }

                    //2. Obtengo desde, hasta, fecha y hora de servicios.txt
                    //Asumo que existe el codigo y los datos corresponden al tipo del mismo
                    //Armo el objeto ruta con desde y hasta

                    String desde = "";
                    String hasta = "";
                    String fecha = "";
                    String hora = "";

                    for (String servicio : servicios) {
                        String[] datosServicio = servicio.split(",");
                        if (datosServicio[0].equals(numeroServicio)) {
                            desde = datosServicio[4];
                            hasta = datosServicio[5];
                            fecha = datosServicio[6];
                            hora = datosServicio[7];
                        }
                    }

                    Ruta ruta = new Ruta(desde, hasta);
                    ruta.setDistancia(distanciaKM);

                    //3. Construyo el objeto Taxi
                    Taxi taxi = new Taxi(fecha, hora, ruta, numeroPasajeros);
                    taxi.setCodigo(numeroServicio);
                    taxi.setTotal(subtotal);

                    //4. Lo agrego al arraylist a devolver
                    serviciosClienteObjeto.add(taxi);

                }
                case "E" -> {

                    //Si se trata de un servicio de encomienda. Obtengo la informacion que pueda de encomiendas.txt
                    //Formato de encomiendas.txt
                    //numeroServicio,tipoEncomienda,cantidadProductos,peso,subtotal

                    //Formato servicios.txt
                    //numeroServicio, tipoServicio, cedulaCliente, nombreConductor,
                    // desde, hasta, Fecha, hora

                    //Datos necesarios para construir el objeto Encomienda
                    //String fecha, String hora, Ruta ruta, double peso, int cantidadProductos, TipoEncomienda tipo

                    //1. Obtengo tipoEncomienda, cantidadProductos, peso y subtotal de encomiendas.txt
                    //Asumo que existe el codigo y los datos corresponden al tipo del mismo
                    Encomienda.TipoEncomienda tipoEncomienda = null;
                    int cantidadProductos = 0;
                    double peso = 0;
                    double subtotal = 0;

                    ArrayList<String> encomiendas = ManejoArchivo.LeeFichero("recursos/encomiendas.txt");

                    for (String encomienda : encomiendas) {
                        String[] datosEncomienda = encomienda.split(",");
                        if (datosEncomienda[0].equals(numeroServicio)) {
                            tipoEncomienda = Encomienda.TipoEncomienda.valueOf(datosEncomienda[1]);
                            cantidadProductos = Integer.parseInt(datosEncomienda[2]);
                            peso = Double.parseDouble(datosEncomienda[3]);
                            subtotal = Double.parseDouble(datosEncomienda[4]);
                        }
                    }

                    //2. Obtengo desde, hasta, fecha y hora de servicios.txt

                    //Asumo que existe el codigo y los datos corresponden al tipo del mismo
                    //Armo el objeto ruta con desde y hasta

                    String desde = "";
                    String hasta = "";
                    String fecha = "";
                    String hora = "";

                    for (String servicio : servicios) {
                        String[] datosServicio = servicio.split(",");
                        if (datosServicio[0].equals(numeroServicio)) {
                            desde = datosServicio[4];
                            hasta = datosServicio[5];
                            fecha = datosServicio[6];
                            hora = datosServicio[7];
                        }
                    }

                    Ruta ruta = new Ruta(desde, hasta);

                    //3. Construyo el objeto Encomienda
                    Encomienda encomienda = new Encomienda(fecha, hora, ruta, peso, cantidadProductos, tipoEncomienda);
                    encomienda.setCodigo(numeroServicio);
                    encomienda.setPrecio(subtotal);

                    //4. Lo agrego al arraylist a devolver
                    serviciosClienteObjeto.add(encomienda);
                }
                case "C" -> {

                    //Si se trata de un servicio de comida. Obtengo la informacion que pueda de comidas.txt
                    //Formato de comidas.txt
                    //numeroServicio,cedulaCliente,cedulaConductor,restaurante,subtotal

                    //Formato servicios.txt
                    //numeroServicio, tipoServicio, cedulaCliente, nombreConductor,
                    // desde, hasta, Fecha, hora

                    //Datos necesarios para construir el objeto Comida
                    //String fecha, String hora, Ruta ruta, String restaurante

                    //1. Obtengo restaurante y subtotal de comidas.txt
                    //Asumo que existe el codigo y los datos corresponden al tipo del mismo

                    String restaurante = "";
                    double subtotal = 0;

                    ArrayList<String> comidas = ManejoArchivo.LeeFichero("recursos/comidas.txt");

                    for (String comida : comidas) {
                        String[] datosComida = comida.split(",");
                        if (datosComida[0].equals(numeroServicio)) {
                            restaurante = datosComida[3];
                            subtotal = Double.parseDouble(datosComida[4]);
                        }
                    }

                    //2. Obtengo desde, hasta, fecha y hora de servicios.txt

                    //Asumo que existe el codigo y los datos corresponden al tipo del mismo
                    //Armo el objeto ruta con desde y hasta

                    String desde = "";
                    String hasta = "";
                    String fecha = "";
                    String hora = "";

                    for (String servicio : servicios) {
                        String[] datosServicio = servicio.split(",");
                        if (datosServicio[0].equals(numeroServicio)) {
                            desde = datosServicio[4];
                            hasta = datosServicio[5];
                            fecha = datosServicio[6];
                            hora = datosServicio[7];
                        }
                    }

                    Ruta ruta = new Ruta(desde, hasta);

                    //3. Construyo el objeto Comida
                    Comida comida = new Comida(fecha, hora, ruta, restaurante);

                    //Antes de devolver el objeto comida, le asigno el subtotal, y el codigo que ya existe
                    comida.setCodigo(numeroServicio);
                    comida.setTotal(subtotal);

                    //4. Lo agrego al arraylist a devolver
                    serviciosClienteObjeto.add(comida);
                }
                default -> {
                    System.out.println("No se ha encontrado el tipo de servicio");
                    System.exit(0);
                }
            }
        }

        // 5. Devuelvo el arraylist de servicios :)
        return serviciosClienteObjeto;
    }

    @Override
    public String toString () {
        return "============================="+
                "======== CLIENTE ============"+
                "============================="+
                "\nCedula: " + getCedula() +
                "\nNombres: " + getNombres() +
                "\nApellidos: " + getApellidos() +
                "\nUsuario: " + getUsuario() +
                "\nClave: " + getClave() +
                "\nTelefono: " + getTelefono() +
                "\nEdad: " + getEdad() +
                "\nTarjeta de credito: " + tarjetaCredito +
                "\n=============================";
    }
}
