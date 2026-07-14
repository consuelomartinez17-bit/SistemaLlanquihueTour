package data;

import model.ServicioTuristico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la colección de servicios.txt turísticos del sistema,
 * permitiendo cargarlos desde un archivo de texto, agregarlos,
 * recorrerlos y buscarlos por código.
 *
 * @author consu
 * @version 1.0
 */
public class GestorServicios {

    private List<ServicioTuristico> servicios;

    /**
     * Constructor sin parámetros.
     * Inicializa la colección vacía de servicios.txt turísticos.
     */
    public GestorServicios() {
        this.servicios = new ArrayList<>();
    }

    /**
     * Carga los servicios.txt turísticos desde un archivo de texto plano,
     * donde cada línea representa un servicio en formato:
     * codigo,nombre,descripcion,precio,cuposDisponibles
     *
     * Si el archivo no existe, se informa por consola sin detener el
     * programa. Si una línea está mal formada o tiene un valor numérico
     * inválido, se omite esa línea específica y se continúa con las
     * siguientes, informando el número de línea afectada.
     *
     * @param rutaArchivo ruta del archivo de servicios.txt a cargar
     */
    public void cargarDesdeArchivo(String nombreArchivo) {
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(nombreArchivo)))) {

            String linea;
            int numeroLinea = 0;

            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");
                if (datos.length < 5) {
                    System.out.println("Línea " + numeroLinea + " ignorada: formato incompleto.");
                    continue;
                }

                try {
                    String codigo = datos[0].trim();
                    String nombre = datos[1].trim();
                    String descripcion = datos[2].trim();
                    double precio = Double.parseDouble(datos[3].trim());
                    int cupos = Integer.parseInt(datos[4].trim());

                    servicios.add(new ServicioTuristico(codigo, nombre, descripcion, precio, cupos));

                } catch (NumberFormatException e) {
                    System.out.println("Línea " + numeroLinea +
                            " ignorada: precio o cupos no son números válidos.");
                }
            }

        } catch (IOException | NullPointerException e) {
            System.out.println("No se pudo leer el archivo de servicios.txt: " + nombreArchivo);
        }
    }

    /**
     * Agrega un nuevo servicio turístico a la colección.
     * @param servicio servicio a agregar
     */
    public void agregarServicio(ServicioTuristico servicio) {
        if (servicio != null) {
            servicios.add(servicio);
        }
    }

    /**
     * Busca un servicio turístico por su código único.
     * @param codigo código del servicio a buscar
     * @return el servicio encontrado, o null si no existe
     */
    public ServicioTuristico buscarPorCodigo(String codigo) {
        for (ServicioTuristico s : servicios) {
            if (s.getCodigo().equalsIgnoreCase(codigo)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Obtiene la colección completa de servicios.txt turísticos.
     * @return lista de servicios.txt
     */
    public List<ServicioTuristico> getServicios() {
        return servicios;
    }

    /**
     * Muestra por consola todos los servicios.txt turísticos registrados.
     * Si la colección está vacía, informa explícitamente en vez de
     * no mostrar nada.
     */
    public void mostrarTodos() {
        if (servicios.isEmpty()) {
            System.out.println("No hay servicios.txt turísticos cargados.");
            return;
        }
        for (ServicioTuristico s : servicios) {
            System.out.println(s);
            System.out.println("----------------------------------------");
        }
    }
}
