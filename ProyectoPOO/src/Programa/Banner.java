package Programa;

import java.util.Scanner;

public class Banner {

    public static void bannerBienvenida(){
        System.out.println("************************************************");
        System.out.println("\t BIENVENIDO/A AL SISTEMA \t");
        System.out.println("************************************************");
    }

    public static int bannerIntentoLogin(){
        System.out.println("\t USUARIO O CONTRASEÑA INCORRECTOS \t");
        System.out.println("\t ¿Desea continuar? \t");

        System.out.println("\t 1) Sí, deseo volver a intentarlo \t");
        System.out.println("\t 2) No, deseo salir \t");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //Verifico que el input sea un número
        if (input.matches("[1-2]+")) {
            return Integer.parseInt(input);
        } else {
            return 0;
        }

    }


}
