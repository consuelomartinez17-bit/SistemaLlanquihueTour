package model;

/**
 * Representa una tarjeta de pago asociada a una reserva, mediante
 * composición dentro de la clase Reserva.
 *
 * @author consu
 * @version 1.0
 */
public class TarjetaPago {

    private String numeroEnmascarado; // solo los últimos 4 dígitos visibles
    private String titular;
    private String tipo; // "Débito" o "Crédito"

    /**
     * Constructor con parámetros.
     * Construye la tarjeta de pago, enmascarando el número completo
     * por seguridad, dejando visibles solo los últimos 4 dígitos.
     *
     * @param numeroCompleto número completo de la tarjeta
     * @param titular        nombre del titular de la tarjeta
     * @param tipo           tipo de tarjeta ("Débito" o "Crédito")
     */
    public TarjetaPago(String numeroCompleto, String titular, String tipo) {
        this.numeroEnmascarado = enmascarar(numeroCompleto);
        this.titular = titular;
        this.tipo = tipo;
    }

    /**
     * Enmascara el número de tarjeta dejando visibles solo los
     * últimos 4 dígitos, por seguridad y buenas prácticas.
     *
     * @param numeroCompleto número completo ingresado
     * @return número enmascarado, ej. "**** **** **** 1234"
     */
    private String enmascarar(String numeroCompleto) {
        if (numeroCompleto == null || numeroCompleto.length() < 4) {
            return "**** **** **** ????";
        }
        String ultimosCuatro = numeroCompleto.substring(numeroCompleto.length() - 4);
        return "**** **** **** " + ultimosCuatro;
    }

    /**
     * Obtiene el número de tarjeta enmascarado, mostrando solo los
     * últimos 4 dígitos.
     * @return numeroEnmascarado
     */
    public String getNumeroEnmascarado() {
        return numeroEnmascarado;
    }

    /**
     * Obtiene el nombre del titular de la tarjeta.
     * @return titular
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Obtiene el tipo de tarjeta (Débito o Crédito).
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return tipo + " " + numeroEnmascarado + " (Titular: " + titular + ")";
    }
}