package model;

/**
 * Clase base abstracta que representa a una persona dentro del
 * sistema Llanquihue Tour. Mediante composición, cada persona
 * tiene asociado un Rut y una Direccion.
 *
 * @author consu
 * @version 1.0
 */
public abstract class Persona implements Registrable {

    protected String nombre;
    protected Rut rut;
    protected Direccion direccion;
    protected String telefono;

    /**
     * Constructor con parámetros.
     * Construye una persona con sus datos comunes.
     *
     * @param nombre    nombre completo de la persona
     * @param rut       RUT ya validado de la persona
     * @param direccion dirección de residencia de la persona
     * @param telefono  número de contacto de la persona
     */
    public Persona(String nombre, Rut rut, Direccion direccion, String telefono) {
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public Rut getRut() {
        return rut;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    /**
     * Registra a la persona en el sistema. Por defecto confirma
     * su incorporación; las subclases pueden sobrescribir este
     * comportamiento si necesitan una lógica de registro distinta.
     */
    @Override
    public void registrar() {
        System.out.println(nombre + " ha sido registrado/a en el sistema.");
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "\nRUT: " + rut +
                "\nDirección: " + direccion +
                "\nTeléfono: " + telefono;
    }
}