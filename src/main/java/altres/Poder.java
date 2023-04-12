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
    ArrayList<Poder> llista = new ArrayList<Poder>();

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
        }
    }

    
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
    

    
}
