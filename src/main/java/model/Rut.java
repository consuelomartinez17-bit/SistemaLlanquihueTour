package model;

import utils.RutInvalidoException;

/**
 * Representa y valida el RUT de una persona, verificando tanto el
 * formato como la correctitud matemática del dígito verificador
 * mediante el algoritmo módulo 11.
 *
 * @author consu
 * @version 1.0
 */
public class Rut {

    private String numero;      // sin puntos ni guión, ej: "12345678"
    private String digitoVerificador; // "0"-"9" o "K"

    /**
     * Constructor con parámetro.
     * Construye y valida un RUT a partir de su representación en texto.
     *
     * @param rutCompleto RUT en formato "12345678-9" o "12.345.678-9"
     * @throws RutInvalidoException si el RUT es nulo, vacío, tiene
     *         formato incorrecto, o su dígito verificador no calza
     */
    public Rut(String rutCompleto) throws RutInvalidoException {
        if (rutCompleto == null || rutCompleto.trim().isEmpty()) {
            throw new RutInvalidoException("El RUT no puede estar vacío.");
        }

        // Normaliza: quita puntos y espacios, deja solo número y guión
        String limpio = rutCompleto.replace(".", "").replace(" ", "").trim();

        if (!limpio.matches("^\\d{7,8}-[\\dkK]$")) {
            throw new RutInvalidoException(
                    "Formato de RUT inválido. Use el formato 12345678-9.");
        }

        String[] partes = limpio.split("-");
        this.numero = partes[0];
        this.digitoVerificador = partes[1].toUpperCase();

        if (!calcularDigitoVerificador(numero).equals(digitoVerificador)) {
            throw new RutInvalidoException(
                    "El dígito verificador no corresponde al RUT ingresado.");
        }
    }

    /**
     * Calcula el dígito verificador correcto para un número de RUT
     * dado, aplicando el algoritmo módulo 11.
     *
     * @param numero número de RUT sin dígito verificador
     * @return dígito verificador correcto ("0"-"9" o "K")
     */
    private String calcularDigitoVerificador(String numero) {
        int suma = 0;
        int multiplicador = 2;

        for (int i = numero.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(numero.charAt(i)) * multiplicador;
            multiplicador++;
            if (multiplicador > 7) {
                multiplicador = 2;
            }
        }

        int resto = 11 - (suma % 11);

        if (resto == 11) return "0";
        if (resto == 10) return "K";
        return String.valueOf(resto);
    }

    /**
     * Obtiene el RUT completo formateado con guión.
     * @return RUT en formato "12345678-9"
     */
    public String getRutFormateado() {
        return numero + "-" + digitoVerificador;
    }

    @Override
    public String toString() {
        return getRutFormateado();
    }
}