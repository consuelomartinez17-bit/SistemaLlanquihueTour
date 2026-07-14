# Sistema Llanquihue Tour

## Descripción general del sistema

Prototipo de software orientado a objetos desarrollado para la agencia de
turismo **Llanquihue Tour**, ubicada en la comuna de Llanquihue, Región de
Los Lagos. El sistema digitaliza la gestión de personas (clientes, guías
turísticos y proveedores), servicios turísticos y reservas, aplicando
encapsulamiento, composición, colecciones, herencia, polimorfismo e
interfaces.

El prototipo resuelve las principales problemáticas administrativas de la
agencia: centraliza la información de personas evitando duplicidad,
diferencia formalmente entre clientes, guías y proveedores mediante una
jerarquía de clases, gestiona reservas evitando la sobreventa de cupos, y
carga datos de prueba desde archivos de texto externos.

## Clases principales y sus funciones

### Paquete `model`
- **`Persona`** *(abstracta)*: clase base con los atributos comunes (nombre,
  RUT, dirección, teléfono), implementa `Registrable`.
- **`Cliente`**, **`Empleado`**, **`Proveedor`**: extienden `Persona`,
  cada una con atributos propios (correo, cargo/sueldo, empresa/rubro).
- **`Rut`**: valida el RUT mediante algoritmo módulo 11, lanza
  `RutInvalidoException` si el formato o el dígito verificador son incorrectos.
- **`Direccion`**: representa la dirección de una persona (composición).
- **`ServicioTuristico`**: representa un servicio turístico ofrecido por
  la agencia, con control de cupos disponibles.
- **`TarjetaPago`**: representa el medio de pago de una reserva, enmascarando
  el número de tarjeta por seguridad (composición).
- **`Reserva`**: compone a `Cliente`, `ServicioTuristico` y `TarjetaPago`;
  valida cupos disponibles y cantidad de personas antes de confirmarse.
  Implementa `Registrable`.
- **`Registrable`** *(interfaz)*: contrato común con los métodos
  `registrar()` y `mostrarDatos()`.

### Paquete `utils`
- **`RutInvalidoException`**: excepción personalizada para RUT inválidos.

### Paquete `data`
- **`GestorPersonas`**: colección `ArrayList<Persona>`; carga clientes desde
  archivo `.txt`, agrega, busca por nombre y filtra por tipo mediante `instanceof`.
- **`GestorServicios`**: colección `ArrayList<ServicioTuristico>`; carga
  servicios desde archivo `.txt`, agrega y busca por código.
- **`GestorReservas`**: colección `HashMap<String, Reserva>` indexada por
  código de reserva, para búsqueda directa y eficiente.

### Paquete `app`
- **`Main`**: punto de entrada del sistema, delega el control a `MenuPrincipal`.
- **`MenuPrincipal`**: controla el menú interactivo por consola (mostrar
  servicios, mostrar personas, buscar cliente, crear reserva, mostrar reservas).

## Datos de entrada

El sistema carga datos de prueba desde dos archivos de texto ubicados en
`src/main/resources/`:

- **`servicios.txt`**: formato `codigo,nombre,descripcion,precio,cuposDisponibles`
- **`clientes.txt`**: formato `nombre,rut,calle,numero,comuna,region,telefono,correo`

Además, un empleado y un proveedor de ejemplo se cargan directamente desde
código al iniciar el sistema, para demostrar las tres subclases de `Persona`
en una misma colección.

## Instrucciones para clonar y ejecutar el proyecto

1. Clona este repositorio:
   git clone https://github.com/consuelomartinez17-bit/SistemaLlanquihueTour.git

2. Abre el proyecto en **IntelliJ IDEA** (se detectará automáticamente como
   proyecto Maven gracias al `pom.xml`).
3. Espera a que Maven descargue las dependencias del proyecto.
4. Ejecuta la clase `Main`, ubicada en el paquete `app`.
5. Usa el menú interactivo por consola para:
    - Mostrar los servicios turísticos cargados.
    - Mostrar las personas registradas, diferenciadas por tipo.
    - Buscar un cliente por nombre.
    - Crear una nueva reserva (ejemplo: RUT `12345678-5`, servicio `SVT001`).
    - Mostrar todas las reservas registradas.

## Autora

Consuelo Martínez — Desarrollo Orientado a Objetos I, Duoc UC.