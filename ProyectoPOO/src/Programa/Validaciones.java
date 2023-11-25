package Programa;

import java.util.ArrayList;
import java.util.Scanner;

public class Validaciones {

    public static int valorRango(int a, int b){
        //Funcion que devuelve un input valido que entre en un cierto rango
        boolean puntoQuiebre = true;
        String input = "";

        while(puntoQuiebre) {

            System.out.println("Elija una opción: ");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

            if (esNumero(input)) {
                if (Integer.parseInt(input) >= a && Integer.parseInt(input) <= b) {
                    puntoQuiebre = false;
                } else {
                    System.out.println("¡INGRESE UN VALOR VALIDO!");
                }
            } else {
                System.out.println("¡INGRESE UN VALOR VALIDO!");
            }

        }

        return Integer.parseInt(input);
    }
    public static boolean esNumero(String input){
        return input.matches("[0-9]+");
    }
    public static boolean esDecimal(String input){
        return input.matches("[0-9]+.[0-9]+");
    }
    public static int obtenerNumeroRandomico(int a, int b){
        //Obtengo un numero randomico para el codigo de servicio entre el 5 y 45
        return (int) (Math.random() * (b - a)) + a;
    }
    public static boolean codigoExistente(String codigo, String tipoServicio){
        //Verifico si por azar del destino el codigo que pienso destinar en el txt ya existe
        ArrayList<String> servicios = ManejoArchivo.LeeFichero("recursos/" + tipoServicio + ".txt");

        //numero o codigo siempre esta en el indice 0

        for (String servicioTxt : servicios) {
            String[] datosServicio = servicioTxt.split(",");
            if (datosServicio[0].equals(codigo)) {
                return true;
            }
        }

        return false;
    }
    public static String crearCodigo(){

        //Creo un codigo de servicio randomico
        StringBuilder codigo = new StringBuilder(Integer.toString(obtenerNumeroRandomico(0, 9)));
        for(int i = 0; i < 4; i++){
            codigo.append(Integer.toString(obtenerNumeroRandomico(0, 9)));
        }

        return codigo.toString();
    }
    public static String retornarCodigo(String tipoServicio) {
        //Creo un codigo de servicio randomico
        //Genero 5 numeros randomicos y los uno
        //verifico si ese codigo no se encuentra ya en el txt
        //Si se encuentra, vuelvo a generar otro codigo
        //Si no se encuentra, lo retorno

        String codigo = crearCodigo();

        while(codigoExistente(codigo, tipoServicio)){
            codigo = crearCodigo();
        }

        return codigo;
    }

    public static boolean esHoraValida(String hora){
        return hora.matches("^([0-1][0-9]|2[0-3]):[0-5][0-9]$");
    }
    public static boolean fecha(int anio, int mes, int dia){
        //Obtengo la fecha del cliente
        //Valido a traves de Calendar

        //Validaciones basicas de mes y anio
        if(mes < 1 || mes > 12 || anio < 2020 || anio > 2024 || dia < 1 || dia > 31){
            return false;
        }

        int diasMaximos = diasDelMes(mes, anio);

        return dia <= diasMaximos;
    }
    public static int diasDelMes(int mes, int anio) {
        switch (mes) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0)) ? 29 : 28;
            default:
                return -1; // Invalid mes
        }
    }
}
