/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package inici;

import personatges.Jugador;
import altres.*;
import java.util.Random;
import static teclat.Pantalla.*;

/**
 *
 * @author Fernando
 */
public class PereiraFernando_JocDeRol {

    /**
     * Esta función muestra un menú para configurar jugadores, equipos y potencias, y permite al usuario seleccionar una opción para realizar la acción correspondiente.
     */
    public static void menuConfiguracio() {
        int opcionMenu = -1;

        while (opcionMenu != 0) {
            printMenuTitle("CONFIGURACIÓN");
            System.out.println("1.Jugadors");
            System.out.println("2.Equips");
            System.out.println("3.Poders");
            System.out.println("0.Eixir");

            opcionMenu = teclat.Teclat.lligInt("Introduce la opcion: ");

            switch (opcionMenu) {
                case 1 ->
                    Jugadors.menu();
                case 2 ->
                    Equips.menu();
                case 3 ->
                    Poders.menu();
                default ->
                    System.out.println("Has introducido una opción erronea");
            }

        }
    }

    /**
     * La función "jugar" muestra un menú con dos opciones (automatizada y manual) y permite al usuario elegir entre ellas.
     */
    public static void jugar() {

        int opcion = 0;

        printMenuTitle("JUGAR");
        System.out.println("1.Automatizat");
        System.out.println("2.Manual");
        System.out.println("0.Eixir");

        opcion = teclat.Teclat.lligInt("Introduce la opcion: ");

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
     * Esta función automatiza el proceso de ataque entre jugadores seleccionados al azar hasta que sólo quede un superviviente.
     */
    public static void Automatizat() {

        if (NoSePuedeJugar()) {
            return;
        }

        Jugador randomJugador1 = null;
        Jugador randomJugador2 = null;

        // Variables para detectar caso de empate
        int contadorEmpates = 0;
        int maxEmpates = 10;

        while (Jugadors.llista.size() > 1) {

            // crea un objeto Random
            Random rand = new Random();

            // genera un índice al azar dentro del rango de los índices del ArrayList
            int randomIndexJugador1 = rand.nextInt(Jugadors.llista.size());

            // inicializa el segundo índice como el índice del primer jugador
            int randomIndexJugador2 = randomIndexJugador1;

            // mientras los índices sean iguales, genera nuevos índices
            while (randomIndexJugador2 == randomIndexJugador1) {
                randomIndexJugador2 = rand.nextInt(Jugadors.llista.size());
            }

            // obtiene los jugadores correspondientes utilizando los índices generados
            randomJugador1 = Jugadors.llista.get(randomIndexJugador1);
            randomJugador2 = Jugadors.llista.get(randomIndexJugador2);

            int vidaAntesAtaque1 = randomJugador1.getVides();
            int vidaAntesAtaque2 = randomJugador2.getVides();

            try {
                randomJugador1.ataca(randomJugador2);
            } catch (AtacAMortException | AtacEllMateixException e) {
                System.out.println(e.getMessage());
            }

            if (vidaAntesAtaque1 == randomJugador1.getVides() && vidaAntesAtaque2 == randomJugador2.getVides()) {
                contadorEmpates++;
            } else {
                contadorEmpates = 0;
            }

            if (contadorEmpates == maxEmpates) {
                System.out.println("Ha habido un empate");
                System.out.println("Los jugadores que han ganado son: ");
                for (Jugador jugador : Jugadors.llista) {
                    System.out.println(jugador.toString());
                }
                return;
            }

        }
        try {
            System.out.println("El ganador es: " + Jugadors.llista.get(0));
        } catch (Exception e) {
            System.out.println("No han habido supervivientes");
        }

    }

    /**
     * Esta función simula un juego en el que cada jugador ataca a otro por turnos hasta que sólo queda un jugador.
     */
    public static void Manual() {

        if (NoSePuedeJugar()) {
            return;
        }

        // Variables para detectar caso de empate
        Jugador anterior1 = null;
        Jugador anterior2 = null;
        int contadorEmpates = 0;
        int maxEmpates = 10;

        while (Jugadors.llista.size() > 1) {
            try {
                for (Jugador jugador : Jugadors.llista) {
                    System.out.println("\nTurno de " + jugador.getNom() + "\n");
                    System.out.println("Jugadores disponibles:");
                    for (Jugador j : Jugadors.llista) {
                        System.out.println(j);
                    }
                    int jugadorAtacado = teclat.Teclat.lligInt("¿A que jugador deseas atacar?", 0,
                            Jugadors.llista.size() - 1);
                    anterior1 = jugador;
                    anterior2 = Jugadors.llista.get(jugadorAtacado);

                    int vidaAntesAtaque1 = jugador.getVides();
                    int vidaAntesAtaque2 = Jugadors.llista.get(jugadorAtacado).getVides();
                    try {
                        jugador.ataca(Jugadors.llista.get(jugadorAtacado));
                    } catch (AtacAMortException | AtacEllMateixException e) {
                        System.out.println(e.getMessage());
                    }

                    if (vidaAntesAtaque1 == anterior1.getVides() && vidaAntesAtaque2 == anterior2.getVides()) {
                        contadorEmpates++;
                    } else {
                        contadorEmpates = 0;
                    }

                    if (contadorEmpates == maxEmpates) {
                        boolean continuar = teclat.Teclat.lligBoolean("Se ha detectado un posible caso de empate ¿Desea continuar?");
                        if (continuar) {
                            contadorEmpates = 0;
                        } else {
                            System.out.println("Ha habido un empate");
                            System.out.println("Los jugadores que han ganado son: ");
                            for (Jugador jugadorGanador : Jugadors.llista) {
                                System.out.println(jugadorGanador);
                            }
                            return;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }

        if (!Jugadors.llista.isEmpty()) {
            System.out.println("El ganador es: " + Jugadors.llista.get(0));
        } else {
            System.out.println("No ha habido supervivientes");
        }
    }

    /**
     * Esta función muestra un menú para el juego de rol y permite al usuario seleccionar opciones como configuración o jugar al juego.
     *
     * @param args
     */
    public static void main(String[] args) {

        int opcion = -1;
        boolean salir = false;

        while (opcion != 0) {
            printMenuTitle("JOC DE ROL");
            System.out.println("1.Configuració");
            System.out.println("2.Jugar");
            System.out.println("0.Eixir");

            opcion = teclat.Teclat.lligInt("Introduce la opcion: ");

            switch (opcion) {
                case 1 -> {
                    menuConfiguracio();
                    opcion = -1;
                }
                case 2 -> {
                    jugar();
                    opcion = -1;
                }
                case 0 -> {
                    salir = teclat.Teclat.lligBoolean("¿Seguro que desea salir?");
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
