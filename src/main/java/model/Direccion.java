package model;

/**
 * Representa la dirección de una persona, mediante composición
 * dentro de la clase Persona.
 *
 * @author consu
 * @version 1.0
 */
public class Direccion {

    private String calle;
    private int numero;
    private String comuna;
    private String region;

    /**
     * Constructor con parámetros.
     * Construye la dirección con sus datos completos.
     *
     * @param calle   nombre de la calle
     * @param numero  número de la vivienda
     * @param comuna  comuna de residencia
     * @param region  región de residencia
     */
    public Direccion(String calle, int numero, String comuna, String region) {
        this.calle = calle;
        this.numero = numero;
        this.comuna = comuna;
        this.region = region;
    }

    /**
     * Obtiene la calle de la dirección.
     * @return calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Obtiene el número de la dirección.
     * @return numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtiene la comuna de la dirección.
     * @return comuna
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * Obtiene la región de la dirección.
     * @return region
     */
    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return calle + " " + numero + ", " + comuna + ", " + region;
    }
}