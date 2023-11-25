package Programa;

import Clases.*;

import static Programa.Validaciones.retornarCodigo;

public class EscrituraTxt {

    public static String escribirTaxiTxt(Taxi taxi){
        //Escribo en viajes.txt el servicio de Taxi solicitado
        //Formato viajes.txt
        //numeroServicio, numeroPasajeros, distanciaKM, subtotal

        //1. Obtengo un codigo randomico que no se repita para automatizar ello
        String codigo = retornarCodigo("viajes");

        //2. Escribo los datos
        String datos = codigo + "," + taxi.getNoPersonas() + "," + taxi.getRuta().getDistancia() + "," + taxi.getPrecio();

        //3. Escribo en el txt
        ManejoArchivo.EscribirArchivo("recursos/viajes.txt", datos);

        return codigo;
    }
    public static String escribirComidaTxt(Comida comida){
        //Escribo en comidas.txt el servicio de Comida solicitado
        //Formato comidas.txt
        //numeroServicio,cedulaCliente,cedulaConductor,restaurante,subtotal

        //1. Obtengo un codigo randomico que no se repita para automatizar ello
        String codigo = retornarCodigo("comidas");

        //2. Escribo los datos
        String datos = codigo + "," + comida.getCliente().getCedula() + "," + comida.getConductor().getCedula() + "," + comida.getRestaurante() + "," + comida.getPrecio();

        //3. Escribo en el txt
        ManejoArchivo.EscribirArchivo("recursos/comidas.txt", datos);

        return codigo;
    }
    public static String escribirEncomiendaTxt(Encomienda encomienda){

        //Escribo en encomiendas.txt el servicio de Encomienda solicitado
        //Formato encomiendas.txt
        //numeroServicio,tipoEncomienda,cantidadProductos,peso,subtotal

        //1. Obtengo un codigo randomico que no se repita para automatizar ello
        String codigo = retornarCodigo("encomiendas");

        //2. Escribo los datos
        String datos = codigo + "," + encomienda.getTipo().toString() + "," + encomienda.getCantidadProductos() + "," + encomienda.getPeso() + "," + encomienda.getPrecio();

        //3. Escribo en el txt
        ManejoArchivo.EscribirArchivo("recursos/encomiendas.txt", datos);

        return codigo;
    }
    public static void escribirClienteNuevo(Cliente cliente){
        //Escribo el cliente nuevo en el txt de clientes

        String cedula = cliente.getCedula();
        String edad = String.valueOf(cliente.getEdad());
        String tarjetaCredito = cliente.getTarjetaCredito();

        String clienteNuevo = cedula + "," + edad + "," + tarjetaCredito;

        ManejoArchivo.EscribirArchivo("recursos/clientes.txt", clienteNuevo);
    }
    public static void escribirServicioTxt(Servicio servicio){
        //Una vez creado un objeto servicio, lo anoto en servicios.txt

        //1. Obtengo de que tipo es el servicio

        String servicioTxt = "";

        switch (servicio) {

            case Taxi taxi -> servicioTxt = instanciaTaxi(taxi);

            case Comida comida -> servicioTxt = instanciaComida(comida);

            case Encomienda encomienda -> servicioTxt = instanciaEncomienda(encomienda);

            case null, default -> {
                System.out.println("No se ha encontrado el tipo de servicio");
                System.exit(0);
            }

        }

        //2. Escribo en el txt
        ManejoArchivo.EscribirArchivo("recursos/servicios.txt", servicioTxt);
    }
    private static String instanciaTaxi(Taxi taxi){
        //String que voy a devolver de taxi para anotarlo en Servicio
        //Formato servicios.txt
        //numeroServicio, tipoServicio, cedulaCliente, nombreConductor, desde, hasta, Fecha, hora
        //El tipo en este caso es "T"

        String numeroServicio = taxi.getCodigo();
        String tipoServicio = "T";
        String cedulaCliente = taxi.getCliente().getCedula();
        String nombreConductor = taxi.getConductor().getNombres() + " " + taxi.getConductor().getApellidos();
        String desde = taxi.getRuta().getDesde();
        String hasta = taxi.getRuta().getHasta();
        String fecha = taxi.getFecha();
        String hora = taxi.getHora();

        return numeroServicio + "," + tipoServicio + "," + cedulaCliente + "," + nombreConductor + "," + desde + "," + hasta + "," + fecha + "," + hora;
    }
    private static String instanciaComida(Comida comida){
        //String que voy a devolver de comida para anotarlo en Servicio
        //Formato servicios.txt
        //numeroServicio, tipoServicio, cedulaCliente, nombreConductor, desde, hasta, Fecha, hora
        //El tipo en este caso es "C"

        String numeroServicio = comida.getCodigo();
        String tipoServicio = "C";
        String cedulaCliente = comida.getCliente().getCedula();
        String nombreConductor = comida.getConductor().getNombres() + " " + comida.getConductor().getApellidos();
        String desde = comida.getRuta().getDesde();
        String hasta = comida.getRuta().getHasta();
        String fecha = comida.getFecha();
        String hora = comida.getHora();

        return numeroServicio + "," + tipoServicio + "," + cedulaCliente + "," + nombreConductor + "," + desde + "," + hasta + "," + fecha + "," + hora;
    }
    private static String instanciaEncomienda(Encomienda encomienda){
        //String que voy a devolver de encomienda para anotarlo en Servicio
        //Formato servicios.txt
        //numeroServicio, tipoServicio, cedulaCliente, nombreConductor, desde, hasta, Fecha, hora
        //El tipo en este caso es "E"

        String numeroServicio = encomienda.getCodigo();
        String tipoServicio = "E";
        String cedulaCliente = encomienda.getCliente().getCedula();
        String nombreConductor = encomienda.getConductor().getNombres() + " " + encomienda.getConductor().getApellidos();
        String desde = encomienda.getRuta().getDesde();
        String hasta = encomienda.getRuta().getHasta();
        String fecha = encomienda.getFecha();
        String hora = encomienda.getHora();

        return numeroServicio + "," + tipoServicio + "," + cedulaCliente + "," + nombreConductor + "," + desde + "," + hasta + "," + fecha + "," + hora;
    }

}
