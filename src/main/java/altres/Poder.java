/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package altres;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Poder {

    private String nom;
    private int bonusAtac;
    private int bonusDefensa;
    public static ArrayList<Poder> llista = new ArrayList<Poder>();

    // Getters
    public String getNom() {
        return nom;
    }

    public int getBonusAtac() {
        return bonusAtac;
    }

    public int getBonusDefensa() {
        return bonusDefensa;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setBonusAtac(int bonusAtac) {
        this.bonusAtac = bonusAtac;
    }

    public void setBonusDefensa(int bonusDefensa) {
        this.bonusDefensa = bonusDefensa;
    }

    // Constructor
    public Poder(String nom, int bonusAtac, int bonusDefensa) {
        this.nom = nom;
        this.bonusAtac = bonusAtac;
        this.bonusDefensa = bonusDefensa;
    }

    //toString
    @Override
    public String toString() {
        return "java{" + "nom=" + nom + ", bonusAtac=" + bonusAtac + ", bonusDefensa=" + bonusDefensa + '}';
    }

    //Menu
    public static void menu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("PODERS");
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

    public static void crear() {
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        int bonusAtac = teclat.Teclat.lligInt("Introduce el bono de ataque: ");
        int bonusDefensa = teclat.Teclat.lligInt("Introduce el bono de defensa: ");

        Poder nuevoPoder = new Poder(nom, bonusAtac, bonusDefensa);

        if (!llista.contains(nuevoPoder)) {
            llista.add(nuevoPoder);
        } else {
            System.out.println("El equipo ya existe");
        }
    }

    public static void consultar() {
        for (Poder poder : llista) {
            System.out.println(poder);
        }
    }

    public static void eliminar() {
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        int bonusAtac = teclat.Teclat.lligInt("Introduce el bono de ataque: ");
        int bonusDefensa = teclat.Teclat.lligInt("Introduce el bono de defensa: ");

        Poder nuevoPoder = new Poder(nom, bonusAtac, bonusDefensa);

        if (!llista.contains(nuevoPoder)) {
            llista.remove(nuevoPoder);
        } else {
            System.out.println("El poder no existe");
        }
    }

}
