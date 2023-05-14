/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personatges;

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

    private ArrayList<Poder> poders = new ArrayList<>();

    /**
     * El constructor del Jugador
     *
     * @param nom          El nombre que representa al jugador (es único para cada
     *                     uno)
     * @param puntsAtac    Los puntos de ataque
     * @param puntsDefensa Los puntos de defensa
     * @param vides        Los puntos de vida
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

        // Si el equipo es null lo establecemos como null y paramos la función.
        if (equip == null) {
            this.equip = null;
            return;
        }

        // Si el equipo actual es diferente de null se lo quitamos
        Equip equipoActual = this.getEquip();
        if (equipoActual != null) {
            equipoActual.lleva(this);
        }

        // Si es diferente al actual se lo ponemos.
        if (equip != equipoActual) {
            this.equip = equip;
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
     * Esta función simula un ataque entre dos jugadores en una partida, teniendo en
     * cuenta su ataque y los bonus de defensa.
     *
     * @param jugador El parámetro "jugador" es un objeto de la clase "Jugador", que
     *                representa a un jugador en el juego. El método "ataca" se
     *                utiliza para atacar a otro jugador de la partida, y el
     *                parámetro jugador" representa al jugador que está siendo
     *                atacado.
     * @throws AtacAMortException     Error si ataca a un jugador muerto
     * @throws AtacEllMateixException Error si se ataca a si mismo
     */
    public void ataca(Jugador jugador) throws AtacAMortException, AtacEllMateixException {

        // Imprimimos los datos antes del ataque.
        System.out.println("ABANS DE L'ATAC");
        System.out.println("Atacant: " + this.toString());
        System.out.println("Atacat: " + jugador.toString());
        System.out.println("");

        // Atacamos e imprimimos los datos.
        System.out.println("ATAC");

        if (this.getVides() <= 0 || jugador.getVides() <= 0) {
            throw new AtacAMortException();
        }

        if (this.getNom().equals(jugador.getNom())) {
            throw new AtacEllMateixException();
        }

        int BonoAtaqueThis = 0;
        for (Poder poder : this.poders) {
            BonoAtaqueThis += poder.getBonusAtac();
        }

        int BonoAtaqueJugador = 0;
        for (Poder poder : jugador.poders) {
            BonoAtaqueJugador += poder.getBonusAtac();
        }

        jugador.esColpejatAmb(this.puntsAtac + BonoAtaqueThis);
        System.out.println("El bono de ataque de jugador es: " + BonoAtaqueThis);
        this.esColpejatAmb(jugador.puntsAtac + BonoAtaqueJugador);
        System.out.println("El bono de ataque de this es: " + BonoAtaqueJugador);

        if (jugador.getVides() < 0) {
            jugador.setVides(0);
        }

        if (this.getVides() < 0) {
            this.setVides(0);
        }

        // Imprimimos los datos de después del ataque
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
     * La función calcula el daño recibido por un personaje basado en los puntos de
     * ataque y defensa, y actualiza sus puntos de salud restantes en consecuencia.
     *
     * @param daño Cantidad de daño infligido al personaje ataca
     *
     */
    protected void esColpejatAmb(int daño) {
        // Iniciamos las variables de los bonos de ataque y defensa

        int BonoDefensa = 0;
        for (Poder poder : this.poders) {
            BonoDefensa += poder.getBonusDefensa();
        }
        // Calculamos el ataque y guardamos en una variable las vidas anteriores
        int ataque = Math.max((daño) - (this.getPuntsDefensa() + BonoDefensa), 0);
        int videsAnteriors = this.getVides();
        // Si el ataque es mayor que 0 quitamos los puntos de vida
        if (ataque > 0) {
            this.setVides(this.getVides() - ataque);
        }
        // Imprimimos los datos
        System.out.println(nom + " és colpejat amb " + (daño) + " punts i es defén amb "
                + (this.getPuntsDefensa() + BonoDefensa) + ". Vides: "
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
        String poderes = "";
        String equip = "";

        if (!this.poders.isEmpty()) {
            poderes = "Poderes: ";
            for (Poder poder : poders) {
                poderes += poder.getNom() + " ";
            }
        }

        if (this.equip != null) {
            equip = "Equipo: " + this.equip.getNom() + "  ";
        }

        return nom + "(" + nomClasse + ", PA:" + puntsAtac + ", PD:" + puntsDefensa + ", PV:" + vides + ")" + equip
                + poderes;
    }

    /**
     * Esta función Java comprueba si dos objetos de tipo Jugador son iguales
     * basándose en su atributo nom.
     *
     * @param player El parámetro "jugador" es un objeto de la clase "Jugador", que
     *               está siendo comparado con el objeto actual utilizando el método
     *               "equals".
     * @return El método devuelve un valor booleano, ya sea verdadero o falso,
     *         dependiendo de si el objeto jugador dado es igual al objeto actual
     *         basándose en su atributo nombre (nom).
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
     * La función "posa" añade un objeto "Poder" a una lista de objetos "Poder" si
     * no está ya en la lista.
     *
     * @param poder El parámetro "poder" es un objeto de la clase "Poder". Se pasa
     *              como al método "posa".
     */
    public void posa(Poder poder) {
        boolean añadido = this.poders.add(poder);
        if (añadido) {
            System.out.println("El poder " + poder.getNom() + " se ha añadido correctamente");
        } else {
            System.out.println("Error");
        }
    }

    /**
     * La función "llevar" elimina un "poder" de una lista de "poders" si ya está
     * presente en la lista.
     *
     * @param poder poder es un objeto de la clase Poder, que se pasa como parámetro
     *              al método método llevar.
     */
    public void lleva(Poder poder) {
        boolean eliminado = this.poders.remove(poder);
        if (eliminado) {
            System.out.println("El poder " + poder.getNom() + " se ha eliminado correctamente");
        } else {
            System.out.println("Error");
        }
    }

}
