package data;

import model.Reserva;

import java.util.HashMap;
import java.util.Map;

/**
 * Gestiona las reservas del sistema utilizando un HashMap indexado
 * por código de reserva, permitiendo una búsqueda directa y eficiente
 * sin necesidad de recorrer toda la colección.
 *
 * @author consu
 * @version 1.0
 */
public class GestorReservas {

    private Map<String, Reserva> reservas;

    /**
     * Constructor sin parámetros.
     * Inicializa el mapa vacío de reservas.
     */
    public GestorReservas() {
        this.reservas = new HashMap<>();
    }

    /**
     * Agrega una nueva reserva al sistema, indexada por su código.
     * Si ya existe una reserva con el mismo código, se rechaza para
     * evitar sobrescribir una reserva existente por error.
     *
     * @param reserva reserva a agregar
     * @return true si se agregó correctamente, false si el código ya existía
     */
    public boolean agregarReserva(Reserva reserva) {
        if (reserva == null || reservas.containsKey(reserva.getCodigoReserva())) {
            return false;
        }
        reservas.put(reserva.getCodigoReserva(), reserva);
        return true;
    }

    /**
     * Busca una reserva por su código único.
     * @param codigo código de la reserva a buscar
     * @return la reserva encontrada, o null si no existe
     */
    public Reserva buscarPorCodigo(String codigo) {
        return reservas.get(codigo);
    }

    /**
     * Muestra por consola todas las reservas registradas en el sistema.
     */
    public void mostrarTodas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        for (Reserva r : reservas.values()) {
            System.out.println(r);
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Obtiene la cantidad de reservas registradas.
     * @return cantidad de reservas
     */
    public int getCantidad() {
        return reservas.size();
    }

    /**
     * Obtiene el mapa completo de reservas registradas.
     * @return mapa de reservas indexado por código
     */
    public Map<String, Reserva> getReservas() {
        return reservas;
    }

}