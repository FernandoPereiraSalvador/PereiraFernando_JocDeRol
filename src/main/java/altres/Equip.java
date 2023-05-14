/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package altres;

import java.util.ArrayList;
import personatges.Jugador;

/**
 *
 * @author Fernando
 */
public class Equip {

    private String nom;

    ArrayList<Jugador> jugadors = new ArrayList<>();

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
    public void lleva(Jugador jugador) {
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

}
