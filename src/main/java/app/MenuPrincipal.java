package app;

import data.GestorPersonas;
import data.GestorServicios;
import data.GestorReservas;
import model.*;
import utils.RutInvalidoException;

import java.util.List;
import java.util.Scanner;

/**
 * Controla el menú interactivo por consola del sistema Llanquihue Tour,
 * permitiendo cargar datos desde archivos, mostrar y buscar entidades,
 * crear reservas, y visualizar el estado general del sistema.
 *
 * @author consu
 * @version 1.0
 */
public class MenuPrincipal {

    private GestorPersonas gestorPersonas;
    private GestorServicios gestorServicios;
    private GestorReservas gestorReservas;
    private Scanner scanner;

    /**
     * Constructor sin parámetros.
     * Inicializa los tres gestores del sistema y el lector de consola.
     */
    public MenuPrincipal() {
        this.gestorPersonas = new GestorPersonas();
        this.gestorServicios = new GestorServicios();
        this.gestorReservas = new GestorReservas();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia el ciclo del menú principal. Carga los datos iniciales
     * desde archivo y desde código, y muestra las opciones disponibles
     * hasta que el usuario decida salir.
     */
    public void iniciar() {
        cargarDatosIniciales();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    gestorServicios.mostrarTodos();
                    break;
                case 2:
                    gestorPersonas.mostrarTodasPorTipo();
                    break;
                case 3:
                    buscarClientePorNombre();
                    break;
                case 4:
                    crearReserva();
                    break;
                case 5:
                    gestorReservas.mostrarTodas();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 6);

        scanner.close();
    }

    /**
     * Muestra las opciones del menú principal por consola.
     */
    private void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("   LLANQUIHUE TOUR - Sistema de Gestión");
        System.out.println("========================================");
        System.out.println("1. Mostrar servicios turísticos");
        System.out.println("2. Mostrar personas registradas (clientes/empleados/proveedores)");
        System.out.println("3. Buscar cliente por nombre");
        System.out.println("4. Crear nueva reserva");
        System.out.println("5. Mostrar todas las reservas");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Lee la opción ingresada por el usuario, devolviendo un valor
     * inválido (-1) si el texto ingresado no es un número, en vez
     * de interrumpir el programa con una excepción.
     *
     * @return número de opción ingresado, o -1 si no era válido
     */
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Carga los datos iniciales del sistema: servicios.txt turísticos y
     * clientes.txt desde archivos de texto, además de un empleado y un
     * proveedor de ejemplo creados directamente en código, para
     * demostrar las tres subclases de Persona en una misma colección.
     */
    private void cargarDatosIniciales() {
        gestorServicios.cargarDesdeArchivo("servicios.txt");
        gestorPersonas.cargarClientesDesdeArchivo("clientes.txt");

        try {
            Rut rutEmpleado = new Rut("11222333-9");
            Direccion dirEmpleado = new Direccion("Av. Costanera", 500, "Llanquihue", "Los Lagos");
            Empleado guia = new Empleado("Pedro Alvarado", rutEmpleado, dirEmpleado,
                    "+56911111111", "Guía Turístico", 650000);
            gestorPersonas.agregarPersona(guia);

            Rut rutProveedor = new Rut("8765432-K");
            Direccion dirProveedor = new Direccion("Ruta 5 Sur", 1200, "Puerto Montt", "Los Lagos");
            Proveedor transportes = new Proveedor("Marcela Ruiz", rutProveedor, dirProveedor,
                    "+56922222222", "Transportes Sur", "Transporte turístico");
            gestorPersonas.agregarPersona(transportes);

        } catch (RutInvalidoException e) {
            System.out.println("Error al cargar datos de ejemplo: " + e.getMessage());
        }
    }

    /**
     * Solicita al usuario un texto y busca clientes.txt cuyo nombre lo
     * contenga, mostrando los resultados encontrados.
     */
    private void buscarClientePorNombre() {
        System.out.print("Ingrese nombre o parte del nombre a buscar: ");
        String texto = scanner.nextLine();
        List<Persona> resultados = gestorPersonas.buscarPorNombre(texto);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron coincidencias.");
            return;
        }
        for (Persona p : resultados) {
            System.out.println(p);
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Guía al usuario paso a paso para crear una nueva reserva,
     * solicitando el RUT del cliente, el código del servicio, la
     * cantidad de personas y los datos de la tarjeta de pago.
     * Valida que el cliente y el servicio existan antes de intentar
     * construir la reserva, y captura cualquier error de negocio
     * (cupos insuficientes, cantidad inválida) sin interrumpir el programa.
     */
    private void crearReserva() {
        System.out.print("Ingrese el RUT del cliente (formato 12345678-9): ");
        String rutBuscado = scanner.nextLine().trim();

        Persona clienteEncontrado = null;
        for (Persona p : gestorPersonas.getPersonas()) {
            if (p instanceof Cliente && p.getRut().getRutFormateado().equalsIgnoreCase(rutBuscado)) {
                clienteEncontrado = p;
                break;
            }
        }

        if (!(clienteEncontrado instanceof Cliente)) {
            System.out.println("No se encontró un cliente con ese RUT.");
            return;
        }
        Cliente cliente = (Cliente) clienteEncontrado;

        System.out.print("Ingrese el código del servicio (ej. SVT001): ");
        String codigoServicio = scanner.nextLine().trim();
        ServicioTuristico servicio = gestorServicios.buscarPorCodigo(codigoServicio);

        if (servicio == null) {
            System.out.println("No se encontró un servicio con ese código.");
            return;
        }

        System.out.print("Ingrese la cantidad de personas: ");
        int cantidadPersonas;
        try {
            cantidadPersonas = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("La cantidad debe ser un número. Operación cancelada.");
            return;
        }

        System.out.print("Ingrese el número de tarjeta: ");
        String numeroTarjeta = scanner.nextLine().trim();
        System.out.print("Ingrese el tipo de tarjeta (Débito/Crédito): ");
        String tipoTarjeta = scanner.nextLine().trim();

        TarjetaPago tarjeta = new TarjetaPago(numeroTarjeta, cliente.getNombre(), tipoTarjeta);
        String codigoReserva = "RES" + (gestorReservas.getCantidad() + 1);

        try {
            Reserva reserva = new Reserva(codigoReserva, cliente, servicio, tarjeta, cantidadPersonas);
            gestorReservas.agregarReserva(reserva);
            reserva.registrar();
            System.out.println(reserva);

        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo crear la reserva: " + e.getMessage());
        }
    }
}