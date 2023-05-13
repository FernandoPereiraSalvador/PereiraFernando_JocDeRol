/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inici;

import altres.Equip;
import altres.Poder;
import java.util.ArrayList;
import personatges.Alien;
import personatges.Guerrer;
import personatges.Huma;
import personatges.Jugador;
import static teclat.Pantalla.*;

/**
 *
 * @author Fernando
 */
public class Jugadors {

    static ArrayList<Jugador> llista = new ArrayList<Jugador>();

    /**
     * Esta función muestra un menú con opciones para crear, consultar, eliminar, asignar a un equipo, eliminar de un equipo, y asignar poder a los jugadores de un partido
     */
    public static void menu() {
        int opcion = -1;

        while (opcion != 0) {
            printMenuTitle("JUGADORS");
            System.out.println("1.Crear");
            System.out.println("2.Consultar");
            System.out.println("3.Eliminar");
            System.out.println("4.Assignar a equip");
            System.out.println("5.Llevar d'equip");
            System.out.println("6.Assignar poder");
            System.out.println("0.Eixir");

            opcion = teclat.Teclat.lligInt("Introduce la opcion: ");

            switch (opcion) {
                case 1 ->
                    crear();
                case 2 ->
                    consultar();
                case 3 ->
                    eliminar();
                case 4 ->
                    assignarEquip();
                case 5 ->
                    llevarEquip();
                case 6 ->
                    assignarPoder();
                default -> {
                }
            }
        }
    }

    /**
     * Esta función crea un nuevo objeto jugador basado en la entrada del usuario y lo añade a una lista de jugadores, comprobando si hay duplicados antes de añadirlo.
     */
    public static void crear() {
        char tipoJugador = teclat.Teclat.lligChar("Introduce el tipo de jugador", "HGA");
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        int puntsAtac = teclat.Teclat.lligInt("Introduce los puntos de ataque: ", 1, 100);
        int puntsDefensa = Math.abs(puntsAtac - 100);

        Jugador nuevoJugador = null;

        switch (tipoJugador) {
            case 'H' ->
                nuevoJugador = new Huma(nom, puntsAtac, puntsDefensa, personatges.Jugador.getCantidadVidas());
            case 'G' ->
                nuevoJugador = new Guerrer(nom, puntsAtac, puntsDefensa, personatges.Jugador.getCantidadVidas());
            case 'A' ->
                nuevoJugador = new Alien(nom, puntsAtac, puntsDefensa, personatges.Jugador.getCantidadVidas());
            default -> {
            }
        }
        boolean encontrado = false;
        for (Jugador jugador : llista) {
            if (jugador.getNom().equals(nuevoJugador.getNom())) {
                encontrado = true;
            }
        }

        if (encontrado) {
            System.out.println("El jugador ya existe");
        } else {
            llista.add(nuevoJugador);
            System.out.println("El jugador se ha creado correctamente");
        }
    }

    /**
     * La función "consultar" imprime una lista de jugadores si los hay, en caso contrario imprime un mensaje indicando que no hay jugadores.
     */
    public static void consultar() {

        if (llista.isEmpty()) {
            System.out.println("No hay jugadores creados");
        } else {
            System.out.println("Lista de jugadores:");
            for (Jugador jugador : llista) {
                System.out.println(jugador);
            }
        }
    }

    /**
     * Esta función elimina un jugador de una lista de jugadores por su nombre.
     */
    public static void eliminar() {

        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        boolean Noeliminado = true;

        for (Jugador jugador : llista) {
            if (jugador.getNom().equals(nom)) {
                llista.remove(jugador);
                System.out.println("Se ha eliminado el jugador correctamente");
                Noeliminado = false;
                break;
            }
        }

        if (Noeliminado) {
            System.out.println("No se ha encontrado al jugador");
        }

    }

