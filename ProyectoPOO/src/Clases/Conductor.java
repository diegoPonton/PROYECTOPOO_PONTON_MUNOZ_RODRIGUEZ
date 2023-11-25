package Clases;

import Programa.ManejoArchivo;

import java.util.ArrayList;

public class Conductor extends Usuario{

        private boolean disponibilidad;

        private Vehiculo vehiculo;

    public Conductor (String cedula, String nombres, String apellidos, String usuario, String clave, String telefono, int edad, boolean disponibilidad, String codigoVehiculo, String placa, String modelo, String marca, Vehiculo.TipoVehiculo tipo) {
        super(cedula, nombres, apellidos, usuario, clave, telefono, edad);
        this.disponibilidad = disponibilidad;
        this.vehiculo = new Vehiculo(codigoVehiculo, placa, modelo, marca, tipo);
    }

    public Conductor (String cedula, String nombres, String apellidos, String usuario, String clave, String telefono, int edad, boolean disponibilidad, Vehiculo vehiculo) {
        super(cedula, nombres, apellidos, usuario, clave, telefono, edad);
        this.disponibilidad = disponibilidad;
        this.vehiculo = vehiculo;
    }

    public boolean getDisponibilidad () {
        return disponibilidad;
    }

    public void setDisponibilidad (boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Vehiculo getVehiculo () {
        return vehiculo;
    }

    public void setVehiculo (Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public ArrayList<Servicio> verServicios() {

        //Obtengo los servicios que coincidan con el nombre del conductor
        //Formato de servicio

        //1. Obtengo los servicios
        ArrayList<String> servicios = ManejoArchivo.LeeFichero("recursos/servicios.txt");

        //2. Obtengo los servicios que coincidan con el nombre del conductor
        // Formato de servicios.txt
        //numeroServicio, tipoServicio, cedulaCliente, nombreConductor, desde, hasta, Fecha, hora

        //3. Obtengo los numeroServicio cuyos nombres de conductor coincidan con el nombre del conductor
        // En servicios cliente anoto [numeroServicio, tipoServicio]

        ArrayList<ArrayList<String>> serviciosConductor = new ArrayList<>();

        for (String servicio : servicios) {
            String[] datosServicio = servicio.split(",");
            if (datosServicio[3].equals(this.getNombres() + " " + this.getApellidos())) {
                ArrayList<String> servicioConductor = new ArrayList<>();
                servicioConductor.add(datosServicio[0]);
                servicioConductor.add(datosServicio[1]);
                serviciosConductor.add(servicioConductor);
            }
        }

        //4. Construyo el objeto Servicio con los datos obtenidos

        //a. Itero por servicios conductor
        ArrayList<Servicio> serviciosConductorObjeto = new ArrayList<>();

        for (ArrayList<String> servicioConductor : serviciosConductor) {
            //b. Obtengo el tipo de servicio
            String tipoServicio = servicioConductor.get(1);
            //c. Obtengo el numeroServicio
            String numeroServicio = servicioConductor.get(0);

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
                    taxi.setTotal(subtotal);
                    taxi.setCodigo(numeroServicio);

                    //4. Lo agrego al arraylist a devolver
                    serviciosConductorObjeto.add(taxi);

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
                    encomienda.setPrecio(subtotal);
                    encomienda.setCodigo(numeroServicio);


                    //4. Lo agrego al arraylist a devolver
                    serviciosConductorObjeto.add(encomienda);
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
                    comida.setTotal(subtotal);
                    comida.setCodigo(numeroServicio);

                    //4. Lo agrego al arraylist a devolver
                    serviciosConductorObjeto.add(comida);
                }
                default -> {
                    System.out.println("No se ha encontrado el tipo de servicio");
                    System.exit(0);
                }
            }
        }

        return serviciosConductorObjeto;
    }
}
