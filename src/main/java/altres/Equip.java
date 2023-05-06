/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package altres;

import java.util.ArrayList;
import inici.Jugador;

/**
 *
 * @author Fernando
 */
public class Equip {

    private String nom;

    ArrayList<Jugador> jugadors = new ArrayList<Jugador>();
    public static ArrayList<Equip> llista = new ArrayList<Equip>();

    /**
     * El getter de la clase equip
     * 
     * @return Devuelve el nombre del equipo
     */
    public String getNom() {
        return nom;
    }

    /**
     * El setter de la clase equip
     * 
     * @param nom El nombre del equipo
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Constructor de la clase equip
     * 
     * @param nom El nombre del equipo
     */
    public Equip(String nom) {
        this.nom = nom;
    }

    /**
     * La función "posa" añade un jugador a un equipo si no está ya en él.
     * 
     * @param jugador Jugador es un objeto de la clase "Jugador", que representa a
     *                un jugador.
     */
    public void posa(Jugador jugador) {

        if (!jugadors.contains(jugador)) {
            jugadors.add(jugador);
            jugador.setEquip(this);
        }

    }

    /**
     * La función elimina un jugador de una lista y establece su equipo en null.
     * 
     * @param jugador El parámetro "jugador" es un objeto de la clase "Jugador", que
     *                representa a un
     *                jugador.
     */
    public void llevar(Jugador jugador) {
        if (jugadors.contains(jugador)) {
            jugadors.remove(jugador);
            jugador.setEquip(null);

        }

    }

    /**
     * Esta función devuelve una representación de cadena de un objeto que incluye
     * el nombre de un equipo y sus
     * jugadores.
     * 
     * @return El método devuelve un String que representa el nombre del equipo y
     *         una lista de sus
     *         jugadores, donde cada jugador está representado por su propia cadena
     *         obtenida de su respectivo método
     *         método `toString()`.
     */
    @Override
    public String toString() {
        String texto = "Equip: " + nom + " Jugadores: ";
        if (this.jugadors.isEmpty()) {
            return texto + "El equipo no tiene jugadores";
        } else {
            for (Jugador jugador : this.jugadors) {
                texto += jugador.toString();
            }
        }
        return texto;
    }

    /**
     * Esta función muestra un menú con opciones para crear, consultar o eliminar
     * equipos y realiza un bucle hasta que el
     * usuario decida salir.
     */
    public static void menu() {
        int opcion = -1;

        while (opcion != 0) {
            inici.PereiraFernando_JocDeRol.printMenuTitle("EQUIPS");
            System.out.println("1.Crear");
            System.out.println("2.Consultar");
            System.out.println("3.Eliminar");
            System.out.println("0.Eixir");

            opcion = teclat.Teclat.lligInt("Introduce la opcion: ");

            switch (opcion) {
                case 1 -> crear();
                case 2 -> consultar();
                case 3 -> eliminar();
                default -> System.out.println("No se ha encontrado la opcion");
            }
        }
    }

    /**
     * La función crea un nuevo objeto de equipo con un nombre dado y lo añade a una
     * lista si aún no existe.
     */
    public static void crear() {
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");

        Equip nuevoEquip = new Equip(nom);

        if (!llista.contains(nuevoEquip)) {
            llista.add(nuevoEquip);
        } else {
            System.out.println("El equipo ya existe");
        }
    }

    /**
     * La función "consultar" imprime una lista de los objetos "Equip" creados si la
     * lista no está vacía.
     */
    public static void consultar() {

        if (llista.isEmpty()) {
            System.out.println("No hay equipos creados");
        } else {
            System.out.println("Lista de equipos: ");
            for (Equip equip : llista) {
                System.out.println(equip.toString());
            }
        }

    }

    /**
     * La función "eliminar" elimina un objeto de una lista si existe, basándose en
     * la entrada del usuario para el
     * nombre del objeto.
     */
    public static void eliminar() {
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");

        Equip nuevoEquip = new Equip(nom);

        if (!llista.contains(nuevoEquip)) {
            llista.remove(nuevoEquip);
        } else {
            System.out.println("El equipo no existe");
        }
    }

}
