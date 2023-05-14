/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inici;

import altres.Poder;
import java.util.ArrayList;
import static teclat.Pantalla.*;
import teclat.Teclat;

/**
 *
 * @author Fernando
 */
public class Poders {

    public static ArrayList<Poder> llista = new ArrayList<Poder>();

    /**
     * Esta función muestra un menú con opciones para crear, consultar o eliminar
     * potencias y realiza un bucle hasta que el usuario decida salir.
     */
    public static void menu() {
        int opcion = -1;

        while (opcion != 0) {
            printMenuTitle("PODERS");
            System.out.println("1.Crear");
            System.out.println("2.Consultar");
            System.out.println("3.Eliminar");
            System.out.println("0.Eixir");

            opcion = Teclat.lligInt("Introduce la opcion: ");

            switch (opcion) {
                case 1 ->
                    crear();
                case 2 ->
                    consultar();
                case 3 ->
                    eliminar();
                case 0 -> {
                }
                default ->
                    System.out.println("Has introducido una opción erronea");
            }
        }
    }

    /**
     * Esta función Java crea un nuevo objeto "Poder" con el nombre, bono de ataque
     * y bono de defensa introducidos por el usuario. , y lo añade a una lista si no
     * existe ya.
     */
    public static void crear() {
        String nom = Teclat.lligString("Introduce el nombre: ");

        for (Poder poder : llista) {
            if (poder.getNom().equals(nom)) {
                System.out.println("El poder ya existe");
                return;
            }
        }

        int bonusAtac = Teclat.lligInt("Introduce el bono de ataque: ");
        int bonusDefensa = Teclat.lligInt("Introduce el bono de defensa: ");

        Poder nuevoPoder = new Poder(nom, bonusAtac, bonusDefensa);

        llista.add(nuevoPoder);
        System.out.println("El poder " + nom + " se ha creado correctamente");

    }

    /**
     * La función "consultar" comprueba si una lista de potencias está vacía e
     * imprime un mensaje en consecuencia, o bien imprime cada potencia de la lista.
     */
    public static void consultar() {
        if (llista.isEmpty()) {
            System.out.println("No hay poderes creados");
        } else {
            System.out.println("Lista de poderes: ");
            mostrarPoderes();
        }

    }

    /**
     * Esta función Java elimina una potencia de una lista si existe, basándose en
     * los datos introducidos por el usuario para la potencia.
     */
    public static void eliminar() {

        System.out.println("Poderes disponibles: ");
        mostrarPoderes();

        // Buscar el poder en la lista
        Poder poderAEliminar = buscar();

        // Eliminar el poder si se encontró
        if (poderAEliminar != null) {
            System.out.println("Poder " + poderAEliminar.getNom() + " eliminado correctamente");
            llista.remove(poderAEliminar);
        } else {
            System.out.println("No se encontró el poder ");
        }
    }

    /**
     * La función "mostrarPoderes" imprime la representación en cadena de cada
     * objeto de la lista "llista".
     */
    public static void mostrarPoderes() {
        for (Poder poder : llista) {
            System.out.println(poder.toString());
        }
        System.out.println("");
    }

    /**
     * La función busca un poder por su nombre en una lista y la devuelve si la
     * encuentra, en caso contrario devuelve null.
     * 
     * @return El método devuelve un objeto de tipo "Poder" (que es una clase
     *         personalizada) o null si el
     *         potencia con el nombre dado no se encuentra en la lista.
     */
    public static Poder buscar() {

        String nom = Teclat.lligString("Introduce el nombre del poder: ");

        for (Poder poder : llista) {
            if (poder.getNom().equals(nom)) {
                return poder;
            }
        }
        return null;
    }
}
