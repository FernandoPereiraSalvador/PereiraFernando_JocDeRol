/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personatges;

import altres.Poder;

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

    /**
     * La función calcula el daño recibido por un personaje en un juego, teniendo en
     * cuenta su defensa
     * y los bonus de ataque o defensa de sus poderes.
     * 
     * @param daño La cantidad de daño que recibe el personaje.
     */
    @Override
    protected void esColpejatAmb(int daño) {
        // Iniciamos las variables de los bonos de ataque y defensa
        int BonoAtaque = 0;
        int BonoDefensa = 0;
        for (Poder poder : this.getPoders()) {
            BonoAtaque += poder.getBonusAtac();
            BonoDefensa += poder.getBonusDefensa();
        }

        // Calculamos el ataque y guardamos en una variable las vidas anteriores
        int ataque = Math.max((daño + BonoAtaque) - (this.getPuntsDefensa() + BonoDefensa), 0);
        int videsAnteriors = this.getVides();

        // Si el ataque es mayor que 5 quitamos los puntos de vida
        if (ataque > 5) {
            this.setVides(this.getVides() - ataque);
        }

        // Imprimimos los datos
        System.out.println(this.getNom() + " és colpejat amb " + (daño + BonoAtaque) + " punts i es defén amb "
                + (this.getPuntsDefensa() + BonoDefensa) + ". Vides: "
                + videsAnteriors + " - " + ataque + "= " + this.getVides());
    }

}
