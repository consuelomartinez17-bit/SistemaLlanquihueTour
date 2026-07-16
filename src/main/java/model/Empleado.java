package model;

/**
 * Representa a un empleado de la agencia Llanquihue Tour,
 * como guías turísticos u operadores internos.
 *
 * @author consu
 * @version 1.0
 */
public class Empleado extends Persona {

    private String cargo;
    private double sueldo;

    /**
     * Constructor con parámetros.
     * Construye un empleado con sus datos particulares.
     *
     * @param nombre    nombre completo del empleado
     * @param rut       RUT ya validado del empleado
     * @param direccion dirección del empleado
     * @param telefono  teléfono de contacto del empleado
     * @param cargo     cargo que ocupa dentro de la agencia
     * @param sueldo    sueldo mensual del empleado
     */
    public Empleado(String nombre, Rut rut, Direccion direccion, String telefono,
                    String cargo, double sueldo) {
        super(nombre, rut, direccion, telefono);
        this.cargo = cargo;
        this.sueldo = sueldo;
    }

    /**
     * Constructor sobrecargado sin sueldo especificado.
     * Construye un empleado asumiendo un sueldo inicial de $0,
     * útil para registrar el cargo antes de definir su remuneración.
     *
     * @param nombre    nombre completo del empleado
     * @param rut       RUT ya validado del empleado
     * @param direccion dirección del empleado
     * @param telefono  teléfono de contacto del empleado
     * @param cargo     cargo que ocupa dentro de la agencia
     */
    public Empleado(String nombre, Rut rut, Direccion direccion, String telefono, String cargo) {
        this(nombre, rut, direccion, telefono, cargo, 0.0);
    }

    /**
     * Obtiene el cargo del empleado.
     * @return cargo
     */
    public String getCargo() {
        return cargo;
    }
    /**
     * Actualiza el cargo del empleado.
     * @param cargo nuevo cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Obtiene el sueldo del empleado.
     * @return sueldo
     */
    public double getSueldo() {
        return sueldo;
    }

    /**
     * Actualiza el sueldo del empleado.
     * @param sueldo nuevo sueldo
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("[Empleado]");
        System.out.println(this);
        System.out.println("Cargo: " + cargo);
    }

    @Override
    public String toString() {
        return super.toString() + "\nCargo: " + cargo;
    }
}