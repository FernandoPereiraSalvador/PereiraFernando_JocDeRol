/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package inici;

import personatges.Jugador;
import altres.*;
import java.util.Random;
import static teclat.Pantalla.*;
import teclat.Teclat;

/**
 *
 * @author Fernando
 */
public class JocDeRol {

    /**
     * Esta función muestra un menú para configurar jugadores, equipos y potencias,
     * y permite al usuario seleccionar una opción para realizar la acción
     * correspondiente.
     */
    public static void menuConfiguracio() {
        int opcionMenu = -1;

        while (opcionMenu != 0) {
            printMenuTitle("CONFIGURACIÓN");
            System.out.println("1.Jugadors");
            System.out.println("2.Equips");
            System.out.println("3.Poders");
            System.out.println("0.Eixir");

            opcionMenu = Teclat.lligInt("Introduce la opcion: ");

            switch (opcionMenu) {
                case 1 -> Jugadors.menu();
                case 2 -> Equips.menu();
                case 3 -> Poders.menu();
                case 0 -> {
                }
                default -> System.out.println("Has introducido una opción erronea");
            }
        }
    }

    /**
     * La función "jugar" muestra un menú con dos opciones (automatizada y manual) y
     * permite al usuario elegir entre ellas.
     */
    public static void jugar() {

        int opcion = 0;

        printMenuTitle("JUGAR");
        System.out.println("1.Automatizat");
        System.out.println("2.Manual");
        System.out.println("0.Eixir");

        opcion = Teclat.lligInt("Introduce la opcion: ");

        switch (opcion) {
            case 1 ->
                Automatizat();
            case 2 ->
                Manual();
            default ->
                System.out.println("Opcion incorrecta");
        }
    }

    /**
     * Comprueba que haya suficientes jugadores para jugar una partida.
     *
     * @return Un true si no se puede jugar y false en caso contrario
     */
    public static boolean NoSePuedeJugar() {
        if (Jugadors.llista.size() == 1) {
            System.out.println("Se necesitan mínimo 2 jugadores");
            return true;
        } else if (Jugadors.llista.isEmpty()) {
            System.out.println("No hay jugadores");
            return true;
        }
        return false;
    }

    /**
     * Esta función automatiza el proceso de ataque entre jugadores seleccionados al
     * azar hasta que sólo quede un superviviente.
     */
    public static void Automatizat() {

        if (NoSePuedeJugar()) {
            return;
        }
        // Creamos dos jugadores random
        Jugador randomJugador1;
        Jugador randomJugador2;

        // Variables para detectar caso de empate
        int contadorEmpates = 0;
        int maxEmpates = 10;

        while (Jugadors.llista.size() > 1) {

            // Crea un objeto Random
            Random rand = new Random();

            // Genera un índice al azar dentro del rango de los índices del ArrayList
            int randomIndexJugador1 = rand.nextInt(Jugadors.llista.size());

            // Inicializa el segundo índice como el índice del primer jugador
            int randomIndexJugador2 = randomIndexJugador1;

            // Mientras los índices sean iguales, genera nuevos índices
            while (randomIndexJugador2 == randomIndexJugador1) {
                randomIndexJugador2 = rand.nextInt(Jugadors.llista.size());
            }

            // Obtiene los jugadores correspondientes utilizando los índices generados
            randomJugador1 = Jugadors.llista.get(randomIndexJugador1);
            randomJugador2 = Jugadors.llista.get(randomIndexJugador2);

            // Guardamos las vidas de antes del ataque para después detectar si ha habido un
            // empate
            int vidaAntesAtaque1 = randomJugador1.getVides();
            int vidaAntesAtaque2 = randomJugador2.getVides();

            // Intentamos atacar
            try {
                randomJugador1.ataca(randomJugador2);
            } catch (AtacAMortException | AtacEllMateixException e) {
                System.out.println(e.getMessage());
            }

            // Comprobamos si ha habido un empate, en caso contrario reiniciamos el
            // contador.
            if (vidaAntesAtaque1 == randomJugador1.getVides() && vidaAntesAtaque2 == randomJugador2.getVides()) {
                contadorEmpates++;
            } else {
                contadorEmpates = 0;
            }

            // Si el contador de empates llega al máximo paramos la partida y mostramos los
            // jugadores que han empatado.
            if (contadorEmpates == maxEmpates) {
                System.out.println("Ha habido un empate");
                System.out.println("Los jugadores que han empatado son: ");
                for (Jugador jugador : Jugadors.llista) {
                    System.out.println(jugador.toString());
                }
                return;
            }

        }
        // Imprimimos el ganador
        try {
            System.out.println("El ganador es: " + Jugadors.llista.get(0));
        } catch (Exception e) {
            System.out.println("No han habido supervivientes");
        }

    }

