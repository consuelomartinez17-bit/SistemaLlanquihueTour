package model;

/**
 * Representa una reserva realizada por un cliente para un servicio
 * turístico específico, aplicando composición con Cliente,
 * ServicioTuristico y TarjetaPago.
 *
 * @author consu
 * @version 1.0
 */
public class Reserva implements Registrable {

    private String codigoReserva;
    private Cliente cliente;
    private ServicioTuristico servicio;
    private TarjetaPago tarjetaPago;
    private int cantidadPersonas;
    private boolean confirmada;

    /**
     * Constructor con parámetros.
     * Construye una reserva, validando que los datos obligatorios
     * no sean nulos y que existan cupos suficientes en el servicio
     * antes de confirmarla.
     *
     * @param codigoReserva    código único de la reserva
     * @param cliente          cliente que realiza la reserva
     * @param servicio         servicio turístico reservado
     * @param tarjetaPago      tarjeta utilizada para el pago
     * @param cantidadPersonas cantidad de personas incluidas en la reserva
     * @throws IllegalArgumentException si algún dato obligatorio es nulo,
     *         la cantidad de personas es inválida, o no hay cupos suficientes
     */
    public Reserva(String codigoReserva, Cliente cliente, ServicioTuristico servicio,
                   TarjetaPago tarjetaPago, int cantidadPersonas) {

        if (cliente == null || servicio == null || tarjetaPago == null) {
            throw new IllegalArgumentException(
                    "Cliente, servicio y tarjeta de pago no pueden ser nulos.");
        }
        if (cantidadPersonas <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de personas debe ser mayor a cero.");
        }
        if (!servicio.descontarCupos(cantidadPersonas)) {
            throw new IllegalArgumentException(
                    "No hay cupos suficientes disponibles para esta reserva.");
        }

        this.codigoReserva = codigoReserva;
        this.cliente = cliente;
        this.servicio = servicio;
        this.tarjetaPago = tarjetaPago;
        this.cantidadPersonas = cantidadPersonas;
        this.confirmada = true;
    }

    /**
     * Obtiene el código único de la reserva.
     * @return codigoReserva
     */
    public String getCodigoReserva() {
        return codigoReserva;
    }

    /**
     * Obtiene el cliente asociado a la reserva.
     * @return cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene el servicio turístico reservado.
     * @return servicio
     */
    public ServicioTuristico getServicio() {
        return servicio;
    }

    /**
     * Obtiene la tarjeta de pago utilizada en la reserva.
     * @return tarjetaPago
     */
    public TarjetaPago getTarjetaPago() {
        return tarjetaPago;
    }

    /**
     * Obtiene la cantidad de personas incluidas en la reserva.
     * @return cantidadPersonas
     */
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    /**
     * Indica si la reserva está confirmada.
     * @return true si está confirmada, false en caso contrario
     */
    public boolean isConfirmada() {
        return confirmada;
    }

    /**
     * Calcula el monto total a pagar por la reserva, multiplicando
     * el precio del servicio por la cantidad de personas.
     *
     * @return monto total de la reserva
     */
    public double calcularTotal() {
        return servicio.getPrecio() * cantidadPersonas;
    }

    @Override
    public void registrar() {
        System.out.println("Reserva " + codigoReserva + " registrada correctamente.");
    }

    @Override
    public void mostrarDatos() {
        System.out.println("[Reserva]");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Código Reserva: " + codigoReserva +
                "\nCliente: " + cliente.getNombre() +
                "\nServicio: " + servicio.getNombre() +
                "\nCantidad de personas: " + cantidadPersonas +
                "\nTotal a pagar: $" + calcularTotal() +
                "\nPago con: " + tarjetaPago +
                "\nEstado: " + (confirmada ? "Confirmada" : "Pendiente");
    }
}