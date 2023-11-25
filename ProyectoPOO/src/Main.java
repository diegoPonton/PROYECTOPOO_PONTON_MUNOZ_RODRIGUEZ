
import static Programa.Instancias.*;
import static java.lang.Integer.parseInt;

import Clases.Cliente;
import Clases.Conductor;
import Programa.Banner;
import Programa.Menu;

public class Main {
    public static void main(String[] args) {

            Menu menu = new Menu(); //Clase menu para navegación simplificada

            //Banner de bienvenida
            Banner.bannerBienvenida();
            //Login
            menu.login();

            //ValidacionCliente
            //Switch case para elegir entre cliente o conductor

            int opcion = menu.tipoUsuario();

            switch (opcion) {
                case 1:
                    //Cliente

                    Cliente cliente;
                    if (menu.isNuevo()) {
                        cliente = clienteNuevo(menu.getUsuario(), menu.getClave());
                    } else {
                        cliente = datosCliente(menu.getUsuario(), menu.getClave());
                    }

                    int optMenuCliente =  0;

                    while (optMenuCliente < 5){
                        optMenuCliente = menu.menuServicio();

                        switch (optMenuCliente){
                            case 1:
                                //Solicitar servicio de Taxi
                                menu.servicioTaxi();
                                break;
                            case 2:
                                //Solicitar comida a domicilio
                                menu.servicioComida();
                                break;
                            case 3:
                                //Solicitar entrega encomienda
                                menu.servicioEncomienda();
                                break;
                            case 4:
                                //Consultar servicios
                                menu.verServicio(cliente);
                                break;
                            case 5:
                                //Salir del sistema
                                System.out.println("¡Hasta pronto!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }

                    }


                    break;
                case 2:
                    //Conductor
                    System.out.println("Conductor");

                    Conductor conductor;

                    conductor = datosConductor(menu.getUsuario(), menu.getClave());

                    int optMenuConductor =  0;

                    while (optMenuConductor < 3){
                        optMenuConductor = menu.menuConductor();

                        switch (optMenuConductor){
                            case 1:
                                //Consultar servicios conductor
                                menu.verServicio(conductor);
                                break;
                            case 2:
                                //Consultar Datos Vehiculo
                                menu.verVehiculo(conductor);
                                break;
                            case 3:
                                System.out.println("¡Hasta pronto!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }


                    }


                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }


    }


}