    /**
     * Esta función asigna un jugador a un equipo basándose en los datos introducidos por el usuario y comprueba si tanto el jugador como el equipo existen en el sistema.
     */
    public static void assignarEquip() {
        String nom = teclat.Teclat.lligString("Introduce el nombre del jugador: ");
        String nomEquip = teclat.Teclat.lligString("Introduce el nombre del equipo: ");

        Jugador jugador = null;
        Equip equip = null;

        for (int i = 0; i < llista.size(); i++) {
            jugador = llista.get(i);
            if (jugador.getNom().equals(nom)) {
                break;
            } else {
                jugador = null;
            }
        }

        for (int i = 0; i < Equips.llista.size(); i++) {
            equip = Equips.llista.get(i);
            if (equip.getNom().equals(nomEquip)) {
                break;
            } else {
                equip = null;
            }
        }

        if (equip != null && jugador != null) {
            equip.posa(jugador);
            System.out.println("El jugador " + nom + " se ha asignado al equipo " + nomEquip + " correctamente");
        } else {
            System.out.println("El jugador o el equipo no existen");
        }
    }

    /**
     * Esta función Java asigna un poder a un jugador introduciendo el nombre del jugador y el nombre del poder, y luego añade un poder a la lista de poderes del jugador.
     */
    public static void assignarPoder() {

        if (!Poders.llista.isEmpty()) {
            System.out.println("Poderes disponibles: ");
            for (Poder poder : Poders.llista) {
                System.out.println(poder.toString());
            }
        } else {
            System.out.println("No hay poderes disponibles");
            return;
        }

        if (!llista.isEmpty()) {
            System.out.println("Jugadores disponibles: ");
            for (Jugador jugador : llista) {
                System.out.println(jugador.toString());
            }
        } else {
            System.out.println("No hay jugadores disponibles");
            return;
        }

        String nomJugador = teclat.Teclat.lligString("Introduce el nombre del jugador: ");
        String poderNombre = teclat.Teclat.lligString("Introduce el nombre del poder: ");

        Jugador jugadorAsignar = null;
        Poder poderAsignar = null;

        for (Jugador jugador : llista) {
            if (jugador.getNom().equals(nomJugador)) {
                jugadorAsignar = jugador;
                break;
            }
        }

        for (Poder poder : Poders.llista) {
            if (poder.getNom().equals(poderNombre)) {
                poderAsignar = poder;
            }
        }

        if (jugadorAsignar != null && poderAsignar != null) {
            ArrayList<Poder> podersNuevos = jugadorAsignar.getPoders();
            podersNuevos.add(poderAsignar);
            jugadorAsignar.setPoders(podersNuevos);
            System.out.println("Se ha asignado correctamente el poder " + poderAsignar.getNom() + " al jugador " + jugadorAsignar.getNom());

        } else {
            System.out.println("Error: El jugador no existe o no hay ningún poder");
        }

    }

    /**
     * La función permite al usuario asignar un jugador a un equipo introduciendo el nombre del jugador y el nombre del equipo, y luego comprobando si ambos existen en sus respectivas listas antes de asignar el jugador al equipo.
     */
    public static void llevarEquip() {
        String nom = teclat.Teclat.lligString("Introduce el nombre del jugador: ");
        String nomEquip = teclat.Teclat.lligString("Introduce el nombre del equipo: ");

        Jugador jugador = null;
        Equip equip = null;

        for (int i = 0; i < llista.size(); i++) {
            jugador = llista.get(i);
            if (jugador.getNom().equals(nom)) {
                break;
            } else {
                jugador = null;
            }
        }

        for (int i = 0; i < Equips.llista.size(); i++) {
            equip = Equips.llista.get(i);
            if (equip.getNom().equals(nomEquip)) {
                break;
            } else {
                equip = null;
            }
        }

        if (equip != null && jugador != null) {
            equip.lleva(jugador);
            System.out.println(" El jugador " + nom + " se ha eliminado del equipo " + nomEquip + " correctamente");
        } else {
            System.out.println("El jugador o el equipo no existen");
        }
    }
    
    public static void matar(Jugador matado){
        llista.remove(matado);
    }
}
