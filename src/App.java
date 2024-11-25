import java.text.DecimalFormat;
import java.util.Scanner;

public class App {
    // SCANNER PARA ENTRADA DE DATOS DEL USUARIO
    static Scanner entry = new Scanner(System.in);

    // ARREGLO DE PLANETAS Y SUS DISTANCIAS DESDE LA TIERRA
    static String[] planetas = { "Mercurio", "Venus", "Marte", "Jupiter", "Saturno", "Urano", "Neptuno" };
    static long[] distancias = { 77000000L, 41000000L, 55000000L, 588000000L, 1275000000L, 2900000000L, 4500000000L };

    // INFORMACION DE LAS NAVES
    static String[] naves = { "Nave Nebulon", "Nave Eclipse", "Nave Aurora" };
    static int[] capacidades = { 150, 300, 350 };
    static double[] velocidades = { 35000, 43000, 50000 };

    // INFORMACION DE LOS RECURSOS
    // consumo de combustible por hora
    static double combustiblePorHora = 200.0; // Unidades consumidas por hora
    // consumo de oxígeno por hora
    static double oxigenoPorHora = 60.0; // Unidades consumidas por hora
    static long combustible = 100_000_000; // Unidades iniciales de combustible
    static long oxigeno = 100_000_000; // Unidades iniciales de oxígeno

    // VARIABLES PARA ALMACENAR DATOS
    static String naveSeleccionada;
    static String planetaSeleccionado;
    static long distanciaPlanetaSeleccionado;
    static double velocidadNaveSeleccionada;
    static int opcion;
    static int opcionNave;

    public static void main(String[] args) throws Exception {
        menuPrincipal(); // MOSTRAR MENU PRINCIPAL

    }

