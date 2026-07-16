package model;

/**
 * Representa un servicio turístico ofrecido por la agencia
 * Llanquihue Tour, con su nombre, descripción, precio y cupos
 * disponibles para reservar.
 *
 * @author consu
 * @version 1.0
 */
public class ServicioTuristico {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cuposDisponibles;

    /**
     * Constructor con parámetros.
     * Construye un servicio turístico con sus datos completos.
     *
     * @param codigo           código único identificatorio del servicio
     * @param nombre           nombre del servicio turístico
     * @param descripcion      breve descripción del servicio
     * @param precio           precio por persona del servicio
     * @param cuposDisponibles cantidad de cupos disponibles para reservar
     */
    public ServicioTuristico(String codigo, String nombre, String descripcion,
                             double precio, int cuposDisponibles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cuposDisponibles = cuposDisponibles;
    }

    /**
     * Obtiene el código único del servicio turístico.
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del servicio turístico.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la descripción del servicio turístico.
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el precio por persona del servicio.
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Actualiza el precio por persona del servicio.
     * @param precio nuevo precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad de cupos disponibles del servicio.
     * @return cuposDisponibles
     */
    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    /**
     * Actualiza la cantidad de cupos disponibles del servicio.
     * @param cuposDisponibles nueva cantidad de cupos
     */
    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    /**
     * Reduce la cantidad de cupos disponibles al confirmarse una reserva.
     * Valida que existan cupos suficientes antes de descontarlos, evitando
     * la sobreventa mencionada en la problemática del sistema.
     *
     * @param cantidad cantidad de cupos a descontar
     * @return true si se pudo descontar, false si no había cupos suficientes
     */
    public boolean descontarCupos(int cantidad) {
        if (cantidad <= 0 || cantidad > cuposDisponibles) {
            return false;
        }
        this.cuposDisponibles -= cantidad;
        return true;
    }

    @Override
    public String toString() {
        return "Servicio: " + nombre +
                "\nCódigo: " + codigo +
                "\nDescripción: " + descripcion +
                "\nPrecio por persona: $" + precio +
                "\nCupos disponibles: " + cuposDisponibles;
    }
}