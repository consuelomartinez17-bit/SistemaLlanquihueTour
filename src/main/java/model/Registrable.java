package model;

/**
 * Interfaz que define el comportamiento común de las entidades
 * registrables dentro del sistema Llanquihue Tour.
 *
 * Todas las clases que implementen esta interfaz deberán
 * proporcionar una implementación de registrar() y mostrarDatos().
 *
 * @author consu
 * @version 1.0
 */
public interface Registrable {

    /**
     * Registra la entidad en el sistema, aplicando validaciones
     * previas a su incorporación (por ejemplo, confirmando que
     * sus datos obligatorios estén completos).
     */
    void registrar();

    /**
     * Muestra los datos de la entidad por consola.
     */
    void mostrarDatos();
}