    public static void menuPrincipal() {
        boolean salir = false;
        boolean opcion1Seleccionada = false; // CONTROLA SELECCION PLANETAS
        boolean opcion2Seleccionada = false; // CONTROLA SELECCION NAVES

        while (!salir) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Seleccionar planeta de destino");
            System.out.println("2. Seleccionar una nave espacial");
            System.out.println("3. Iniciar el vuelo");
            System.out.println("4. Salir");
            System.out.print("Por favor, elige una opción: ");

            if (entry.hasNextInt()) { // VERIFICAR SI LA ENTRADA ES UN NUMERO
                int opcion = entry.nextInt(); // ALMACENAR LA OPCION DEL USUARIO
                entry.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        /*
                         * OPCION SELECCIONAR PLANETA DE DESTINO
                         * SI LA OPCION 1 NO HA SIDO SELECCIONADA AUN EL USUARIO NO PUEDE AVANZAR A
                         * NINGUNA DE LAS SIGUIENTES
                         * EXEPTUANDO LA DE SALIR.
                         * DEBE SEGUIR UN ORDEN COHERENTE.
                         */
                        // SI EL PLANETA NO HA SIDO SELECCIONADO
                        if (!opcion1Seleccionada) {
                            seleccionPlanetas();// LLAMAR EL METODO PARA SELECCIONAR PLANETA
                            System.out.println("_");
                            System.out.println("PRESIONE 8 para CONFIRMAR y VOLVER AL MENU PRINCIPAL");
                            // VERIFICAR SI EL USUARIO PRESIONA 8 PARA CONFIRMAR Y QUE INGRESA UN NUMERO
                            // ENTERO
                            if (entry.hasNextInt() && entry.nextInt() == 8) {
                                opcion1Seleccionada = true; // CONFIRMAR LA SELECCION DEL PLANETA
                                System.out.println("Planeta de destino confirmado correctamente.");
                            } else {
                                System.out.println("Debes presionar 8 para confirmar.");
                            }
                        } else {
                            System.out.println("Ya seleccionaste un planeta de destino. Procede a la opción 2.");
                        }
                        break;

                    case 2:
                        if (opcion1Seleccionada && !opcion2Seleccionada) { // SI EL PLANETA YA ESTA SELECCIONADO PERO LA
                                                                           // NAVE AUN NO
                            seleccionNaves(); // LLAMAR EL METODO PARA SELECCIONAR NAVES
                            System.out.println("_");
                            System.out.println("PRESIONE 8 para CONFIRMAR y VOLVER AL MENU PRINCIPAL");
                            if (entry.hasNextInt() && entry.nextInt() == 8) {
                                opcion2Seleccionada = true; // CONFIRMAR SELECCION DE NAVE
                                System.out.println("Nave espacial confirmada correctamente.");
                            } else {
                                System.out.println("Debes presionar 8 para confirmar.");
                            }
                        } else if (!opcion1Seleccionada) { // SI EL PLANETA NO HA SIDO SELECCIONADO
                            System.out.println("Primero debes seleccionar un planeta de destino (opción 1).");
                        } else {
                            System.out.println("Ya seleccionaste una nave. Procede a la opción 3.");
                        }
                        break;

                    case 3:
                        // MOSTRAR EL TIEMPO ESTIMADO DE VIAJE DESDE LA TIERRA HASTA EL PLANETA
                        tiempoDeViaje();
                        // recursos
                        verificarRecursos(distanciaPlanetaSeleccionado);
                        if (opcion1Seleccionada && opcion2Seleccionada) { // SI LA NAVE Y EL PLANETA YA FUERON
                                                                          // SELECCIONADOS
                            iniciarSimulacionViaje(opcion); // INICIAR SIMULACION!

                        } else if (!opcion1Seleccionada) { // SI LA OPCION DEL PLANETA NO HA SIDO SELECCIONADO
                            System.out.println("Primero debes seleccionar un planeta de destino (opción 1).");
                        } else { // SI LA NAVE NO HA SIDO SELECCIONADA
                            System.out.println("Debes seleccionar una nave antes de iniciar el vuelo (opción 2).");
                        }
                        break;

                    case 4: // SALIR DEL PROGRAMA
                        System.out.println("Saliendo del programa. ¡Gracias por usar nuestra aplicación!");
                        salir = true;
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, ingrese un número del 1 al 4.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                entry.nextLine(); // LIMPIAR BUFFER
            }
        }
    }

    public static void seleccionNaves() {

        System.out.println("----------------");
        System.out.println("Naves Espaciales");
        System.out.println("----------------");

        // ITERAR LAS NAVES CON EL BUCLE FOR
        for (int i = 0; i < naves.length; i++) {
            // MOSTRAR NAVES DISPONIBLES RECORRIENDO EL ARREGLO DE NAVES
            System.out.println((i + 1) + " " + naves[i]); // Se suma 1 a 'i' para que el usuario vea una lista
                                                          // comenzando en 1 en lugar de 0
            // MOSTRAR CAPACIDAD Y VELOCIDAD DE CADA NAVE
            mostrarCapacidadYVelocidadNaves(i); // Llama a un método que muestra detalles adicionales de la nave

        }
        // PEDIR AL USUARIO QUE ELIGA UNA DE LAS NAVES
        System.out.println("Seleccione una nave: ");
        opcionNave = entry.nextInt(); // Captura la opción seleccionada por el usuario
        entry.nextLine(); // Limpia el buffer de entrada
        System.out.println("Ingrese la cantidad de pasajeros:");
        var pasajeros = entry.nextInt(); // Captura la cantidad de pasajeros ingresada por el usuario

        // VERIFICAR SI EL INGRESO DE PASAJEROS EXCEDE LA CAPACIDAD DE LA NAVE
        if (opcionNave == 1 && pasajeros <= 150) {
            System.out.println("Selección exitosa!");
        } else if (opcionNave == 2 && pasajeros <= 300) {
            System.out.println("Selección exitosa!");
        } else if (opcionNave == 3 && pasajeros <= 350) {
            System.out.println("Selección exitosa!");
        } else {
            System.out.println("Cantidad de pasajeros no valida! Intente de nuevo");
            seleccionNaves(); // Llama nuevamente a la función si el número de pasajeros excede la capacidad
        }

        // almacenar velocidad nave seleccionada
        /*
         * Se usa opcionNave - 1 para obtener la nave seleccionada y su velocidad del
         * array. Esto se debe a que los arrays en Java están indexados desde 0, pero el
         * usuario selecciona comenzando en 1.
         */
        naveSeleccionada = naves[opcionNave - 1]; // Almacena la nave seleccionada usando un índice ajustado a 0
        velocidadNaveSeleccionada = velocidades[opcionNave - 1]; // Almacena la velocidad de la nave seleccionada usando
                                                                 // un índice ajustado a 0

        // VERIFICAR QUE SELECCIONO UNA NAVE VALIDA
        if (opcionNave > 0 && opcionNave <= 3) { // Asegura que la opción de la nave esté dentro del rango válido
            System.out.println("SELECCIONASTE LA NAVE: " + naveSeleccionada);
        } else {

            System.out.println("Opción inválida, intente de nuevo.");
            seleccionNaves(); // Llama nuevamente a la función si la opción es inválida
        }

    }

    private static void mostrarCapacidadYVelocidadNaves(int i) {
        if (i == 0) {
            // velocidad
            System.out.println("Velocidad de la Nave Nebulon: " + velocidades[0] + " km/h");
            // pasajeros
            System.out.println("Capacidad: Pasajeros: " + capacidades[0]);

        } else if (i == 1) {
            System.out.println("Velocidad de la Nave Eclipse: " + velocidades[1] + " km/h");
            // pasajeros
            System.out.println("Capacidad: Pasajeros: " + capacidades[1]);
        } else if (i == 2) {
            System.out.println("Velocidad de la Nave Eclipse: " + velocidades[2] + " km/h");
            // pasajeros
            System.out.println("Capacidad: Pasajeros: " + capacidades[2]);
        }
    }

    public static void seleccionPlanetas() {

        System.out.println("--------");
        System.out.println("Planetas");
        System.out.println("--------");

        // MOSTRAR OPCIONES DE PLANETAS
        System.out.println(
                "Seleccione un Planeta:\n1.Mercurio\n2.Venus\n3.Marte\n4.Jupiter\n5.Saturno\n6.Urano\n7.Neptuno");
        int seleccionPlaneta = entry.nextInt();
        entry.nextLine();
        // ALMACENAR EL PLANETA SELECCIONADO Y LA DISTANCIA
        // El índice seleccionado por el usuario se ajusta restando 1 para acceder
        // correctamente al array
        planetaSeleccionado = planetas[seleccionPlaneta - 1];
        distanciaPlanetaSeleccionado = distancias[seleccionPlaneta - 1];

        // MOSTRAR INFORMACION DEL PLANETA SELECCIONADO
        System.out.println("Has seleccionado: " + planetas[seleccionPlaneta - 1] + "\n");

        // MOSTRAR DISTANCIA DEL PLANETA SELECCIONADO DESDE LA TIERRA
        System.out
                .println("Distancia desde la Tierra: " + distancias[seleccionPlaneta - 1] + " millones de Kilometros");

        // MOSTRAR INFORMACION DEL PLANETA SELECCIONADO
        // Mostrar información adicional del planeta seleccionado llamando a un método
        // específico
        imprimirInformacionPlaneta(seleccionPlaneta); // Llama a un método para imprimir información detallada del
                                                      // planeta seleccionado

    }

    public static void tiempoDeViaje() {

        /*
         * El tiempo de viaje se calcula dividiendo la distancia al planeta seleccionado
         * por la velocidad de la nave seleccionada, y luego dividiendo el resultado por
         * 24 para convertir el tiempo a días
         */

        // tiempo = (distancia / velocidad) / 24
        // Calcular el tiempo estimado de viaje en días, dado que la distancia se divide
        // por la velocidad y luego se convierte a días

        long tiempoEstimado = (long) (distanciaPlanetaSeleccionado / velocidadNaveSeleccionada) / 24;

        System.out.println("TIEMPO DE VIAJE ESTIMADO");
        System.out.println(
                "A continuación se estimarán los tiempos de viaje y recursos para ir al planeta en nuestro sistema solar:");
        // Mostrar el tiempo estimado para llegar al planeta seleccionado
        System.out.println("El tiempo estimado para ir al planeta " + planetaSeleccionado + " con la "
                + naveSeleccionada + " es de " + tiempoEstimado + " DIAS.");
        // Mostrar el combustible disponible
        System.out.println("Combustible disponible: " + combustible + " unidades.");

        // Mostrar el oxígeno disponible
        System.out.println("Oxígeno disponible: " + oxigeno + " unidades.");
        System.out.println("------------------------------------------------------------");
    }

    public static void verificarRecursos(long distancia) {
        // tiempo estimado en horas
        long tiempoEstimadoHoras = (long) (distanciaPlanetaSeleccionado / velocidadNaveSeleccionada);

        // Calcular los recursos necesarios basados en el tiempo estimado de viaje
        long combustibleNecesario = (long) (tiempoEstimadoHoras * combustiblePorHora);
        long oxigenoNecesario = (long) (tiempoEstimadoHoras * oxigenoPorHora);

        // verificar si hay recursos suficientes
        if (combustible >= combustibleNecesario && oxigeno >= oxigenoNecesario) {
            // Si los recursos son suficientes, imprimir un mensaje de confirmación
            System.out.println("¡Recursos suficientes para el viaje a" + planetaSeleccionado);
            System.out.println("COMBUSTIBLE NECESARIO: " + combustibleNecesario + " unidades");
            System.out.println("OXÍGENO NECESARIO: " + oxigenoNecesario + " unidades");
        } else {
            // Si no hay suficientes recursos, imprimir un mensaje de advertencia
            System.out.println("¡ATENCION! No hay recursos suficientes para completar el viaje");
            // Verificar si falta combustible
            if (combustible < combustibleNecesario) {
                double combustibleFaltante = combustibleNecesario - combustible;
                System.out.println("FALTA COMBUSTIBLE: " + combustible + " unidades.");
                recargarCombustible(combustibleFaltante); // llamar un metodo para recargar combustible

            }
            // Verificar si falta oxígeno
            if (oxigeno < oxigenoNecesario) {
                System.out.println("FALTA OXÍGENO: " + (oxigenoNecesario - oxigeno) + " unidades.");
                double oxigenoFaltante = oxigenoNecesario - oxigeno;
                recargarOxigeno(oxigenoFaltante); // llamar un metodo para recargar oxigeno
            }
        }

    }

    private static void recargarOxigeno(double oxigenoFaltante) {
        // Mostrar mensaje para recargar oxígeno
        System.out.println("________________________________________");
        System.out.println("|                                       |");
        System.out.println("|   Oprima O para recargar el oxígeno   |");
        System.out.println("________________________________________");
        // Leer la entrada del usuario para recargar oxígeno
        String recargarOxigeno = entry.nextLine().trim();
        // Verificar si el usuario ingresó 'O' para recargar el oxígeno
        if (recargarOxigeno.length() == 1 && recargarOxigeno.equalsIgnoreCase("O")) {
            // Aumentar la cantidad de oxígeno disponible
            oxigeno += oxigenoFaltante;
            System.out.println("Oxígeno recargado!");
            // Iniciar la simulación del viaje con la nueva cantidad de oxígeno
            iniciarSimulacionViaje(oxigeno);
        } else {
            // Mensaje de cancelación si el usuario no presiona 'O'
            System.out.println("_______________________________________________________");
            System.out.println("|  Recarga de oxígeno cancelada. Vuelva a intentarlo  |");
            System.out.println("_______________________________________________________");
            menuPrincipal(); // volver al menuPrincipal
        }
    }

    private static void recargarCombustible(double combustibleFaltante) {
        // mostrar mensaje para recargar combustible
        System.out.println("____________________________________________");
        System.out.println("|                                           |");
        System.out.println("|   Oprima R para recargar el combustible   |");
        System.out.println("____________________________________________");
        // Leer la entrada del usuario para recargar combustible
        String recargarCombustible = entry.nextLine().trim();
        // Verificar si el usuario ingresó 'R' para recargar el combustible
        if (recargarCombustible.length() == 1 && recargarCombustible.equalsIgnoreCase("R")) {
            // Aumentar la cantidad de combustible disponible
            combustible += combustibleFaltante;
            System.out.println("Combustible recargado!");
            // Iniciar la simulación del viaje con la nueva cantidad de combustible
            iniciarSimulacionViaje(combustible);
        } else {
            // Mensaje de cancelación si el usuario no presiona 'R'
            System.out.println("________________________________________________________");
            System.out.println("| Recarga de combustible cancelada. Vuelva a intentarlo |");
            System.out.println("________________________________________________________");
            menuPrincipal(); // ir a menu principal
        }
    }

    private static void iniciarSimulacionViaje(long tiempoEstimado) {
        // Calcular el tiempo estimado en milisegundos para la duración total del viaje
        long tiempoMilisegundos = tiempoEstimado * 3600 * 1000;
        // Establecer el tiempo que representa el progreso en porcentaje durante la
        // simulación
        var tiempoPorcentaje = 15000;
        System.out.println("Iniciando simulacion del viaje....");
        // Bucle para simular el progreso del viaje en incrementos del 10%
        for (int i = 0; i <= 100; i += 10) {
            // Mostrar el progreso del viaje
            System.out.println("________________________________________");
            System.out.println(" |  🚀 Progreso del viaje " + i + "% 🚀  |");
            System.out.println("________________________________________");

            if (i == 10) {
                System.out.println(
                        "🚀 Apenas hemos alcanzado el 10% del viaje. Todo marcha según lo planeado, pero aún queda un largo camino por recorrer. Disfruta de las vistas del espacio infinito mientras continuamos hacia "
                                + planetaSeleccionado + ". 🌌");
            } else if (i == 20) {

                getAbrocharCinturon(); // Llama a un método para abrocharse el cinturón
            } else if (i == 30) {
                System.out.println(
                        "⚠ ¡ATENCION! ⚠ \nEstamos en un momento de gran explosion de asteroides! No te preocupes, tu cinturon te protege.");
            } else if (i == 40) {
                System.out.println(
                        "Estamos a pocas horas para llegar a la mitad del viaje.\nSIGUE DISFRUTANDO DE LAS HERMOSAS VISTAS!");
            } else if (i == 50) {
                System.out.println("Mitad del camino.");
            } else if (i == 60) {
                getEscudos(); // Llama a un método para activar los escudos
            } else if (i == 70) {
                getActivarModoDefensivo(); // Llama a un método para activar el modo defensivo

            } else if (i == 80) {
                System.out.println("Contacto con base espacial cercana. Actualizando coordenadas.");
            } else if (i == 90) {
                System.out.println("🌟 ¡Estamos al 90% del viaje! El destino está a la vista. Los sensores detectan a "
                        + planetaSeleccionado
                        + " en el horizonte. 🪐 Prepárate para la llegada, ajustando sistemas y asegurando la nave. ¡La aventura está por comenzar! 🚀");
            } else if (i == 100) {
                System.out.println("! El viaje ha finalizado con éxito. Disfruta de tu aventura!");

            }

            // Pausa la simulación por el tiempo especificado para imitar el progreso en
            // tiempo real
            try {
                Thread.sleep(tiempoPorcentaje);
            } catch (InterruptedException e) {
                System.out.println("ERROR EN LA SIMULACION! " + e);
            }

        }
        // Mensaje final al completar el viaje
        System.out.println("Viaje finalizado con exito!");
        System.out.println("Haz llegado a " + planetaSeleccionado);
    }

    private static void getEscudos() {
        // muestra el evento
        System.out.println(
                "La nave atraviesa una región de radiación cósmica. 🗳  Escribe 'Ajustar escudos'  🗳   para Ajustar los Escudos.");
        String activarEscudos = entry.nextLine(); // captura la entrada del usuario

        // Verificar si la entrada del usuario es 'Ajustar escudos'
        if (activarEscudos.equals("Ajustar escudos")) {
            System.out.println(" ⚙  Ajustando escudos.  ⚙ ");
            System.out.println(" ✅   ESCUDOS AJUSTADOS!");
        } else {
            // Si la entrada no es correcta, se informa al usuario y se vuelve a llamar al
            // método
            System.out.println("No se pudo activar los Escudos. Vuelva a intentarlo.");
            getEscudos(); // Llama al método nuevamente para pedir la entrada correcta
        }
    }

    private static void getActivarModoDefensivo() {
        // muestra el evento
        System.out.println(
                "Detectamos una pequeña lluvia de meteoritos. 🗳  PRESIONE G para poner la Nave en modo defensivo.  🗳");

        // Captura la entrada del usuario para activar el modo defensivo
        String activarModoDefensivo = entry.nextLine().trim();

        // Verifica si la entrada del usuario es 'G' para activar el modo defensivo
        if (activarModoDefensivo.length() == 1 && activarModoDefensivo.equalsIgnoreCase("G")) {
            System.out.println(" 🛡  Iniciando MODO DEFENSIVO...  🛡 ");
            System.out.println(" 🛡 MODO DEFENSIVO ACTIVADO  🛡  ");
        } else {
            // Si la entrada no es correcta, se informa al usuario y se vuelve a llamar al
            // método
            System.out.println("No se pudo activar el modo defensivo. Vuelva a intentarlo.");
            getActivarModoDefensivo(); // llama al metodo nuevamente para pedir la entrada correcta
        }
    }

    private static void getAbrocharCinturon() {
        // muestra el evento
        System.out.println(
                "⚠ ¡ATENCION! ⚠ \nEstamos pasando en estos momentos por una lluvia de ☄ Meteoritos! Abroche su cinturon para salvaguardarse de las turbulencias.\n 🗳  OPRIMA LA LETRA C PARA ABROCHAR EL CINTURON  🗳");

        // Captura la entrada del usuario para abrochar el cinturon
        String abrocharCinturon = entry.nextLine().trim();

        // Verifica si la entrada del usuario es 'C' para abrochar el cinturon

        if (abrocharCinturon.length() == 1 && abrocharCinturon.equalsIgnoreCase("C")) {
            System.out.println("Cinturon activado! ✅");
        } else {
            // Si la entrada no es correcta, se informa al usuario y se vuelve a llamar al
            // método
            System.out.println("❌ No se pudo activar el cinturon. Vuelva a intentarlo.");
            getAbrocharCinturon(); // llama al metodo nuevamente para pedir la entrada correcta
        }
    }

    public static void imprimirInformacionPlaneta(int seleccionPlaneta) {
        switch (seleccionPlaneta) {
            case 1:

                System.out.println(
                        "Buena eleccion!\n" +
                                "Mercurio, el veloz mensajero del cosmos, es un pequeño y misterioso mundo de extremos. "
                                +
                                "Siendo el planeta más cercano al Sol, su superficie está marcada por cráteres ancestrales "
                                +
                                "y llanuras silenciosas, testigos de incontables impactos cósmicos. Durante el día, el calor "
                                +
                                "alcanza los abrasadores 430 °C, pero al caer la noche, se congela hasta los -180 °C, mostrando "
                                +
                                "el contraste de un planeta sin atmósfera que lo abrace. Mercurio orbita al Sol en tan solo 88 días "
                                +
                                "terrestres, convirtiéndose en el corredor más rápido del sistema solar. Aunque carece de lunas y "
                                +
                                "atmósfera densa, su historia geológica y su soledad en el espacio lo convierten en un enigma "
                                +
                                "fascinante, explorado solo por las misiones más audaces.");

                break;

            case 2:

                System.out.println(
                        "Buena eleccion!\n" +
                                "Venus, la brillante joya del firmamento, es un mundo de contrastes cautivadores y letales.  "
                                +
                                "Conocido como el “gemelo de la Tierra” por su tamaño y composición, su belleza oculta un corazón furioso:"
                                +
                                "un clima infernal con temperaturas que alcanzan los 465 °C, suficientes para derretir plomo, y una atmósfera densa"
                                +
                                "de dióxido de carbono cargada de nubes de ácido sulfúrico. Bajo esta envoltura, su superficie volcánica está salpicada de montañas y vastas"
                                +
                                "llanuras, mientras que vientos huracanados recorren el planeta a velocidades impresionantes. A pesar de su ferocidad, Venus tiene un encanto"
                                +
                                "hipnótico, girando lentamente al revés en una danza única que intriga tanto como desafía a los exploradores espaciales."

                );
                break;
            case 3:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Marte, el intrigante “Planeta Rojo”, es un desierto frío y polvoriento que despierta sueños de exploración y vida más allá"
                                +
                                "de la Tierra. Su paisaje está dominado por imponentes volcanes como el gigantesco Monte Olimpo, profundos cañones"
                                +
                                "como el Valles Marineris y vastas llanuras cubiertas de óxido, que dan al planeta su distintivo color rojizo."
                                +
                                "Aunque su atmósfera es delgada y helada, con temperaturas que caen hasta los -125 °C en sus polos, Marte alguna vez albergó"
                                +
                                "ríos y océanos, dejando rastros de su antiguo pasado acuoso. Cada amanecer y atardecer tiñe su cielo de un azul"
                                +
                                "suave, recordándonos su conexión única con la Tierra. Es un mundo de retos y promesas, un lugar donde la ciencia y la"
                                +
                                "imaginación sueñan con colonias humanas y nuevos comienzos.");
                break;

            case 4:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Júpiter, el gigante de gas y rey del sistema solar, es un mundo majestuoso y turbulento, adornado con nubes de colores"
                                +
                                "vibrantes y tormentas eternas. Su rasgo más famoso, la Gran Mancha Roja, es un huracán colosal que ha rugido por más"
                                +
                                "de 300 años, lo suficientemente grande como para tragarse tres Tierras. Aunque carece de una superficie sólida, Júpiter"
                                +
                                "es un tesoro cósmico con más de 90 lunas, incluyendo a Europa, que podría ocultar un océano bajo su capa de hielo, y Ío, un"
                                +
                                "volcánico espectáculo de fuego. Su inmenso campo magnético y su velocidad de rotación vertiginosa lo convierten en una maravilla"
                                +
                                "inigualable, un coloso que guarda secretos sobre el origen y la dinámica de nuestro sistema solar.");
                break;
            case 5:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Saturno, el hermoso y majestuoso rey de los anillos, es un planeta que deslumbra con su increíble belleza y misterio. Sus vastos"
                                +
                                "anillos, formados por polvo, rocas y hielo, crean un espectáculo visual único que lo convierte en uno de los objetos más fascinantes"
                                +
                                "del cielo nocturno. Saturno es un gigante gaseoso compuesto principalmente de hidrógeno y helio, con una atmósfera marcada por capas"
                                +
                                "de nubes doradas y tormentas violentas. A pesar de su tamaño imponente, con un diámetro 9 veces mayor que el de la Tierra, su baja"
                                +
                                "densidad significa que podría flotar en el agua si existiera un océano lo suficientemente grande. Con más de 80 lunas, incluidas Titán,"
                                +
                                "un mundo misterioso con una atmósfera densa y mares de metano, Saturno sigue siendo un enigma cósmico que desafía nuestra comprensión"
                                +
                                "y despierta la imaginación de los exploradores espaciales");
                break;
            case 6:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Urano, el planeta inclinado, es un mundo de extremos y misterios que orbita de lado, como si estuviera descansando en su"
                                +
                                "eje. Su atmósfera azul verdosa, compuesta principalmente de hidrógeno, helio y metano, le da un tono único y helado. Este"
                                +
                                "gigante gaseoso, que es más de 4 veces el tamaño de la Tierra, es hogar de vientos extremadamente rápidos que alcanzan"
                                +
                                "los 900 km/h, y aunque es conocido por sus bajas temperaturas de hasta -224 °C, no es el planeta más frío del sistema solar."
                                +
                                "Urano tiene una serie de anillos sutiles y más de 20 lunas, siendo Titania la más grande. Este planeta misterioso y lejano, "
                                +
                                "descubierto solo en el siglo XVIII, sigue siendo un desafío para los astrónomos, ya que su inclinación extrema y su ubicación"
                                +
                                "en el borde del sistema solar lo convierten en un objeto fascinante de estudio y exploración");
                break;
            case 7:
                System.out.println(
                        "Buena eleccion!\n" +
                                "Neptuno, el último de los gigantes gaseosos, es un mundo profundo y misterioso que se encuentra en las fronteras del sistema solar. "
                                +
                                "Su atmósfera, rica en hidrógeno, helio y metano, le da su distintivo color azul intenso, y su clima es uno de los más turbulentos"
                                +
                                "del sistema, con vientos que alcanzan hasta 2,100 km/h, los más rápidos conocidos en el sistema solar. A pesar de su lejanía, Neptuno"
                                +
                                "emite más calor del que recibe del Sol, lo que sugiere un núcleo caliente y activo. Su órbita es tan extensa que su año dura 165 años"
                                +
                                "terrestres, pero su día dura solo 16 horas. Neptuno posee 14 lunas conocidas, siendo Tritón la más grande y única en su tipo, ya que"
                                +
                                "orbita en dirección opuesta a la rotación del planeta. Este enigmático gigante sigue siendo un punto de fascinación para los astrónomos,"
                                +
                                "que continúan desentrañando sus secretos, a pesar de que solo ha sido visitado una vez por una nave espacial, Voyager 2.");
                break;

        }
    }
}
