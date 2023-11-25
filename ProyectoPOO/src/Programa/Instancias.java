package Programa;

import Clases.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static Programa.EscrituraTxt.escribirClienteNuevo;
import static Programa.Validaciones.esNumero;
import static Programa.Validaciones.valorRango;

public class Instancias {

    public static Taxi nuevoServicioTaxi(Ruta ruta,
                                   String fecha,
                                   String hora, int metodoPago,
                                   int noPersonasViaje,
                                   Cliente cliente, String cedulaConductor,
                                         String usuario, String clave){

        Conductor conductor = conductorPorCedula(cedulaConductor, usuario, clave);

        Taxi taxi = new Taxi(fecha, hora, ruta, noPersonasViaje);
        taxi.calcularTotal();
        taxi.setCliente(cliente);
        taxi.setConductor(conductor);
        taxi.calcularPrecioAPagar(metodoPago == 2);

        return taxi;
    }

    public static Conductor conductorPorCedula(String cedula, String usuario, String clave){
        //Obtengo el conductor por cedula del txt
        //Asumo que el conductor existe

        ArrayList<String> usuarios = ManejoArchivo.LeeFichero("recursos/usuarios.txt");
        ArrayList<String> conductores = ManejoArchivo.LeeFichero("recursos/conductores.txt");

        //1. Lo busco en usuario

        //Formato usuarios.txt
        //cedula,nombre,apellido,user,contraseña,celular,tipoUsuario

        String nombres = "";
        String apellidos = "";
        String telefono = "";
        int edad = 0;

        for (String conductorTxt : usuarios) {
            String[] datosConductor = conductorTxt.split(",");
            if (datosConductor[0].equals(cedula)) {
                nombres = datosConductor[1];
                apellidos = datosConductor[2];
                telefono = datosConductor[5];
            }
        }

        if (nombres.isEmpty()) {
            System.out.println("No se ha encontrado el conductor");
            System.exit(0);
        }

        //2. Lo busco en conductor y termino de sacar sus datos

        //Formato conductores.txt
        // cedula, edad, estado, codigoVehiculo

        String codigoVehiculo = "";
        boolean disponibilidad = false;

        for (String conductorTxt : conductores) {
            String[] datosConductor = conductorTxt.split(",");
            if (datosConductor[0].equals(cedula)) {
                codigoVehiculo = datosConductor[3];
                edad = Integer.parseInt(datosConductor[1]);
                disponibilidad = datosConductor[2].equals("D");
            }
        }

        if (codigoVehiculo.isEmpty()) {
            System.out.println("No se ha encontrado el vehiculo del conductor");
            System.exit(0);
        }

        //3. Obtengo el vehiculo para inicializar mi conductor

        Vehiculo vehiculo = datosVehiculoNuevo(codigoVehiculo);

        return new Conductor(cedula, nombres, apellidos, usuario, clave, telefono, edad, disponibilidad, vehiculo);
    }

    public static Vehiculo datosVehiculoNuevo(String codigoVehiculo){

        //Obtengo el vehiculo para inicializar mi conductor
        //Asumo que el vehiculo existe para entonces

        ArrayList<String> vehiculos = ManejoArchivo.LeeFichero("recursos/vehiculos.txt");

        //Formato vehiculos.txt
        //codigoVehiculo, placa, modelo, marca, tipoVehiculo

        String placa = "";
        String modelo = "";
        String marca = "";
        Vehiculo.TipoVehiculo tipoVehiculo = null;

        for (String vehiculoTxt : vehiculos) {
            String[] datosVehiculo = vehiculoTxt.split(",");
            if (datosVehiculo[0].equals(codigoVehiculo)) {
                placa = datosVehiculo[1];
                modelo = datosVehiculo[2];
                marca = datosVehiculo[3];
                tipoVehiculo = datosVehiculo[4].equals("A") ? Vehiculo.TipoVehiculo.AUTO : (datosVehiculo[4].equals("M") ? Vehiculo.TipoVehiculo.MOTO : Vehiculo.TipoVehiculo.OTRO);
            }
        }

        if (placa.isEmpty()) {
            System.out.println("No se ha encontrado el vehiculo");
            System.exit(0);
        }

        return new Vehiculo(codigoVehiculo, placa, modelo, marca, tipoVehiculo);
    }
    public static Comida nuevoServicioComida(Ruta ruta,
                                       String fecha,
                                       String hora, int metodoPago,
                                       Cliente cliente, String cedulaConductor, String restaurante,
                                             String usuario, String clave){

        Conductor conductor = conductorPorCedula(cedulaConductor, usuario, clave);

        Comida comida = new Comida(fecha, hora, ruta, restaurante);
        comida.calcularTotal();
        comida.setCliente(cliente);
        comida.setConductor(conductor);
        comida.calcularPrecioAPagar(metodoPago == 2);

        return comida;
    }
    public static Encomienda nuevoServicioEncomienda(Ruta ruta,
                                               String fecha,
                                               String hora, int metodoPago,
                                               Cliente cliente, String cedulaConductor,
                                             Encomienda.TipoEncomienda tipoEncomienda,
                                             int cantidadProductos, double peso,
                                                     String usuario, String clave){

        Conductor conductor = conductorPorCedula(cedulaConductor, usuario, clave);

        Encomienda encomienda = new Encomienda(fecha, hora, ruta, peso, cantidadProductos, tipoEncomienda);
        encomienda.calcularTotal();
        encomienda.setCliente(cliente);
        encomienda.setConductor(conductor);
        encomienda.calcularPrecioAPagar(metodoPago == 2);

        return encomienda;
    }

