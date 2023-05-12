/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inici;

import altres.Equip;
import java.util.ArrayList;
import static teclat.Pantalla.*;
/**
 *
 * @author Fernando
 */
public class Equips {

    public static ArrayList<Equip> llista = new ArrayList<Equip>();

    /**
     * Esta función muestra un menú con opciones para crear, consultar o eliminar equipos y realiza un bucle hasta que el usuario decida salir.
     */
    public static void menu() {
        int opcion = -1;

        while (opcion != 0) {

            printMenuTitle("EQUIPS");
            System.out.println("1.Crear");
            System.out.println("2.Consultar");
            System.out.println("3.Eliminar");
            System.out.println("0.Eixir");

            opcion = teclat.Teclat.lligInt("Introduce la opcion: ");

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
     * La función crea un nuevo objeto de equipo con un nombre dado y lo añade a una lista si aún no existe.
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
     * La función "consultar" imprime una lista de los objetos "Equip" creados si la lista no está vacía.
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
     * La función "eliminar" elimina un objeto de una lista si existe, basándose en la entrada del usuario para el nombre del objeto.
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
