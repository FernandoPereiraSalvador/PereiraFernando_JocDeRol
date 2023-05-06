/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inici;

/**
 *
 * @author Fernando
 */
public class Alien extends Jugador {

    private boolean enloquecido;

    /**
     * El constructor de la clase Alien
     * 
     * @param nom          El nombre del jugador
     * @param puntsAtac    Los puntos de ataque del jugador
     * @param puntsDefensa Los puntos de defensa del jugador
     * @param vides        Los puntos de vida del jugador
     */
    public Alien(String nom, int puntsAtac, int puntsDefensa, int vides) {

        super(nom, puntsAtac, puntsDefensa, vides);

    }

    /**
     * La función "enloquecer" modifica los puntos de ataque y defensa de un
     * personaje en función de sus
     * vidas restantes, y establece una bandera booleana para indicar si el
     * personaje está actualmente "enloquecido".
     */
    public void enloquecer() {
        if (this.getVides() > 20) {
            this.setPuntsAtac(this.getPuntsAtac() + 3);
            this.setPuntsDefensa(this.getPuntsDefensa() - 3);
            enloquecido = true;
            System.out.println("Ha enloquecido");
        } else if (enloquecido) {
            this.setPuntsAtac(this.getPuntsAtac() - 3);
            this.setPuntsDefensa(this.getPuntsDefensa() + 3);
            enloquecido = false;
        }
    }
}
