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
            System.out.println(); // línea en blanco antes de mostrar el resultado

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
                    registrarNuevaPersona();
                    break;
                case 5:
                    crearReserva();
                    break;
                case 6:
                    gestorReservas.mostrarTodas();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 7);

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
        System.out.println("3. Buscar persona por nombre");
        System.out.println("4. Registrar nueva persona");
        System.out.println("5. Crear nueva reserva");
        System.out.println("6. Mostrar todas las reservas");
        System.out.println("7. Salir");
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
     * Solicita al usuario un valor numérico decimal (por ejemplo, un sueldo),
     * limpiando símbolos comunes de formato monetario chileno ($, puntos,
     * guión final) antes de intentar convertirlo. Si el valor ingresado no
     * es válido, vuelve a pedirlo en vez de cancelar toda la operación.
     *
     * @param mensaje texto a mostrar al solicitar el dato
     * @return el valor numérico ingresado, ya validado
     */
    private double leerMontoValido(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();

            // Quita $, puntos, comas y guiones sueltos (formato tipo $440.000.-)
            String limpio = texto.replace("$", "")
                    .replace(".", "")
                    .replace(",", "")
                    .replace("-", "")
                    .trim();

            try {
                return Double.parseDouble(limpio);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Intente nuevamente (solo números, ej: 440000).");
            }
        }
    }

    /**
     * Solicita al usuario un valor entero (por ejemplo, cantidad de personas
     * o número de una dirección), limpiando espacios y puntos de miles antes
     * de intentar convertirlo. Si el valor ingresado no es válido, vuelve a
     * pedirlo en vez de cancelar toda la operación.
     *
     * @param mensaje texto a mostrar al solicitar el dato
     * @return el valor entero ingresado, ya validado
     */
    private int leerEnteroValido(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();

            String limpio = texto.replace(".", "")
                    .replace(",", "")
                    .trim();

            try {
                return Integer.parseInt(limpio);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Intente nuevamente (solo números, ej: 3).");
            }
        }
    }

    /**
     * Solicita al usuario el número de tarjeta, validando que tenga
     * exactamente 16 dígitos numéricos. Si el valor ingresado no es
     * válido, vuelve a pedirlo en vez de cancelar toda la operación.
     *
     * @param mensaje texto a mostrar al solicitar el dato
     * @return el número de tarjeta ya validado (16 dígitos)
     */
    private String leerTarjetaValida(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();

            if (texto.matches("\\d{16}")) {
                return texto;
            }
            System.out.println("El número de tarjeta debe tener exactamente 16 dígitos numéricos. Intente nuevamente.");
        }
    }
    /**
     * Carga los datos iniciales del sistema: servicios turísticos y
     * clientes desde archivos de texto, además de un empleado y un
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
     * Solicita al usuario un texto y busca clientes cuyo nombre lo
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
     * Guía al usuario para registrar una nueva persona en el sistema,
     * solicitando primero el tipo (Cliente, Empleado o Proveedor), luego
     * los datos comunes a toda persona, y finalmente los datos específicos
     * según el tipo elegido. Valida el RUT y evita duplicados antes de
     * construir el objeto correspondiente.
     */
    private void registrarNuevaPersona() {
        System.out.println("\n¿Qué tipo de persona desea registrar?");
        System.out.println("1. Cliente");
        System.out.println("2. Empleado");
        System.out.println("3. Proveedor");
        System.out.print("Seleccione una opción: ");

        int tipo = leerOpcion();
        if (tipo < 1 || tipo > 3) {
            System.out.println("Opción inválida. Operación cancelada.");
            return;
        }

        // --- Datos comunes a toda Persona ---
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío. Operación cancelada.");
            return;
        }

        System.out.print("RUT (formato 12345678-9): ");
        String rutTexto = scanner.nextLine().trim();

        for (Persona p : gestorPersonas.getPersonas()) {
            if (p.getRut().getRutFormateado().equalsIgnoreCase(rutTexto)) {
                System.out.println("Ya existe una persona registrada con ese RUT.");
                return;
            }
        }

        Rut rut;
        try {
            rut = new Rut(rutTexto);
        } catch (RutInvalidoException e) {
            System.out.println("RUT inválido: " + e.getMessage());
            return;
        }

        System.out.print("Calle: ");
        String calle = scanner.nextLine().trim();

        int numero = leerEnteroValido("Número: ");

        System.out.print("Comuna: ");
        String comuna = scanner.nextLine().trim();
        System.out.print("Región: ");
        String region = scanner.nextLine().trim();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine().trim();

        Direccion direccion = new Direccion(calle, numero, comuna, region);

        // --- Datos específicos según el tipo elegido ---
        Persona nuevaPersona = null;

        if (tipo == 1) {
            System.out.print("Correo electrónico: ");
            String correo = scanner.nextLine().trim();
            nuevaPersona = new Cliente(nombre, rut, direccion, telefono, correo);

        } else if (tipo == 2) {
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine().trim();

            double sueldo = leerMontoValido("Sueldo: ");

            nuevaPersona = new Empleado(nombre, rut, direccion, telefono, cargo, sueldo);

        } else if (tipo == 3) {
            System.out.print("Empresa: ");
            String empresa = scanner.nextLine().trim();
            System.out.print("Rubro: ");
            String rubro = scanner.nextLine().trim();
            nuevaPersona = new Proveedor(nombre, rut, direccion, telefono, empresa, rubro);
        }

        gestorPersonas.agregarPersona(nuevaPersona);
        nuevaPersona.registrar();
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

        int cantidadPersonas = leerEnteroValido("Ingrese la cantidad de personas: ");

        String numeroTarjeta = leerTarjetaValida("Ingrese el número de tarjeta (16 dígitos, solo números): ");

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