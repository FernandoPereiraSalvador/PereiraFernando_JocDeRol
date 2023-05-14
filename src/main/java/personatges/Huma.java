/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personatges;

/**
 * La clase "Huma" amplía la clase "Jugador" y fija el número máximo de vidas en
 * 100.
 * 
 * @author Fernando
 */
public class Huma extends Jugador {

    /**
     * Este es el constructor de la clase Huma
     * 
     * @param nom          El nombre del jugador
     * @param puntsAtac    Los puntos de ataque
     * @param puntsDefensa Los puntos de defensa
     * @param vides        Los puntos de vida del jugador (maximo 100)
     */
    public Huma(String nom, int puntsAtac, int puntsDefensa, int vides) {

        super(nom, puntsAtac, puntsDefensa, (vides > 100) ? 100 : vides);
    }

}
