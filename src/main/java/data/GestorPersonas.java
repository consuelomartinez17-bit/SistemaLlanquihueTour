package data;

import model.*;
import utils.RutInvalidoException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la colección polimórfica de personas del sistema
 * (clientes.txt, empleados y proveedores), permitiendo cargarlas desde
 * un archivo de texto, agregarlas, buscarlas y filtrarlas.
 *
 * @author consu
 * @version 1.0
 */
public class GestorPersonas {

    private List<Persona> personas;

    /**
     * Constructor sin parámetros.
     * Inicializa la colección vacía de personas.
     */
    public GestorPersonas() {
        this.personas = new ArrayList<>();
    }

    /**
     * Carga clientes.txt desde un archivo de texto plano, donde cada línea
     * tiene el formato:
     * nombre,rut,calle,numero,comuna,region,telefono,correo
     *
     * Si el archivo no existe, o una línea tiene formato inválido,
     * RUT inválido, o un valor numérico incorrecto, se informa y se
     * omite esa línea puntual sin detener la carga de las demás.
     *
     * @param nombreArchivo  ruta del archivo de clientes.txt a cargar
     */
    public void cargarClientesDesdeArchivo(String nombreArchivo) {
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(nombreArchivo)))) {
            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");
                if (datos.length < 8) {
                    System.out.println("Línea " + numeroLinea +
                            " ignorada: formato incompleto.");
                    continue;
                }

                try {
                    String nombre = datos[0].trim();
                    Rut rut = new Rut(datos[1].trim());
                    int numero = Integer.parseInt(datos[3].trim());
                    Direccion direccion = new Direccion(
                            datos[2].trim(), numero, datos[4].trim(), datos[5].trim());
                    String telefono = datos[6].trim();
                    String correo = datos[7].trim();

                    personas.add(new Cliente(nombre, rut, direccion, telefono, correo));

                } catch (RutInvalidoException e) {
                    System.out.println("Línea " + numeroLinea +
                            " ignorada: RUT inválido (" + e.getMessage() + ")");
                } catch (NumberFormatException e) {
                    System.out.println("Línea " + numeroLinea +
                            " ignorada: número de dirección inválido.");
                }
            }

        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de clientes.txt: " + nombreArchivo );
        }
    }

    /**
     * Agrega una persona (cliente, empleado o proveedor) a la colección.
     * @param persona persona a agregar
     */
    public void agregarPersona(Persona persona) {
        if (persona != null) {
            personas.add(persona);
        }
    }

    /**
     * Busca personas cuyo nombre contenga el texto indicado,
     * sin distinguir mayúsculas de minúsculas.
     *
     * @param texto texto a buscar dentro del nombre
     * @return lista de personas que coinciden con la búsqueda
     */
    public List<Persona> buscarPorNombre(String texto) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona p : personas) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Filtra y muestra las personas registradas, diferenciando su tipo
     * real (Cliente, Empleado o Proveedor) mediante instanceof.
     */
    public void mostrarTodasPorTipo() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }
        for (Persona p : personas) {
            if (p instanceof Cliente) {
                System.out.println("[Cliente]");
            } else if (p instanceof Empleado) {
                System.out.println("[Empleado]");
            } else if (p instanceof Proveedor) {
                System.out.println("[Proveedor]");
            } else {
                System.out.println("[Persona]");
            }
            System.out.println(p);
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Obtiene la colección completa de personas registradas.
     * @return lista de personas
     */
    public List<Persona> getPersonas() {
        return personas;
    }
}