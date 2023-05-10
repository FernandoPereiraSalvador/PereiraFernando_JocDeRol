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
     * Esta función automatiza el proceso de ataque entre jugadores seleccionados al azar hasta que sólo quede un superviviente.
     */
    public static void Automatizat() {

        Jugador randomJugador1 = null;
        Jugador randomJugador2 = null;
        
        Jugador anterior1 = null;
        Jugador anterior2 = null;
        int contadorEmpates = 0;

        while (Jugador.llista.size() > 1) {

            // crea un objeto Random
            Random rand = new Random();

            // genera un índice al azar dentro del rango de los índices del ArrayList
            int randomIndexJugador1 = rand.nextInt(Jugador.llista.size());

            // inicializa el segundo índice como el índice del primer jugador
            int randomIndexJugador2 = randomIndexJugador1;

            // mientras los índices sean iguales, genera nuevos índices
            while (randomIndexJugador2 == randomIndexJugador1) {
                randomIndexJugador2 = rand.nextInt(Jugador.llista.size());
            }

            // obtiene los jugadores correspondientes utilizando los índices generados
            randomJugador1 = Jugador.llista.get(randomIndexJugador1);
            randomJugador2 = Jugador.llista.get(randomIndexJugador2);
            
            anterior1 = randomJugador1;
            anterior2 = randomJugador2;
            
            try {
                randomJugador1.ataca(randomJugador2);
            } catch (AtacAMortException | AtacEllMateixException e) {
                System.out.println(e.getMessage());
            }
            
            if(anterior1.getVides()==randomJugador1.getVides() && anterior2.getVides()==randomJugador2.getVides()){
                contadorEmpates++;
            }
            
            if(contadorEmpates==10){
                System.out.println("Ha habido un empate");
                System.out.println("Los jugadores que han ganado son: ");
                for (Jugador jugador : Jugador.llista) {
                    System.out.println(jugador.toString());
                }
                return;
            }

        }
        try {
            System.out.println("El ganador es: " + Jugador.llista.get(0));
        } catch (Exception e) {
            System.out.println("No han habido supervivientes");
        }

    }

    /**
     * Esta función simula un juego en el que cada jugador ataca a otro por turnos hasta que sólo queda un jugador.
     */
    public static void Manual() {
        while (Jugador.llista.size() > 1) {
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

        if (!Jugador.llista.isEmpty()) {
            System.out.println("El ganador es: " + Jugador.llista.get(0));
        }
    }

    /**
     * Esta función imprime un título de menú con una línea de signos iguales por encima y por debajo, centrado dentro de una línea de 40 caracteres.
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
