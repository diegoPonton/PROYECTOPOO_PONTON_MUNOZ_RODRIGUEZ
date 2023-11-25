package Programa;
import Clases.*;

import java.util.ArrayList;
import java.util.Scanner;

import static Programa.Banner.bannerIntentoLogin;
import static Programa.EscrituraTxt.*;
import static Programa.Instancias.*;
import static Programa.ObtencionCampo.*;
import static Programa.Validaciones.obtenerNumeroRandomico;
import static Programa.Validaciones.valorRango;

public class Menu {

    private String usuario;
    private String clave;
    private boolean nuevo;
    //Constructor
    public Menu () {
        this.nuevo = true;
    }
    //Getters y Setters
    public boolean isNuevo () {
        return nuevo;
    }
    private void setNuevo (boolean nuevo) {
        this.nuevo = nuevo;
    }
    public String getUsuario () {
        return usuario;
    }
    private void setUsuario (String usuario) {
        this.usuario = usuario;
    }
    public String getClave () {
        return clave;
    }
    private void setClave (String clave) {
        this.clave = clave;
    }
    //Metodos
    public int menuServicio(){
        System.out.println("/******************MENU********************/");
        System.out.println("/*                                        */");
        System.out.println("/******************************************/");

        System.out.println("1. Solicitar servicio de Taxi");
        System.out.println("2. Solicitar comida a domicilio");
        System.out.println("3. Solicitar entrega encomienda");
        System.out.println("4. Consultar servicios");
        System.out.println("5. Salir del sistema");

        return valorRango(1, 5);
    }
    public int menuConductor(){
        System.out.println("\n\n/******************MENU********************/");
        System.out.println("/*                                        */");
        System.out.println("/******************************************/");

        System.out.println("1. Consultar servicios");
        System.out.println("2. Datos de vehiculo");
        System.out.println("3. Salir del sistema");

        return valorRango(1, 3);
    }

