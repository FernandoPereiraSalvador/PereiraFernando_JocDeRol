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
     * Esta función muestra un menú con opciones para crear, consultar, eliminar,
     * asignar a un equipo, eliminar de un equipo, y asignar poder a los jugadores
     * de un partido
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
     * Esta función crea un nuevo objeto jugador basado en la entrada del usuario y
     * lo añade a una lista de jugadores, comprobando si hay duplicados antes de
     * añadirlo.
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
        System.out.println("El jugador " + nom + " se ha añadido correctamente");

    }

    /**
     * La función "consultar" imprime una lista de jugadores si los hay, en caso
     * contrario imprime un mensaje indicando que no hay jugadores.
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

        // Mostramos los jugadores disponibles si la lista no esta vacía.
        if (llista.isEmpty()) {
            System.out.println("No hay jugadores creados");
            return;
        } else {
            System.out.println("Lista de jugadores:");
            mostrarJugadores();
        }

        // Pedimos el jugador y lo eliminamos
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
     * Esta función asigna un jugador a un equipo basándose en los datos
     * introducidos por el usuario y comprueba si tanto el jugador como el equipo
     * existen en el sistema.
     */
    public static void assignarEquip() {

        // Si no hay jugadores o equipos mostramos un mensaje y paramos la función.
        if (llista.isEmpty() || Equips.llista.isEmpty()) {
            System.out.println("No existen jugadores o equipos: ");
            return;
        }

        // Pedimos y buscamos el jugador
        System.out.println("Jugadores disponibles: ");
        mostrarJugadores();

        Jugador jugador = buscar();

        if (jugador == null) {
            System.out.println("No se ha encontrado al jugador");
            return;
        }

        // Pedimos y buscamos el equipo
        System.out.println("Equipos disponibles: ");
        Equips.mostrarEquips();

        Equip equip = Equips.buscar();

        // Asignamos el equipo al jugador si no es null.
        if (equip == null) {
            System.out.println("No se ha encontrado al equipo");
            return;
        }

        equip.posa(jugador);
        System.out.println(
                "El jugador " + jugador.getNom() + " se ha asignado al equipo " + equip.getNom() + " correctamente");
    }

    /**
     * Esta función Java asigna un poder a un jugador introduciendo el nombre del
     * jugador y el nombre del poder, y luego añade un poder a la lista de poderes
     * del jugador.
     */
    public static void assignarPoder() {

        // Mostramos todos los poderes disponibles, en caso de que no haya ninguno
        // detenemos la función.
        if (!Poders.llista.isEmpty()) {
            System.out.println("Poderes disponibles: ");
            Poders.mostrarPoderes();
        } else {
            System.out.println("No hay poderes disponibles");
            return;
        }

        // Mostramos todos los jugadores disponibles, en caso de que no haya ninguno
        // detenemos la función.
        if (!llista.isEmpty()) {
            System.out.println("Jugadores disponibles: ");
            mostrarJugadores();
        } else {
            System.out.println("No hay jugadores disponibles");
            return;
        }

        // Solicitamos el jugador y el poder

        Jugador jugadorAsignar = buscar();
        Poder poderAsignar = Poders.buscar();

        // Si el jugador o el poder no son nulos los asignamos
        if (jugadorAsignar != null && poderAsignar != null) {
            ArrayList<Poder> podersNuevos = jugadorAsignar.getPoders();
            podersNuevos.add(poderAsignar);
            jugadorAsignar.setPoders(podersNuevos);
            System.out.println("Se ha asignado correctamente el poder " + poderAsignar.getNom() + " al jugador "
                    + jugadorAsignar.getNom());

        } else {
            System.out.println("Error: El jugador no existe o no hay ningún poder");
        }

    }

    /**
     * La función permite al usuario asignar un jugador a un equipo introduciendo el
     * nombre del jugador y el nombre del equipo, y luego comprobando si ambos
     * existen en sus respectivas listas antes de asignar el jugador al equipo.
     */
    public static void llevarEquip() {

        // Mostramos los jugadores disponibles
        System.out.println("Jugadores disponibles: ");
        mostrarJugadores();

        // Busca el jugador en la lista de jugadores
        Jugador jugador = buscar();

        if (jugador == null) {
            System.out.println("El jugador no existe");
            return;
        }

        // Busca el equipo en la lista de equipos
        Equip equip = Equips.buscar();

        // Si hemos encontrado el equipo se lo quitamos.
        if (equip != null) {
            String nomEquip = equip.getNom();
            equip.lleva(jugador);
            System.out.println(
                    "El jugador " + jugador.getNom() + " se ha eliminado del equipo " + nomEquip + " correctamente");
        } else {
            System.out.println("El equipo no existe");
        }
    }

    /**
     * La función "matar" elimina un jugador de una lista.
     *
     * @param matado El parámetro "matado" es de tipo "Jugador", lo que significa
     *               que representa un objeto de la clase "Jugador". Este método
     *               "matar" elimina el objeto "matado" de una lista llamada
     *               "llista".
     */
    public static void matar(Jugador matado) {

        llista.remove(matado);
    }

    /**
     * Esta función elimina un poder de la lista de poderes de un jugador.
     */
    public static void eliminarPoder() {
        
        // Mostramos los jugadores disponibles
        System.out.println("Jugadores disponibles: ");
        mostrarJugadores();
        
        // Obtenemos el jugador

        Jugador jugadorAsignar = buscar();

        if (jugadorAsignar == null) {
            System.out.println("No existe el jugador");
            return;
        }

        // Si el jugador no tiene ningún poder paramos la funcion
        if (jugadorAsignar.getPoders().isEmpty()) {
            System.out.println("El jugador no tiene poderes");
        } else {
            System.out.println("\nPoderes del jugador: ");
            for (Poder poder : jugadorAsignar.getPoders()) {
                System.out.println(poder);
            }

            // Si tiene alguno lo pedimos y lo eliminamos
            String poderNombre = Teclat.lligString("Introduce el nombre del poder: ");
            Poder poderEliminar = null;

            for (Poder poder : jugadorAsignar.getPoders()) {
                if (poder.getNom() == null ? poderNombre == null : poder.getNom().equals(poderNombre)) {
                    poderEliminar = poder;
                }
            }

            if (poderEliminar != null) {
                jugadorAsignar.lleva(poderEliminar);
            } else {
                System.out.println("El poder no existe");
            }

        }

    }

    /**
     * La función "mostrarJugadores" imprime la información de todos los jugadores
     * en una lista.
     */
    public static void mostrarJugadores() {

        for (Jugador jugador : llista) {
            System.out.println(jugador.toString());
        }
        System.out.println("");
    }

    /**
     * Esta función busca un jugador en la lista por su nombre y devuelve el
     * objeto jugador si es
     * encontrado, en caso contrario devuelve null.
     * 
     * @return El método devuelve un objeto "Jugador" (que es una clase
     *         personalizada) o null si el
     *         jugador con el nombre dado no se encuentra en la lista.
     */
    public static Jugador buscar() {

        // Preguntamos el nombre del jugador a buscar.
        String nom = Teclat.lligString("Introduce el nombre del jugador: ");

        // Recorremos la lista comparando los nombres y si encontramos uno igual lo
        // devolvemos.
        for (Jugador jugador : llista) {
            if (jugador.getNom().equals(nom)) {
                return jugador;
            }
        }
        return null;
    }
}
