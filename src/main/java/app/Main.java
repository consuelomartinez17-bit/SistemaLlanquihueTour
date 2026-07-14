package app;

/**
 * Clase principal del sistema Llanquihue Tour. Su única responsabilidad
 * es iniciar la aplicación, delegando el control del menú a
 * MenuPrincipal para evitar concentrar la lógica de interacción
 * directamente en main().
 *
 * @author consu
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.iniciar();
    }
}
