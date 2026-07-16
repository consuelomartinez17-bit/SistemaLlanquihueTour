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

    /**
     * Obtiene el nombre de la persona.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el RUT de la persona.
     * @return rut
     */
    public Rut getRut() {
        return rut;
    }

    /**
     * Obtiene la dirección de la persona.
     * @return direccion
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Obtiene el teléfono de contacto de la persona.
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Actualiza el teléfono de contacto de la persona.
     * @param telefono nuevo número de teléfono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
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