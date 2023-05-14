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
import teclat.Teclat;
import static teclat.Pantalla.*;

/**
 *
 * @author Fernando
 */
public class Jugadors {

    static ArrayList<Jugador> llista = new ArrayList<>();

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
            System.out.println("7.Eliminar poder");
            System.out.println("0.Eixir");

            opcion = Teclat.lligInt("Introduce la opcion: ");

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
                case 7 ->
                    eliminarPoder();
                default -> {
                }
            }
        }
    }

    /**
     * Esta función crea un nuevo objeto jugador basado en la entrada del usuario y lo añade a una lista de jugadores, comprobando si hay duplicados antes de añadirlo.
     */
    public static void crear() {

        String nom = Teclat.lligString("Introduce el nombre: ");

        for (Jugador jugador : llista) {
            if (jugador.getNom().equals(nom)) {
                System.out.println("El jugador ya existe");
                return;
            }
        }

        char tipoJugador = Teclat.lligChar("Introduce el tipo de jugador", "HGA");
        int puntsAtac = Teclat.lligInt("Introduce los puntos de ataque: ", 1, 100);
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
        llista.add(nuevoJugador);

    }

    /**
     * La función "consultar" imprime una lista de jugadores si los hay, en caso contrario imprime un mensaje indicando que no hay jugadores.
     */
    public static void consultar() {

        if (llista.isEmpty()) {
            System.out.println("No hay jugadores creados");
        } else {
            System.out.println("Lista de jugadores:");
            mostrarJugadores();
        }
    }

    /**
     * Esta función elimina un jugador de una lista de jugadores por su nombre.
     */
    public static void eliminar() {

        if (llista.isEmpty()) {
            System.out.println("No hay jugadores creados");
            return;
        } else {
            System.out.println("Lista de jugadores:");
            mostrarJugadores();
        }

        System.out.println("");
        String nom = Teclat.lligString("Introduce el nombre: ");
        boolean eliminado = llista.removeIf(jugador -> jugador.getNom().equals(nom));

        if (eliminado) {
            System.out.println("Se ha eliminado el jugador " + nom + " correctamente");
        } else {
            System.out.println("No se ha encontrado al jugador " + nom);
        }

    }

    /**
     * Esta función asigna un jugador a un equipo basándose en los datos introducidos por el usuario y comprueba si tanto el jugador como el equipo existen en el sistema.
     */
    public static void assignarEquip() {

        if (llista.isEmpty() || Equips.llista.isEmpty()) {
            System.out.println("No existen jugadores o equipos: ");
        }

        Jugador jugador = null;
        Equip equip = null;

        System.out.println("Jugadores disponibles: ");
        mostrarJugadores();

        String nom = Teclat.lligString("Introduce el nombre del jugador: ");

        for (Jugador jugador1 : llista) {
            if (jugador1.getNom().equals(nom)) {
                jugador = jugador1;
                break;
            }
        }

        if (jugador == null) {
            System.out.println("No se ha encontrado al jugador");
            return;
        }

        System.out.println("Equipos disponibles: ");
        Equips.mostrarEquips();

        String nomEquip = Teclat.lligString("Introduce el nombre del equipo: ");

        for (Equip equip1 : Equips.llista) {
            if (equip1.getNom().equals(nomEquip)) {
                equip = equip1;
                break;
            }
        }

        if (equip == null) {
            System.out.println("No se ha encontrado al equipo");
            return;
        }

        equip.posa(jugador);
        System.out.println("El jugador " + nom + " se ha asignado al equipo " + nomEquip + " correctamente");
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

        String nomJugador = Teclat.lligString("Introduce el nombre del jugador: ");
        String poderNombre = Teclat.lligString("Introduce el nombre del poder: ");

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

        Jugador jugador = null;
        Equip equip = null;

        System.out.println("Jugadores disponibles: ");
        mostrarJugadores();

        String nom = Teclat.lligString("Introduce el nombre del jugador: ");

        // Busca el jugador en la lista de jugadores
        for (Jugador j : llista) {
            if (j.getNom().equals(nom)) {
                jugador = j;
                break;
            }
        }
        
        if(jugador == null){
            System.out.println("El jugador no existe");
            return;
        }

        // Busca el equipo en la lista de equipos
        for (Equip e : Equips.llista) {
            if (e.getNom().equals(jugador.getEquip().getNom())) {
                equip = e;
                break;
            }
        }

        if (equip != null) {
            String nomEquip = equip.getNom();
            equip.lleva(jugador);
            System.out.println("El jugador " + nom + " se ha eliminado del equipo " + nomEquip + " correctamente");
        } else {
            System.out.println("El equipo no existe");
        }
    }

    public static void matar(Jugador matado) {

        llista.remove(matado);
    }

    public static void eliminarPoder() {

        String nomJugador = Teclat.lligString("Introduce el nombre del jugador: ");

        Jugador jugadorAsignar = null;

        for (Jugador jugador : llista) {
            if (jugador.getNom().equals(nomJugador)) {
                jugadorAsignar = jugador;
                break;
            }
        }

        if (jugadorAsignar == null) {
            System.out.println("No existe el jugador");
            return;
        }

        if (jugadorAsignar.getPoders().isEmpty()) {
            System.out.println("El jugador no tiene poderes");
        } else {
            System.out.println("Poderes del jugador: ");
            for (Poder poder : jugadorAsignar.getPoders()) {
                System.out.println(poder);
            }

            String poderNombre = Teclat.lligString("Introduce el nombre del poder: ");
            Poder poderEliminar = null;

            for (Poder poder : jugadorAsignar.getPoders()) {
                if (poder.getNom() == null ? poderNombre == null : poder.getNom().equals(poderNombre)) {
                    poderEliminar = poder;
                }
            }

            if (poderEliminar != null && jugadorAsignar != null) {
                jugadorAsignar.lleva(poderEliminar);
            } else {
                System.out.println("El jugador o el poder no existen");
            }

        }

    }

    public static void mostrarJugadores() {

        for (Jugador jugador : llista) {
            System.out.println(jugador.toString());
        }
        System.out.println("");
    }
}
