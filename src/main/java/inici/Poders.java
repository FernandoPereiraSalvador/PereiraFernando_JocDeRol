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
     * Esta función muestra un menú con opciones para crear, consultar o eliminar potencias y realiza un bucle hasta que el usuario decida salir.
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
                default ->
                    System.out.println("No se ha encontrado la opcion");
            }
        }
    }

    /**
     * Esta función Java crea un nuevo objeto "Poder" con el nombre, bono de ataque y bono de defensa introducidos por el usuario. , y lo añade a una lista si no existe ya.
     */
    public static void crear() {
        String nom = Teclat.lligString("Introduce el nombre: ");
        int bonusAtac = Teclat.lligInt("Introduce el bono de ataque: ");
        int bonusDefensa = Teclat.lligInt("Introduce el bono de defensa: ");

        Poder nuevoPoder = new Poder(nom, bonusAtac, bonusDefensa);

        if (!llista.contains(nuevoPoder)) {
            llista.add(nuevoPoder);
        } else {
            System.out.println("El equipo ya existe");
        }
    }

    /**
     * La función "consultar" comprueba si una lista de potencias está vacía e imprime un mensaje en consecuencia, o bien imprime cada potencia de la lista.
     */
    public static void consultar() {
        if (llista.isEmpty()) {
            System.out.println("No hay poderes creados");
        } else {
            System.out.println("Lista de poderes: ");
            for (Poder poder : llista) {
                System.out.println(poder.toString());
            }
        }

    }

    /**
     * Esta función Java elimina una potencia de una lista si existe, basándose en los datos introducidos por el usuario para la potencia.
     */
    public static void eliminar() {
        String nom = Teclat.lligString("Introduce el nombre: ");
        int bonusAtac = Teclat.lligInt("Introduce el bono de ataque: ");
        int bonusDefensa = Teclat.lligInt("Introduce el bono de defensa: ");

        // Buscar el poder en la lista
        Poder poderAEliminar = null;
        for (Poder poder : llista) {
            if (poder.getNom().equals(nom) && poder.getBonusAtac() == bonusAtac && poder.getBonusDefensa() == bonusDefensa) {
                poderAEliminar = poder;
                break;
            }
        }

        // Eliminar el poder si se encontró
        if (poderAEliminar != null) {
            llista.remove(poderAEliminar);
            System.out.println("Poder " + nom + " eliminado correctamente");
        } else {
            System.out.println("No se encontró el poder " + nom);
        }
    }
}
