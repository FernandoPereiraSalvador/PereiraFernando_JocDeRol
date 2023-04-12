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
    ArrayList<Equip> llista = new ArrayList<Equip>();

    // Getter
    public String getNom() {
        return nom;
    }

    // Setter
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Constructor
    public Equip(String nom) {
        this.nom = nom;
    }

    // Metodos
    public void posa(Jugador jugador) {

        if (!jugadors.contains(jugador)) {
            jugadors.add(jugador);
            jugador.setEquip(this);
        }

    }

    public void llevar(Jugador jugador) {
        if (jugadors.contains(jugador)) {
            jugadors.remove(jugador);
            jugador.setEquip(null);

        }

    }

    @Override
    public String toString() {
        return "Equip{" + "nom=" + nom + ", jugadors=" + jugadors + '}';
    }

    //Menu
    public static void menu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("EQUIPS");
            System.out.println("1.Crear");
            System.out.println("2.Consultar");
            System.out.println("3.Eliminar");
            System.out.println("0.Eixir");

            opcion = teclat.Teclat.lligInt("Introduce la opcion: ");
        }
    }

}
