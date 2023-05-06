/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inici;

/**
 * La clase "Guerrer" amplía la clase "Jugador".
 * 
 * @author Fernando
 */
public class Guerrer extends Huma {

    /**
     * El constructor de la clase Guerrer
     * 
     * @param nom          El nombre del jugador
     * @param puntsAtac    Los puntos de ataque del jugador
     * @param puntsDefensa Los puntos de defensa del jugador
     * @param vides        Los puntos de vida del jugador
     */
    public Guerrer(String nom, int puntsAtac, int puntsDefensa, int vides) {
        super(nom, puntsAtac, puntsDefensa, vides);
    }

}
