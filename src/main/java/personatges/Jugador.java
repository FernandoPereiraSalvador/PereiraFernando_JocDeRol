/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personatges;

import personatges.Huma;
import personatges.Guerrer;
import personatges.Alien;
import altres.*;
import java.util.ArrayList;
import inici.Jugadors;

/**
 *
 * @author Fernando
 */
public class Jugador {

    private String nom;
    private int puntsAtac;
    private int puntsDefensa;
    private int vides;
    private Equip equip;
    static int cantidadVidas = 200;

    private ArrayList<Poder> poders = new ArrayList<Poder>();

    /**
     * El constructor del Jugador
     *
     * @param nom El nombre que representa al jugador (es único para cada uno)
     * @param puntsAtac Los puntos de ataque
     * @param puntsDefensa Los puntos de defensa
     * @param vides Los puntos de vida
     */
    public Jugador(String nom, int puntsAtac, int puntsDefensa, int vides) {
        this.nom = nom;
        this.puntsAtac = puntsAtac;
        this.puntsDefensa = puntsDefensa;
        this.vides = vides;

    }
    // Getters

    /**
     * Devuelve el nombre del jugador
     *
     * @return El nombre del jugador
     */
    public String getNom() {
        return nom;
    }

    /**
     * Devuelve los puntos de ataque
     *
     * @return Puntos de ataque
     */
    public int getPuntsAtac() {
        return puntsAtac;
    }

    /**
     * Devuelve los puntos de defensa
     *
     * @return Los puntos de defensa
     */
    public int getPuntsDefensa() {
        return puntsDefensa;
    }

    /**
     * Devuelve los puntos de vida
     *
     * @return Los puntos de vida del jugador
     */
    public int getVides() {
        return vides;
    }

    /**
     * Devuelve el equipo
     *
     * @return El equipo del jugador
     */
    public Equip getEquip() {
        return equip;
    }

    public static int getCantidadVidas() {
        return cantidadVidas;
    }

    /**
     * Devuelve una lista con los poderes del jugador
     *
     * @return Una lista con los poderes
     */
    public ArrayList<Poder> getPoders() {
        return poders;
    }

    // Setters
    /**
     * Establece el nombre del jugador
     *
     * @param nom El nombre del jugador
     */
    protected void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Estable los puntos de ataque del jugador
     *
     * @param puntsAtac Los puntos de ataque
     */
    protected void setPuntsAtac(int puntsAtac) {
        this.puntsAtac = puntsAtac;
    }

    /**
     * Establece los puntos de defensa
     *
     * @param puntsDefensa Los puntos de defensa
     */
    protected void setPuntsDefensa(int puntsDefensa) {
        this.puntsDefensa = puntsDefensa;
    }

    /**
     * Establecemos los puntos de vida
     *
     * @param vides Los puntos de vida
     */
    protected void setVides(int vides) {
        this.vides = vides;
    }

    /**
     * Establecemos el equipo del jugador
     *
     * @param equip El equipo
     */
    public void setEquip(Equip equip) {

        if (this.getEquip() != null) {
            equip.lleva(this);
        } else {
            equip.posa(this);

        }

    }

    /**
     * Establecemos los poderes del jugador.
     *
     * @param poders Una lista con todos los poderes
     */
    public void setPoders(ArrayList<Poder> poders) {
        this.poders = poders;
    }

