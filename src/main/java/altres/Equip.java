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
        String texto = "Equip" + nom;
        
        for(Jugador jugador : this.jugadors){
            texto += jugador.toString();
        }
        
        return texto;
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
            
            switch (opcion) {
                case 1 -> crear();
                case 2 -> consultar();
                case 3 -> eliminar();
                default -> System.out.println("No se ha encontrado la opcion");
            }
        }
    }

    public static void crear(){
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        
        Equip nuevoEquip = new Equip(nom);

        if (!llista.contains(nuevoEquip)) {
            llista.add(nuevoEquip);
        } else {
            System.out.println("El equipo ya existe");
        }
    }

    public static void consultar() {
        for (Equip equip : llista) {
            System.out.println(equip);
        }
    }
    
        public static void eliminar(){
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        
        Equip nuevoEquip = new Equip(nom);

        if (!llista.contains(nuevoEquip)) {
            llista.remove(nuevoEquip);
        } else {
            System.out.println("El equipo no existe");
        }
    }

}