    /**
     * Esta función simula un juego en el que cada jugador ataca a otro por turnos
     * hasta que sólo queda un jugador.
     */
    public static void Manual() {

        // Se comprueba si es posible jugar, en caso contrario se sale de la función.
        if (NoSePuedeJugar()) {
            return;
        }

        // Variables para detectar caso de empate
        Jugador anterior1;
        Jugador anterior2;
        int contadorEmpates = 0;
        int maxEmpates = 10;

        // Iniciamos un bucle mientras haya mas de un jugador
        while (Jugadors.llista.size() > 1) {
            try {
                // Recorremos la lista de jugadores (los turnos van en orden)
                for (Jugador jugador : Jugadors.llista) {

                    System.out.println("\nTurno de " + jugador.getNom() + "\n");

                    // Imprimimos los jugadores disponibles y preguntamos a quien atacar
                    System.out.println("Jugadores disponibles:");
                    for (Jugador j : Jugadors.llista) {
                        System.out.println(j);
                    }
                    int jugadorAtacado = Teclat.lligInt("¿A que jugador deseas atacar?", 0,
                            Jugadors.llista.size() - 1);
                    // Guardamos las variables que necesitamos para detectar si los jugadores no han
                    // perdido vida y asi detectar empates.

                    // Guardamos las direcciones de memoria de los dos jugadores de esta ronda
                    anterior1 = jugador;
                    anterior2 = Jugadors.llista.get(jugadorAtacado);

                    // Guardamos sus vidas antes del atque
                    int vidaAntesAtaque1 = jugador.getVides();
                    int vidaAntesAtaque2 = Jugadors.llista.get(jugadorAtacado).getVides();

                    // Atacamos
                    try {
                        jugador.ataca(Jugadors.llista.get(jugadorAtacado));
                    } catch (AtacAMortException | AtacEllMateixException e) {
                        System.out.println(e.getMessage());
                    }

                    // Comprobamos si ha habido un ataque, en caso contrario reiniciamos el contador
                    if (vidaAntesAtaque1 == anterior1.getVides() && vidaAntesAtaque2 == anterior2.getVides()) {
                        contadorEmpates++;
                    } else {
                        contadorEmpates = 0;
                    }

                    // Si el contador de empates llegua al máximo, preguntamos si queremos parar la
                    // partida.
                    if (contadorEmpates == maxEmpates) {
                        boolean continuar = Teclat
                                .lligBoolean("Se ha detectado un posible caso de empate ¿Desea continuar?");
                        if (continuar) {
                            contadorEmpates = 0;
                        } else {
                            System.out.println("Ha habido un empate");
                            System.out.println("Los jugadores que han empatado son: ");
                            for (Jugador jugadorGanador : Jugadors.llista) {
                                System.out.println(jugadorGanador);
                            }
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                // Se usa para evitar el error ConcurrentModificationException (no se imprime
                // nada porque el usuario no tiene que ver el error)
            }
        }
        // Imprimimos el ganador
        if (!Jugadors.llista.isEmpty()) {
            System.out.println("El ganador es: " + Jugadors.llista.get(0));
        } else {
            System.out.println("No ha habido supervivientes");
        }
    }

    /**
     * Esta función muestra un menú para el juego de rol y permite al usuario
     * seleccionar opciones como configuración o jugar al juego.
     *
     * @param args
     */
    public static void main(String[] args) {

        int opcion = -1;
        boolean salir;

        while (opcion != 0) {
            printMenuTitle("JOC DE ROL");
            System.out.println("1.Configuració");
            System.out.println("2.Jugar");
            System.out.println("0.Eixir");

            opcion = Teclat.lligInt("Introduce la opcion: ");

            switch (opcion) {
                case 1 -> {
                    menuConfiguracio();
                }
                case 2 -> {
                    jugar();
                }
                case 0 -> {
                    salir = Teclat.lligBoolean("¿Seguro que desea salir?");
                    if (!salir) {
                        opcion = -1;
                    }
                }
                default -> {
                }
            }
        }
    }
}