    public static String obtenerRestaurante(){
        //Obtengo el restaurante en base a restaurantes.txt
        //Leo todos los restaurante y luego permito que elija uno

        ArrayList<String> restaurantes = ManejoArchivo.LeeFichero("recursos/restaurantes.txt");

        //Formato restaurantes.txt
        //numeroServicio,nombreRestaurante

        System.out.println("Seleccione el restaurante: ");
        int opcion = 1;

        for (String restauranteTxt : restaurantes) {
            String[] datosRestaurante = restauranteTxt.split(",");

            if(!Objects.equals(datosRestaurante[1], "nombreRestaurante")) {
                System.out.println(opcion + ") " + datosRestaurante[1]);
                opcion++;
            }
        }

        int input = valorRango(1, opcion - 1);

        return restaurantes.get(input).split(",")[1];
    }
    public static Ruta obtenerRuta(){
        //Obtengo la ruta del cliente
        //String desde, String hasta
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el punto de partida: ");
        String desde = scanner.nextLine();

        System.out.println("Ingrese el punto de llegada: ");
        String hasta = scanner.nextLine();

        return new Ruta(desde, hasta);
    }
    public static ArrayList<ArrayList<String>> conductoresDisponibles(){
        //Obtengo el nombre, la cedula y el codigoVehiculo de los conductores disponibles
        ArrayList<String> conductores = ManejoArchivo.LeeFichero("recursos/conductores.txt");
        //Formato conductores.txt
        // cedula, edad, estado, codigoVehiculo

        ArrayList<ArrayList<String>> conductoresDisponibles = new ArrayList<>();
        ArrayList<String> cedulaConductorD = new ArrayList<>();
        ArrayList<String> codigoVehiculoConductorD = new ArrayList<>();

        for (String conductorTxt : conductores) {
            String[] datosConductor = conductorTxt.split(",");
            if (datosConductor[2].equals("D")) {
                cedulaConductorD.add(datosConductor[0]);
                codigoVehiculoConductorD.add(datosConductor[3]);
            }
        }

        conductoresDisponibles.add(cedulaConductorD);
        conductoresDisponibles.add(codigoVehiculoConductorD);

        return conductoresDisponibles;
    }
    public static ArrayList<String> conductoresConVehiculo(String vehiculo){
        //A partir de conductoresDisponibles obtengo solo los que tienen auto o moto
        ArrayList<ArrayList<String>> conductoresDisponibles = conductoresDisponibles();

        //Formato conductoresDisponibles
        // Arraylist cedula, Arraylist codigoVehiculo

        ArrayList<String> conductoresConAuto = new ArrayList<>();

        ArrayList<String> vehiculos = ManejoArchivo.LeeFichero("recursos/vehiculos.txt");

        //Obtengo las cedulas de los conductores cuyo codigoVehiculo coincida con un tipoVehiculo AUTO "A"
        //Saco del segundo arraylist de conductores disponibles los codigos de vehiculos que sean de tipo A
        //Luego obtengo ese indice en el primero Arraylist de cedula en conductores Disponibles y lo agrego a conductoresConAuto

        //1. Recorro vehiculos con un for para obtener los codigos de vehiculos que sean de tipo A

        for (String vehiculoTxt : vehiculos) {
            String[] datosVehiculo = vehiculoTxt.split(",");

            if (datosVehiculo[4].equals(vehiculo)) {
                //Veo si el codigoVehiculo se contiene en el arraylist de codigos de vehiculos de conductores disponibles

                if (conductoresDisponibles.get(1).contains(datosVehiculo[0])) {
                    //Obtengo el indice del codigoVehiculo en el arraylist de codigos de vehiculos de conductores disponibles
                    int indice = conductoresDisponibles.get(1).indexOf(datosVehiculo[0]);
                    //Obtengo la cedula del conductor en el arraylist de cedulas de conductores disponibles
                    conductoresConAuto.add(conductoresDisponibles.get(0).get(indice));
                }

            }
        }

        return conductoresConAuto;
    }
    public static Cliente datosCliente(String usuario, String clave){

        //Retorno el objeto cliente que se ha loggeado al sistema con usuario y contraseña
        //Asumo que el cliente existe para entonces

        ArrayList<String> usuarios = ManejoArchivo.LeeFichero("recursos/usuarios.txt");
        ArrayList<String> clientes = ManejoArchivo.LeeFichero("recursos/clientes.txt");

        //1. Lo busco en usuario

        //Formato usuarios.txt
        //cedula,nombre,apellido,user,contraseña,celular,tipoUsuario

        String cedula = "";
        String nombres = "";
        String apellidos = "";
        String telefono = "";
        int edad = 0;

        for (String clienteTxt : usuarios) {
            String[] datosCliente = clienteTxt.split(",");
            if (datosCliente[3].equals(usuario)) {
                cedula = datosCliente[0];
                nombres = datosCliente[1];
                apellidos = datosCliente[2];
                telefono = datosCliente[5];
            }
        }

        if (cedula.isEmpty()) {
            System.out.println("No se ha encontrado el cliente");
            System.exit(0);
        }


        //2. Lo busco en cliente y termino de sacar sus datos

        //Formato clientes.txt
        //cedula, edad, tarjetaCredito

        String tarjetaCredito = "";

        for (String usuarioTxt : clientes) {
            String[] datosUsuario = usuarioTxt.split(",");
            if (datosUsuario[0].equals(cedula)) {
                tarjetaCredito = datosUsuario[2];
                if (!esNumero(datosUsuario[1])) {
                    System.out.println(datosUsuario[1]);
                    System.out.println("No se ha encontrado la edad del cliente");
                    System.exit(0);
                }
                edad = Integer.parseInt(datosUsuario[1]);
            }
        }


        return new Cliente(cedula, nombres, apellidos, usuario, clave, telefono, edad, tarjetaCredito);
    }
    public static Conductor datosConductor(String usuario, String clave){
        //Retorno el objeto cliente que se ha loggeado al sistema con usuario y contraseña
        //Asumo que el conductor existe para entonces

        ArrayList<String> usuarios = ManejoArchivo.LeeFichero("recursos/usuarios.txt");
        ArrayList<String> conductores = ManejoArchivo.LeeFichero("recursos/conductores.txt");

        //1. Lo busco en usuario

        //Formato usuarios.txt
        //cedula,nombre,apellido,user,contraseña,celular,tipoUsuario

        String cedula = "";
        String nombres = "";
        String apellidos = "";
        String telefono = "";
        int edad = 0;

        for (String conductorTxt : usuarios) {
            String[] datosConductor = conductorTxt.split(",");
            if (datosConductor[3].equals(usuario)) {
                cedula = datosConductor[0];
                nombres = datosConductor[1];
                apellidos = datosConductor[2];
                telefono = datosConductor[5];
            }
        }

        if (cedula.isEmpty()) {
            System.out.println("No se ha encontrado el conductor");
            System.exit(0);
        }

        //2. Lo busco en conductor y termino de sacar sus datos

        //Formato conductores.txt
        // cedula, edad, estado, codigoVehiculo

        String codigoVehiculo = "";
        boolean disponibilidad = false;

        for (String conductorTxt : conductores) {
            String[] datosConductor = conductorTxt.split(",");
            if (datosConductor[0].equals(cedula)) {
                codigoVehiculo = datosConductor[3];
                edad = Integer.parseInt(datosConductor[1]);
                disponibilidad = datosConductor[2].equals("D");
            }
        }

        if (codigoVehiculo.isEmpty()) {
            System.out.println("No se ha encontrado el vehiculo del conductor");
            System.exit(0);
        }

        //3. Obtengo el vehiculo para inicializar mi conductor

        Vehiculo vehiculo = datosVehiculoNuevo(codigoVehiculo);

        return new Conductor(cedula, nombres, apellidos, usuario, clave, telefono, edad, disponibilidad, vehiculo);
    }
    public static Cliente clientePorUsuario(String usuario, String clave){
        //Obtengo el cliente por usuario del txt
        //Asumo que el cliente existe

        ArrayList<String> usuarios = ManejoArchivo.LeeFichero("recursos/usuarios.txt");
        ArrayList<String> clientes = ManejoArchivo.LeeFichero("recursos/clientes.txt");

        //1. Lo busco en usuario

        //Formato usuarios.txt
        //cedula,nombre,apellido,user,contraseña,celular,tipoUsuario

        String cedula = "";
        String nombres = "";
        String apellidos = "";
        String telefono = "";
        int edad = 0;

        for (String clienteTxt : usuarios) {
            String[] datosCliente = clienteTxt.split(",");
            if (datosCliente[3].equals(usuario)) {
                cedula = datosCliente[0];
                nombres = datosCliente[1];
                apellidos = datosCliente[2];
                telefono = datosCliente[5];
            }
        }

        if (cedula.isEmpty()) {
            System.out.println("No se ha encontrado el cliente");
            System.exit(0);
        }

        //2. Lo busco en cliente y termino de sacar sus datos

        //Formato clientes.txt
        //cedula, edad, tarjetaCredito

        String tarjetaCredito = "";
        int edadCliente = 0;

        for (String clienteTxt : clientes) {
            String[] datosCliente = clienteTxt.split(",");
            if (datosCliente[0].equals(cedula)) {
                tarjetaCredito = datosCliente[2];
                edadCliente = Integer.parseInt(datosCliente[1]);
            }
        }

        if (tarjetaCredito.isEmpty()) {
            System.out.println("No se ha encontrado la tarjeta de credito del cliente");
            System.exit(0);
        }

        return new Cliente(cedula, nombres, apellidos, usuario, clave, telefono, edadCliente, tarjetaCredito);
    }
    public static Cliente clienteNuevo(String usuario, String clave){
        //Retorno el objeto cliente que se ha loggeado al sistema con usuario y contraseña
        //Asumo que el cliente no existe
        ArrayList<String> usuarios = ManejoArchivo.LeeFichero("recursos/usuarios.txt");
        //1. Lo busco en usuario

        //Formato usuarios.txt
        //cedula,nombre,apellido,user,contraseña,celular,tipoUsuario

        String cedula = "";
        String nombres = "";
        String apellidos = "";
        String telefono = "";
        int edad = 0;
        String tarjetaCredito = "";

        for (String clienteTxt : usuarios) {
            String[] datosCliente = clienteTxt.split(",");
            if (datosCliente[3].equals(usuario)) {
                cedula = datosCliente[0];
                nombres = datosCliente[1];
                apellidos = datosCliente[2];
                telefono = datosCliente[5];
            }
        }


        if (cedula.isEmpty()) {
            System.out.println("No se ha encontrado el cliente");
            System.exit(0);
        }

        //2. Pido por consola los datos que faltan

        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
        System.out.print("~ REGISTRO DE CLIENTE NUEVO ~");
        System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
        System.out.print("\n \n \t INGRESE LOS SIGUIENTES DATOS PARA COMENZAR: \t \n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("EDAD: ");

        String input = scanner.nextLine();

        while (!esNumero(input)) {
            System.out.println("¡INGRESE UN NUMERO VALIDO!");
            System.out.print("EDAD: ");
            input = scanner.nextLine();
        }

        edad = Integer.parseInt(input);

        System.out.print("TARJETA DE CREDITO: ");

        input = scanner.nextLine();

        while (!esNumero(input)) {
            System.out.println("¡INGRESE UN VALOR VALIDO!");
            System.out.print("TARJETA DE CREDITO: ");
            input = scanner.nextLine();
        }

        tarjetaCredito = input;

        //3. Escribo en el txt lo que acabo de obtener y Devuelvo el objeto
        Cliente cliente = new Cliente(cedula, nombres, apellidos, usuario, clave, telefono, edad, tarjetaCredito);
        escribirClienteNuevo(cliente);

        return cliente;
    }

}