    /**
     * Esta función simula un ataque entre dos jugadores en una partida, teniendo en cuenta su ataque y los bonus de defensa.
     *
     * @param jugador El parámetro "jugador" es un objeto de la clase "Jugador", que representa a un jugador en el juego. El método "ataca" se utiliza para atacar a otro jugador de la partida, y el parámetro jugador" representa al jugador que está siendo atacado.
     * @throws AtacAMortException Error si ataca a un jugador muerto
     * @throws AtacEllMateixException Error si se ataca a si mismo
     */
    public void ataca(Jugador jugador) throws AtacAMortException, AtacEllMateixException {

        if (this instanceof Alien) {
            ((Alien) this).enloquecer();
        }

        System.out.println("ABANS DE L'ATAC");
        System.out.println("Atacant: " + this.toString());
        System.out.println("Atacat: " + jugador.toString());
        System.out.println("");

        System.out.println("ATAC");

        int BonoAtaqueThis = 0;
        int BonoAtaqueJugador = 0;
        int BonoDefensaThis = 0;
        int BonoDefensaJugador = 0;

        for (Poder poder : this.poders) {
            BonoAtaqueThis += poder.getBonusAtac();
            BonoDefensaThis += poder.getBonusDefensa();
        }

        for (Poder poder : jugador.poders) {
            BonoAtaqueJugador += poder.getBonusAtac();
            BonoDefensaJugador += poder.getBonusDefensa();
        }

        if (this.getVides() <= 0 || jugador.getVides() <= 0) {
            throw new AtacAMortException();
        }

        if (this.getNom().equals(jugador.getNom())) {
            throw new AtacEllMateixException();
        }

        jugador.esColpejatAmb(this.puntsAtac, this.puntsDefensa, BonoAtaqueThis, BonoDefensaThis);
        this.esColpejatAmb(jugador.puntsAtac, this.puntsDefensa, BonoAtaqueJugador, BonoDefensaJugador);

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

    /**
     * La función calcula el daño recibido por un personaje basado en los puntos de ataque y defensa, y actualiza sus puntos de salud restantes en consecuencia.
     *
     * @param quantitat Cantidad de daño infligido al personaje ataca
     * @param defensa Cantidad de defensa con la que se defiende.
     * @param bonoAtaque Valor añadido a los puntos de ataque del personaje.
     * @param bonoDefensa El parámetro "bonoDefensa" es un valor entero que representa una bonificación a los puntos de defensa del defensor. Se añade a los puntos de defensa base del defensor para calcular el total de puntos de defensa utilizados en el cálculo del ataque. puntos de defensa utilizados en el cálculo del ataque.
     */
    protected void esColpejatAmb(int quantitat, int defensa, int bonoAtaque, int bonoDefensa) {
        int ataque = Math.max((quantitat + bonoAtaque) - (defensa + bonoDefensa), 0);
        int videsAnteriors = this.getVides();

        if (ataque > 0) {
            this.setVides(this.getVides() - ataque);
        }

        System.out.println(nom + " és colpejat amb " + (quantitat + bonoAtaque) + " punts i es defén amb " + (defensa + bonoDefensa) + ". Vides: "
                + videsAnteriors + " - " + ataque + "= " + vides);
    }

    /**
     * Devolvemos un string con todos los datos sobre un jugador
     *
     * @return El string con los datos del jugador
     */
    @Override
    public String toString() {
        String nomClasse = this.getClass().getName();
        nomClasse = nomClasse.substring(nomClasse.lastIndexOf(".") + 1);
        return nom + "(" + nomClasse + ", PA:" + puntsAtac + ", PD:" + puntsDefensa + ", PV:" + vides + ")";
    }

    /**
     * Esta función Java comprueba si dos objetos de tipo Jugador son iguales basándose en su atributo nom.
     *
     * @param player El parámetro "jugador" es un objeto de la clase "Jugador", que está siendo comparado con el objeto actual utilizando el método "equals".
     * @return El método devuelve un valor booleano, ya sea verdadero o falso, dependiendo de si el objeto jugador dado es igual al objeto actual basándose en su atributo nombre (nom).
     */
    public boolean equals(Jugador player) {
        if (player == this) {
            return true;
        }
        if (!(player instanceof Jugador)) {
            return false;
        }
        Jugador jugador = (Jugador) player;
        return nom.equals(jugador.nom);
    }

    /**
     * La función "posa" añade un objeto "Poder" a una lista de objetos "Poder" si no está ya en la lista.
     *
     * @param poder El parámetro "poder" es un objeto de la clase "Poder". Se pasa como al método "posa".
     */
    public void posa(Poder poder) {

        if (!this.poders.contains(poder)) {
            this.poders.add(poder);
        }
    }

    /**
     * La función "llevar" elimina un "poder" de una lista de "poders" si ya está presente en la lista.
     *
     * @param poder poder es un objeto de la clase Poder, que se pasa como parámetro al método método llevar.
     */
    public void lleva(Poder poder) {
        if (this.poders.contains(poder)) {
            this.poders.remove(poder);
        }
    }

}
