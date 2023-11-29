package inf.junit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Map<String, Vuelo> vuelos = new HashMap<>();
    public static List<Reserva> reservas = new ArrayList<>();
    public static int confirmacionCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Gestión de Vuelos");
            System.out.println("2. Búsqueda de Vuelos");
            System.out.println("3. Hacer reservas");
            System.out.println("4. Ver reservas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    gestionarVuelos();
                    break;
                case 2:
                    menuBuscarVuelos();
                    break;
                case 3:
                    menuHacerReserva();
                    break;
                case 4:
                    verReservas();
                    break;
                case 5:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    public static boolean existeVuelo(String origen, String destino, String horaSalida) {
        if (vuelos.containsKey(generarCodigoVuelo(origen, destino, horaSalida))) {
            return true;
        } else {
            return false;
        }
    }

    public static Vuelo getVuelo(String codigo) {
        return vuelos.get(codigo);
    }

    public static void gestionarVuelos() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("----- Gestión de Vuelos -----");
            System.out.println("1. Agregar Vuelo");
            System.out.println("2. Ver Vuelos Disponibles");
            System.out.println("3. Modificar Vuelo");
            System.out.println("4. Eliminar Vuelo");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    menuAgregarVuelo();
                    break;
                case 2:
                    verVuelos();
                    break;
                case 3:
                    menuModificarVuelo();
                    break;
                case 4:
                	menuEliminarVuelo();
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    public static void menuAgregarVuelo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese origen del vuelo: ");
        String origen = scanner.next();

        System.out.print("Ingrese destino del vuelo: ");
        String destino = scanner.next();

        System.out.print("Ingrese hora de salida del vuelo: ");
        String horaSalida = scanner.next();

        System.out.print("Ingrese duración del vuelo en minutos: ");
        int duracion = scanner.nextInt();

        System.out.print("Ingrese cantidad de asientos disponibles: ");
        int asientosDisponibles = scanner.nextInt();

        agregarVuelo(origen, destino, horaSalida, duracion, asientosDisponibles);

        System.out.println("Vuelo agregado con éxito.");
    }

    public static void agregarVuelo(String origen, String destino, String horaSalida, int duracion,
            int asientosDisponibles) {
        Vuelo vuelo = new Vuelo(origen, destino, horaSalida, duracion, asientosDisponibles);
        vuelos.put(generarCodigoVuelo(origen, destino, horaSalida), vuelo);
    }

    public static void verVuelos() {
        System.out.println("----- Vuelos Disponibles -----");
        for (Map.Entry<String, Vuelo> entry : vuelos.entrySet()) {
            Vuelo vuelo = entry.getValue();
            System.out.println("Código: " + entry.getKey() +
                    ", Origen: " + vuelo.origen +
                    ", Destino: " + vuelo.destino +
                    ", Hora de Salida: " + vuelo.horaSalida +
                    ", Duración: " + vuelo.duracion +
                    " minutos, Asientos Disponibles: " + vuelo.asientosDisponibles);
        }
        System.out.println("-------------------------------");
    }

    public static void menuModificarVuelo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el código del vuelo que desea modificar: ");
        String codigoVuelo = scanner.next();

        if (validarCodigoVuelo(codigoVuelo)) {
            System.out.print("Ingrese nueva cantidad de asientos disponibles: ");
            int nuevosAsientos = scanner.nextInt();

            modificarVuelo(codigoVuelo, nuevosAsientos);
            System.out.println("Vuelo modificado con éxito.");
        } else {
            System.out.println("No se encontró un vuelo con el código proporcionado.");
        }
    }

    public static void modificarVuelo(String codigoVuelo, int nuevosAsientos) {
        Vuelo vuelo = vuelos.get(codigoVuelo);

        vuelo.asientosDisponibles = nuevosAsientos;
    }

    public static boolean validarCodigoVuelo(String codigoVuelo) {
        return vuelos.containsKey(codigoVuelo);
    }

    public static void menuEliminarVuelo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el código del vuelo que desea eliminar: ");
        String codigoVuelo = scanner.next();

        if (vuelos.containsKey(codigoVuelo)) {
            eliminarVuelo(codigoVuelo);
            System.out.println("Vuelo eliminado con éxito.");
        } else {
            System.out.println("No se encontró un vuelo con el código proporcionado.");
        }
    }

    public static void eliminarVuelo(String codigoVuelo) {
        vuelos.remove(codigoVuelo);
    }

    public static Map<String, Vuelo> getVuelos() {
        return vuelos;
    }

    public static void menuBuscarVuelos() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese origen del vuelo: ");
        String origen = scanner.next();

        System.out.print("Ingrese destino del vuelo: ");
        String destino = scanner.next();

        System.out.println("----- Resultados de Búsqueda -----");
        for (Map.Entry<String, Vuelo> entry : buscarVuelos(origen, destino).entrySet()) {
            Vuelo vuelo = entry.getValue();
            if (vuelo.origen.equalsIgnoreCase(origen) && vuelo.destino.equalsIgnoreCase(destino)) {
                System.out.println("Código: " + entry.getKey() +
                        ", Origen: " + vuelo.origen +
                        ", Destino: " + vuelo.destino +
                        ", Hora de Salida: " + vuelo.horaSalida +
                        ", Duración: " + vuelo.duracion +
                        " minutos, Asientos Disponibles: " + vuelo.asientosDisponibles);
            }
        }
        System.out.println("-----------------------------------");

    }

    public static Map<String, Vuelo> buscarVuelos(String origen, String destino) {
        Map<String, Vuelo> vuelosEncontrados = new HashMap<>();

        for (Map.Entry<String, Vuelo> entry : vuelos.entrySet()) {
            Vuelo vuelo = entry.getValue();
            if (vuelo.origen.equalsIgnoreCase(origen) && vuelo.destino.equalsIgnoreCase(destino)) {
                vuelosEncontrados.put(entry.getKey(), vuelo);
            }
        }

        return vuelosEncontrados;
    }

    public static void menuHacerReserva() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre (nombre apellido): ");
        String nombrePasajero = scanner.nextLine();

        System.out.print("Ingrese el código del vuelo en el que desea hacer la reserva: ");
        String codigoVuelo = scanner.next();

        if (vuelos.containsKey(codigoVuelo)) {
            Vuelo vuelo = vuelos.get(codigoVuelo);

            System.out.print("Ingrese la cantidad de asientos que desea reservar: ");
            int cantidadAsientos = scanner.nextInt();

            if (cantidadAsientos <= vuelo.asientosDisponibles) {
                String numeroConfirmacion = generarNumeroConfirmacion();
                Reserva reserva = new Reserva(nombrePasajero, cantidadAsientos, numeroConfirmacion);
                reservas.add(reserva);

                vuelo.asientosDisponibles -= cantidadAsientos;

                System.out.println("Reserva realizada con éxito.");
                System.out.println("Número de Confirmación: " + numeroConfirmacion);
            } else {
                System.out.println("No hay suficientes asientos disponibles para realizar la reserva.");
            }
        } else {
            System.out.println("No se encontró un vuelo con el código proporcionado.");
        }
    }

    public static String generarNumeroConfirmacion() {
        return "C" + confirmacionCounter++;
    }

    public static String generarCodigoVuelo(String origen, String destino, String horaSalida) {
        return origen.substring(0, 3) + destino.substring(0, 3) + horaSalida.replace(":", "");
    }

    public static void verReservas() {
        System.out.println("----- Reservas Realizadas -----");
        for (Reserva reserva : reservas) {
            System.out.println("Pasajero: " + reserva.pasajero +
                    ", Asientos Reservados: " + reserva.asientos +
                    ", Número de Confirmación: " + reserva.numeroConfirmacion);
        }
        System.out.println("-----------------------------");
    }
}