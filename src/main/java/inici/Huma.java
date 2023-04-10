/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inici;

/**
 *
 * @author Fernando
 */
public class Huma extends Jugador {

    public Huma(String nom, int puntsAtac, int puntsDefensa, int vides) {

        super(nom, puntsAtac, puntsDefensa, (vides > 100) ? 100 : vides);
    }

}
