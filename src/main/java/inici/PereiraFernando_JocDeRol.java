/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package inici;

import altres.Equip;
import altres.Poder;
import altres.*;
import java.util.Random;

/**
 *
 * @author Fernando
 */
public class PereiraFernando_JocDeRol {

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

            opcionMenu = teclat.Teclat.lligInt("Introduce la opcion: ");

            switch (opcionMenu) {
                case 1 ->
                    Jugador.menu();
                case 2 ->
                    Equip.menu();
                case 3 ->
                    Poder.menu();
                default ->
                    System.out.println("Has introducido una opción erronea");
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
     * Esta función automatiza el proceso de ataque entre jugadores seleccionados al
     * azar hasta que sólo quede un superviviente.
     */
    public static void Automatizat() {
        while (!Jugador.llista.isEmpty()) {
            // crea un objeto Random
            Random randJugador1 = new Random();
            Random randJugador2 = new Random();

            // genera un índice al azar dentro del rango de los índices del ArrayList
            int randomIndexJugador1 = randJugador1.nextInt(Jugador.llista.size());
            int randomIndexJugador2 = randJugador2.nextInt(Jugador.llista.size());

            // obtiene el elemento correspondiente del ArrayList utilizando el índice
            // generado al azar
            Jugador randomJugador1 = Jugador.llista.get(randomIndexJugador1);
            Jugador randomJugador2 = Jugador.llista.get(randomIndexJugador2);
            try {
                randomJugador1.ataca(randomJugador2);
            } catch (AtacAMortException | AtacEllMateixException e) {
                System.out.println(e.getMessage());
            }

        }
        try {
            System.out.println("El ganador es: " + Jugador.llista.get(0));
        } catch (Exception e) {
            System.out.println("No han habido supervivientes");
        }

    }

    /**
     * Esta función simula un juego en el que cada jugador ataca a otro por turnos
     * hasta que sólo queda un jugador.
     */
    public static void Manual() {
        while (Jugador.llista.size()>1) {
            try {
                for (Jugador jugador : Jugador.llista) {
                    System.out.println("\nTurno de " + jugador.getNom() + "\n");
                    System.out.println("Jugadores disponibles:");
                    for (Jugador j : Jugador.llista) {
                        System.out.println(j);
                    }
                    int jugadorAtacado = teclat.Teclat.lligInt("¿A que jugador deseas atacar?", 0,
                            Jugador.llista.size() - 1);

                    try {
                        jugador.ataca(Jugador.llista.get(jugadorAtacado));
                    } catch (AtacAMortException | AtacEllMateixException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("No ha habido supervivientes");
            }
        }
        
        if(!Jugador.llista.isEmpty()){
            System.out.println("El ganador es: " + Jugador.llista.get(0));
        }
    }

    /**
     * Esta función imprime un título de menú con una línea de signos iguales por
     * encima y por debajo, centrado dentro de una línea de 40 caracteres.
     *
     * @param title El título del menú que se imprimirá.
     */
    public static void printMenuTitle(String title) {
        int titleLength = title.length();
        int lineLength = 40;
        int spaces = (lineLength - titleLength) / 2;
        System.out.println("\n" + "=".repeat(lineLength));
        System.out.println(" ".repeat(spaces) + title);
        System.out.println("=".repeat(lineLength) + "\n");
    }

    /**
     * Esta función muestra un menú para un juego de rol y permite al usuario
     * seleccionar opciones como configuración o jugar al juego.
     * 
     * @param args
     */
    public static void main(String[] args) {

        int opcion = -1;

        while (opcion != 0) {
            printMenuTitle("JOC DE ROL");
            System.out.println("1.Configuració");
            System.out.println("2.Jugar");
            System.out.println("0.Eixir");

            opcion = teclat.Teclat.lligInt("Introduce la opcion: ");

            if (opcion == 1) {
                menuConfiguracio();
                opcion = -1;
            } else if (opcion == 2) {
                jugar();
                opcion = -1;
            }
        }
    }
}
