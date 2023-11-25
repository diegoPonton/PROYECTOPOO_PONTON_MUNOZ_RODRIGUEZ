package Programa;

import Clases.Encomienda;

import java.util.Scanner;

import static Programa.Validaciones.*;

public class ObtencionCampo {

    public static Encomienda.TipoEncomienda seleccionarTipoEncomienda(){
        //Escribo en un menu los datos del enum TipoEncomienda
        //Retorno el tipo de encomienda seleccionado

        System.out.println("Seleccione el tipo de encomienda: ");

        Encomienda.TipoEncomienda[] valores = Encomienda.TipoEncomienda.values();

        //Itero por todos los valores para imprimirlos en consola
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + ") " + valores[i]);
        }

        int opcion = valorRango(1, valores.length);

        return valores[opcion - 1];
    }
    public static String obtenerFecha(){
        //Obtengo la fecha del cliente
        //String fecha
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el dia: ");
        String dia = scanner.nextLine();

        while(!esNumero(dia)){
            System.out.println("Ingrese un dia valido: ");
            dia = scanner.nextLine();
        }

        System.out.println("Ingrese el mes: ");
        String mes = scanner.nextLine();

        while(!esNumero(mes)){
            System.out.println("Ingrese un mes valido: ");
            mes = scanner.nextLine();
        }

        System.out.println("Ingrese el año: ");
        String anio = scanner.nextLine();

        while(!esNumero(anio)){
            System.out.println("Ingrese un año valido: ");
            anio = scanner.nextLine();
        }

        if(fecha(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia))) {
            return dia + "/" + mes + "/" + anio;
        }

        return "";
    }
    public static String obtenerHora(){
        //Obtengo la hora del cliente
        //String hora
        Scanner scanner = new Scanner(System.in);
        System.out.println("Formato de hora HH:MM (24 horas) ");
        System.out.println("Ingrese la hora: ");
        String hora = scanner.nextLine();

        while(!esHoraValida(hora)){
            System.out.println("Ingrese una hora valida: ");
            hora = scanner.nextLine();
        }

        return hora;
    }
    public static int metodoPago(){
        //Obtengo si paga por efectivo o tarjeta
        System.out.println("Seleccione el metodo de pago: ");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta de credito");
        return valorRango(1, 2);
    }
    public static int obtenerNoPersonasViaje(){
        //Obtengo el numero de personas que viajan

        System.out.println("Ingrese el numero de personas que viajan: ");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while(!esNumero(input)){
            System.out.println("Ingrese un numero valido: ");
            input = scanner.nextLine();
        }

        return Integer.parseInt(input);
    }
    public static double obtenerPesoEnKg(){
        //Obtengo el peso de la encomienda en kg

        System.out.println("Ingrese el peso de la encomienda en kg: ");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while(!esDecimal(input)){
            System.out.println("Ingrese un numero valido: ");
            input = scanner.nextLine();
        }

        return Double.parseDouble(input);
    }
    public static int obtenerCantProductos(){
        //Obtengo la cantidad de productos que se van a enviar

        System.out.println("Ingrese la cantidad de productos que se van a enviar: ");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while(!esNumero(input)){
            System.out.println("Ingrese un numero valido: ");
            input = scanner.nextLine();
        }

        return Integer.parseInt(input);
    }


}
