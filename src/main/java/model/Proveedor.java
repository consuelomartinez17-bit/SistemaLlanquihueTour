package model;

/**
 * Representa a un proveedor externo que presta servicios.txt a la
 * agencia Llanquihue Tour, como transporte o alojamiento.
 *
 * @author consu
 * @version 1.0
 */
public class Proveedor extends Persona {

    private String empresa;
    private String rubro;

    /**
     * Constructor con parámetros.
     * Construye un proveedor con sus datos particulares.
     *
     * @param nombre    nombre de contacto del proveedor
     * @param rut       RUT ya validado del proveedor
     * @param direccion dirección del proveedor
     * @param telefono  teléfono de contacto del proveedor
     * @param empresa   nombre de la empresa que representa
     * @param rubro     rubro del servicio que provee (ej. transporte, alojamiento)
     */
    public Proveedor(String nombre, Rut rut, Direccion direccion, String telefono,
                     String empresa, String rubro) {
        super(nombre, rut, direccion, telefono);
        this.empresa = empresa;
        this.rubro = rubro;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getRubro() {
        return rubro;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("[Proveedor]");
        System.out.println(this);
        System.out.println("Empresa: " + empresa + " | Rubro: " + rubro);
    }

    @Override
    public String toString() {
        return super.toString() + "\nEmpresa: " + empresa + " | Rubro: " + rubro;
    }
}