/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personatges;

import altres.AtacAMortException;
import altres.AtacEllMateixException;
import altres.Poder;
import inici.Jugadors;

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
    
        public void ataca(Jugador jugador) throws AtacAMortException, AtacEllMateixException {

        enloquecer();
                
        System.out.println("ABANS DE L'ATAC");
        System.out.println("Atacant: " + this.toString());
        System.out.println("Atacat: " + jugador.toString());
        System.out.println("");

        System.out.println("ATAC");

        int BonoAtaqueThis = 0;
        int BonoAtaqueJugador = 0;
        int BonoDefensaThis = 0;
        int BonoDefensaJugador = 0;

        for (Poder poder : this.getPoders()) {
            BonoAtaqueThis += poder.getBonusAtac();
            BonoDefensaThis += poder.getBonusDefensa();
        }

        for (Poder poder : jugador.getPoders()) {
            BonoAtaqueJugador += poder.getBonusAtac();
            BonoDefensaJugador += poder.getBonusDefensa();
        }

        if (this.getVides() <= 0 || jugador.getVides() <= 0) {
            throw new AtacAMortException();
        }

        if (this.getNom().equals(jugador.getNom())) {
            throw new AtacEllMateixException();
        }

        jugador.esColpejatAmb(this.getPuntsAtac());
        this.esColpejatAmb(jugador.getPuntsAtac());

        if (jugador.getVides() < 0) {
            jugador.setVides(0);
        }

        if (this.getVides() < 0) {
            this.setVides(0);
        }

        System.out.println("");

        System.out.println("DESPRÉS DE L'ATAC");
        System.out.println("Atacant: " + this.toString());
        System.out.println("Atacat: " + jugador.toString());
        if (jugador.getVides() <= 0) {
            Jugadors.matar(jugador);
        }

        if (this.getVides() <= 0) {
            Jugadors.matar(this);
        }
        System.out.println("---");
    }
}
