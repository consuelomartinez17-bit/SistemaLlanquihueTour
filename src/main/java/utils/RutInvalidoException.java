package utils;

/**
 * Excepción personalizada lanzada cuando un RUT no cumple con el
 * formato esperado o su dígito verificador no es matemáticamente
 * correcto.
 *
 * @author consu
 * @version 1.0
 */
public class RutInvalidoException extends Exception {

    /**
     * Construye la excepción con un mensaje descriptivo del error.
     * @param mensaje detalle de por qué el RUT no es válido
     */
    public RutInvalidoException(String mensaje) {
        super(mensaje);
    }
}