    public void servicioTaxi(){

        System.out.println("~~~~~~ SOLICITUD DE SERVICIO DE TAXI  ~~~~~~~~");
        System.out.println("---------------- RUTA ------------------------");

        Ruta ruta = obtenerRuta();

        System.out.println("---------------- FECHA ------------------------");
        String fecha = obtenerFecha();

        while(fecha.isEmpty()){
            System.out.println("Ingrese una fecha valida: ");
            fecha = obtenerFecha();
        }

        System.out.println("----------------- HORA ------------------------");
        String hora = obtenerHora();

        System.out.println("----------- METODO DE PAGO ---------------------");
        int metodoPago = metodoPago();

        System.out.println("----------- NUMERO DE PERSONAS -----------------");
        int noPersonasViaje = obtenerNoPersonasViaje();

        System.out.println("%%%%%%%%%%%%%% SUBTOTAL A PAGAR %%%%%%%%%%%%%%%%");

        int distancia = obtenerNumeroRandomico(5,45);
        ruta.setDistancia(distancia);
        Taxi taxi = nuevoServicioTaxi(ruta, fecha, hora, metodoPago, noPersonasViaje, clientePorUsuario(this.usuario, this.clave), conductoresConVehiculo("A").get(0), this.usuario, this.clave);

        System.out.println(" ~~ Recuerde que si usted desea pagar con tarjeta, se le agregará una comisión del 15% ~~");

        System.out.println(" \n \t ¿Desea continuar? \t");

        System.out.println("1. Sí, deseo continuar");
        System.out.println("2. No, deseo volver al menu");

        int opcion = valorRango(1, 2);

        if(opcion == 1){
            System.out.println("°°° Su servicio ha sido solicitado con exito °°°");
            System.out.println("=========== DETALLES DE COMPRA ===========");
            taxi.setCodigo(escribirTaxiTxt(taxi));
            System.out.println(taxi.toString());
            escribirServicioTxt(taxi);
        }else{
            System.out.println("Su servicio ha sido cancelado");
        }

    }
    public void servicioComida(){

        System.out.println("~~~~~~ SOLICITUD DE SERVICIO DE COMIDA  ~~~~~~~~");
        System.out.println("---------------- RUTA ------------------------");

        Ruta ruta = obtenerRuta();

        System.out.println("---------------- FECHA ------------------------");
        String fecha = obtenerFecha();

        while(fecha.isEmpty()){
            System.out.println("Ingrese una fecha valida: ");
            fecha = obtenerFecha();
        }

        System.out.println("----------------- HORA ------------------------");
        String hora = obtenerHora();

        System.out.println("----------- METODO DE PAGO ---------------------");
        int metodoPago = metodoPago();

        System.out.println("----------- RESTAURANTE -----------------");
        String restaurante = obtenerRestaurante();

        System.out.println("%%%%%%%%%%%%%% SUBTOTAL A PAGAR %%%%%%%%%%%%%%%%");

        int distancia = obtenerNumeroRandomico(5,45);
        ruta.setDistancia(distancia);

        Comida comida = nuevoServicioComida(ruta, fecha, hora, metodoPago, clientePorUsuario(this.usuario, this.clave), conductoresConVehiculo("M").get(0),restaurante, this.usuario, this.clave);;

        System.out.println(" ~~ Recuerde que si usted desea pagar con tarjeta, se le agregará una comisión del 10% ~~"); //Para comida creo una comision del 10% para probar la versatilidad del polimorfismo
        System.out.println(" \n \t ¿Desea continuar? \t");

        System.out.println("1. Sí, deseo continuar");
        System.out.println("2. No, deseo volver al menu");

        int opcion = valorRango(1, 2);

        if(opcion == 1){
            System.out.println("°°° Su servicio ha sido solicitado con exito °°°");
            System.out.println("=========== DETALLES DE COMPRA ===========");
            comida.setCodigo(escribirComidaTxt(comida));
            System.out.println(comida.toString());
            escribirServicioTxt(comida);
        }else{
            System.out.println("Su servicio ha sido cancelado");
        }

    }
    public void servicioEncomienda(){

        System.out.println("~~~~~~ SOLICITUD DE SERVICIO DE ENCOMIENDA ~~~~~~~~");
        System.out.println("---------------- RUTA ------------------------");

        Ruta ruta = obtenerRuta();

        System.out.println("---------------- FECHA ------------------------");
        String fecha = obtenerFecha();

        while(fecha.isEmpty()){
            System.out.println("Ingrese una fecha valida: ");
            fecha = obtenerFecha();
        }

        System.out.println("----------------- HORA ------------------------");
        String hora = obtenerHora();

        System.out.println("----------- METODO DE PAGO ---------------------");
        int metodoPago = metodoPago();

        System.out.println("----------- TIPO ENCOMIENDA -----------------");
        Encomienda.TipoEncomienda tipoEncomienda = seleccionarTipoEncomienda();

        System.out.println("----------- CANTIDAD DE PRODUCTOS -----------------");
        int cantidadProductos = obtenerCantProductos();

        System.out.println("----------- PESO ENCOMIENDA -----------------");

        double peso = obtenerPesoEnKg();

        System.out.println("%%%%%%%%%%%%%% SUBTOTAL A PAGAR %%%%%%%%%%%%%%%%");

        Encomienda encomienda = nuevoServicioEncomienda(ruta, fecha, hora, metodoPago, clientePorUsuario(this.usuario, this.clave), conductoresConVehiculo("M").get(0), tipoEncomienda, cantidadProductos, peso, this.usuario, this.clave);

        System.out.println(" ~~ Recuerde que si usted desea pagar con tarjeta, se le agregará una comisión del 15% ~~");

        System.out.println("1. Sí, deseo continuar");
        System.out.println("2. No, deseo volver al menu");

        int opcion = valorRango(1, 2);

        if(opcion == 1){
            System.out.println("°°° Su servicio ha sido solicitado con exito °°°");
            System.out.println("=========== DETALLES DE COMPRA ===========");

            encomienda.setCodigo(escribirEncomiendaTxt(encomienda));
            System.out.println(encomienda.toString());
            escribirServicioTxt(encomienda);

        }else{
            System.out.println("Su servicio ha sido cancelado");
        }

    }
    public void login(){

        boolean login = false;
        int intentos = 0;
        while (!login && intentos < 3) { //Tres intentos validos
            login = credenciales();
            if (!login) {
                intentos++;
                int opcion = 0;

                while(opcion == 0) {
                    opcion = bannerIntentoLogin();

                    if (opcion == 0) {System.out.println("¡OPCIÓN INCORRECTA, INTENTE DE NUEVO!");};
                }

                if (opcion == 2) {
                    System.out.println("¡GRACIAS POR USAR EL SISTEMA!");
                    System.exit(0);
                }
            }
        }

    }
    public boolean credenciales(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("USUARIO: ");
        setUsuario(scanner.nextLine());
        System.out.print("CLAVE: ");
        setClave(scanner.nextLine());

//        System.out.println(usuario);
//        System.out.println(clave);

        return verificacionUsuario(usuario, clave);
    }
    public boolean verificacionUsuario(String usuario, String clave){
        ArrayList<String> usuarios = ManejoArchivo.LeeFichero("recursos/usuarios.txt");

        //Formato usuarios.txt
        //cedula,nombre,apellido,user,contraseña,celular,tipoUsuario
        //Busco user y contraseña en el txt

        for (String usuarioTxt : usuarios) {
            String[] datosUsuario = usuarioTxt.split(",");
            if (datosUsuario[3].equals(usuario) && datosUsuario[4].equals(clave)) {
                return true;
            }
        }

        return false;
    }
    public int tipoUsuario(){
        ArrayList<String> usuarios = ManejoArchivo.LeeFichero("recursos/usuarios.txt");
        ArrayList<String> clientes = ManejoArchivo.LeeFichero("recursos/clientes.txt");
        ArrayList<String> conductores = ManejoArchivo.LeeFichero("recursos/conductores.txt");

        //Formato usuarios.txt
        //cedula,nombre,apellido,user,contraseña,celular,tipoUsuario
        //Formato clientes.txt
        //cedula, edad, tarjetaCredito
        //Formato conductores.txt
        // cedula, estado, codigoVehiculo

        //Obtengo la cedula y el tipoUsario del usuario loggeado

        String cedula = "";
        String tipoUsuario = "";
        for (String usuarioTxt : usuarios) {
            String[] datosUsuario = usuarioTxt.split(",");

            if (datosUsuario[3].equals(usuario) && datosUsuario[4].equals(clave)) {
                cedula = datosUsuario[0];

                if (datosUsuario[6].equals("C")) {
                    tipoUsuario = "Cliente";
                } else if(datosUsuario[6].equals("R")){
                    tipoUsuario = "Conductor";
                }
            }

        }

        if (tipoUsuario.equals("Cliente")) {

            //Busco la cedula en el txt de clientes
            for (String clienteTxt : clientes) {
                String[] datosCliente = clienteTxt.split(",");
                if (datosCliente[0].equals(cedula)) {
                    setNuevo(false);
                }
            }

            return 1; //Caso Cliente
        } else if (tipoUsuario.equals("Conductor")) {

            //Busco la cedula en el txt de conductores
            for (String conductorTxt : conductores) {
                String[] datosConductor = conductorTxt.split(",");
                if (datosConductor[0].equals(cedula)) {
                    setNuevo(false);
                }
            }

            return 2; //Caso Conductor
        } else {
            return 0; //Caso ninguno
        }

    }
    public void verServicio(Usuario usuario){
        ArrayList<Servicio> servicios = usuario.verServicios();
        System.out.println("********** SERVICIOS **********");

        if(servicios.isEmpty()){
            System.out.println("%%%%%% No tiene servicios %%%%%%%%");
        }else {
            for (Servicio servicio : servicios) {
                System.out.println(servicio.toString());
            }
        }
    }
    public void verVehiculo(Conductor conductor){
        System.out.println("********** VEHICULO **********");
        System.out.println(conductor.getVehiculo().toString());
    }

}
