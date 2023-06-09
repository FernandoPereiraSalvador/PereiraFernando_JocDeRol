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
        } else if (enloquecido && this.getVides() <= 20) {
            this.setPuntsAtac(this.getPuntsAtac() - 3);
            this.setPuntsDefensa(this.getPuntsDefensa() + 3);
            enloquecido = false;
        }
    }

    /**
     * La función "ataca" simula un ataque entre dos jugadores en una partida,
     * comprobando las excepciones y
     * actualizando sus puntos de vida en consecuencia.
     * 
     * @param jugador El parámetro "jugador" es un objeto de la clase "Jugador", que
     *                representa al jugador
     *                que está siendo atacado por el jugador actual.
     */
    public void ataca(Jugador jugador) throws AtacAMortException, AtacEllMateixException {

        // Llamamos a la función enloquecer.
        enloquecer();

        // Mostramos los datos antes del ataque
        System.out.println("ABANS DE L'ATAC");
        System.out.println("Atacant: " + this.toString());
        System.out.println("Atacat: " + jugador.toString());
        System.out.println("");

        // Atacamos y mostramos los datos
        System.out.println("ATAC");

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

        // Mostramos los datos después del ataque.
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
