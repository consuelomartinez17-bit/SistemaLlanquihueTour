package model;

/**
 * Representa a un cliente que solicita servicios.txt turísticos
 * a la agencia Llanquihue Tour.
 *
 * @author consu
 * @version 1.0
 */
public class Cliente extends Persona {

    private String correo;

    /**
     * Constructor con parámetros.
     * Construye un cliente con sus datos particulares.
     *
     * @param nombre    nombre completo del cliente
     * @param rut       RUT ya validado del cliente
     * @param direccion dirección del cliente
     * @param telefono  teléfono de contacto del cliente
     * @param correo    correo electrónico del cliente
     */
    public Cliente(String nombre, Rut rut, Direccion direccion, String telefono, String correo) {
        super(nombre, rut, direccion, telefono);
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("[Cliente]");
        System.out.println(this);
        System.out.println("Correo: " + correo);
    }

    @Override
    public String toString() {
        return super.toString() + "\nCorreo: " + correo;
    }
}