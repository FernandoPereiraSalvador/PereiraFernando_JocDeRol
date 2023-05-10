/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inici;

import altres.*;
import java.util.ArrayList;

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
    private ArrayList<Poder> poders = new ArrayList<Poder>();
    static ArrayList<Jugador> llista = new ArrayList<Jugador>();
    static int cantidadVidas = 200;

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
            equip.llevar(this);
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
     * Esta función simula un ataque entre dos jugadores en una partida, teniendo en
     * cuenta su ataque
     * y los bonus de defensa.
     *
     * @param jugador El parámetro "jugador" es un objeto de la clase "Jugador", que
     *                representa a un
     *                jugador en el juego. El método "ataca" se utiliza para atacar
     *                a otro jugador de la partida, y el parámetro
     *                jugador" representa al jugador que está siendo atacado.
     * @throws AtacAMortException     Error si ataca a un jugador muerto
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

        jugador.esColpejatAmb(this.puntsAtac,this.puntsDefensa, BonoAtaqueThis, BonoDefensaThis);
        this.esColpejatAmb(jugador.puntsAtac,this.puntsDefensa, BonoAtaqueJugador, BonoDefensaJugador);

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
            llista.remove(jugador);
        }

        if (this.getVides() <= 0) {
            llista.remove(this);
        }
        System.out.println("---");
    }

    /**
     * La función calcula el daño recibido por un personaje basado en los puntos de
     * ataque y defensa, y
     * actualiza sus puntos de salud restantes en consecuencia.
     *
     * @param quantitat   Cantidad de daño infligido al personaje ataca
     * @param defensa     Cantidad de defensa con la que se defiende.
     * @param bonoAtaque  Valor añadido a los puntos de ataque del personaje.
     * @param bonoDefensa El parámetro "bonoDefensa" es un valor entero que
     *                    representa una bonificación a los
     *                    puntos de defensa del defensor. Se añade a los puntos de
     *                    defensa base del defensor para calcular el total de puntos
     *                    de defensa utilizados en el cálculo del ataque.
     *                    puntos de defensa utilizados en el cálculo del ataque.
     */
    protected void esColpejatAmb(int quantitat,int defensa, int bonoAtaque, int bonoDefensa) {

        int ataque = Math.max((quantitat + bonoAtaque) - (defensa + bonoDefensa), 0);
        int videsAnteriors = this.getVides();
        if (ataque > 0 && !(this instanceof Guerrer)) {
            this.setVides(this.getVides() - ataque);

        } else if (ataque >= 5 && this instanceof Guerrer) {
            this.setVides(this.getVides() - ataque);
            System.out.println("guerrero");
        }

        System.out.println(nom + " és colpejat amb " + (quantitat+bonoAtaque) + " punts i es defén amb " + (defensa+bonoDefensa) + ". Vides: "
                + videsAnteriors + " - " + ataque + "= " + vides);

    }

    /**
     * Devolvemos un string con todos los datos sobre un jugador
     *
     * @return El string con los datos del jugador
     */
    @Override
    public String toString() {

        String tipo = "";

        if (this instanceof Guerrer) {
            tipo = "Guerrer";
        } else if (this instanceof Alien) {
            tipo = "Alien";
        } else if (this instanceof Huma) {
            tipo = "Huma";
        }

        return nom + "(" + tipo + ", PA:" + puntsAtac + ", PD:" + puntsDefensa + ", PV:" + vides + ")";
    }

    /**
     * Esta función Java comprueba si dos objetos de tipo Jugador son iguales
     * basándose en su atributo nom.
     *
     * @param player El parámetro "jugador" es un objeto de la clase "Jugador", que
     *               está siendo comparado con
     *               el objeto actual utilizando el método "equals".
     * @return El método devuelve un valor booleano, ya sea verdadero o falso,
     *         dependiendo de si el
     *         objeto jugador dado es igual al objeto actual basándose en su
     *         atributo nombre (nom).
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
     * no está ya en la
     * lista.
     *
     * @param poder El parámetro "poder" es un objeto de la clase "Poder". Se pasa
     *              como al método "posa".
     */
    public void posa(Poder poder) {

        if (!this.poders.contains(poder)) {
            this.poders.add(poder);

        }

    }

    /**
     * La función "llevar" elimina un "poder" de una lista de "poders" si ya está
     * presente en la lista.
     *
     * @param poder poder es un objeto de la clase Poder, que se pasa como parámetro
     *              al método
     *              método llevar.
     */
    public void llevar(Poder poder) {
        if (this.poders.contains(poder)) {
            this.poders.remove(poder);
        }
    }

    /**
     * Esta función muestra un menú con opciones para crear, consultar, eliminar,
     * asignar a un equipo, eliminar de un equipo, y asignar poder a los jugadores
     * de un partido
     */
    public static void menu() {
        int opcion = -1;

        while (opcion != 0) {
            inici.PereiraFernando_JocDeRol.printMenuTitle("JUGADORS");
            System.out.println("1.Crear");
            System.out.println("2.Consultar");
            System.out.println("3.Eliminar");
            System.out.println("4.Assignar a equip");
            System.out.println("5.Llevar d'equip");
            System.out.println("6.Assignar poder");
            System.out.println("0.Eixir");

            opcion = teclat.Teclat.lligInt("Introduce la opcion: ");

            switch (opcion) {
                case 1 ->
                    crear();
                case 2 ->
                    consultar();
                case 3 ->
                    eliminar();
                case 4 ->
                    assignarEquip();
                case 5 ->
                    llevarEquip();
                case 6 ->
                    assignarPoder();
                default -> {
                }
            }
        }
    }

    /**
     * Esta función crea un nuevo objeto jugador basado en la entrada del usuario y
     * lo añade a una lista de jugadores, comprobando si hay duplicados antes de
     * añadirlo.
     */
    public static void crear() {
        char tipoJugador = teclat.Teclat.lligChar("Introduce el tipo de jugador", "HGA");
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        int puntsAtac = teclat.Teclat.lligInt("Introduce los puntos de ataque: ", 1, 100);
        int puntsDefensa = Math.abs(puntsAtac - 100);

        Jugador nuevoJugador = null;

        switch (tipoJugador) {
            case 'H' ->
                nuevoJugador = new Huma(nom, puntsAtac, puntsDefensa, cantidadVidas);
            case 'G' ->
                nuevoJugador = new Guerrer(nom, puntsAtac, puntsDefensa, cantidadVidas);
            case 'A' ->
                nuevoJugador = new Alien(nom, puntsAtac, puntsDefensa, cantidadVidas);
            default -> {
            }
        }
        boolean encontrado = false;
        for (Jugador jugador : llista) {
            if (jugador.getNom().equals(nuevoJugador.getNom())) {
                encontrado = true;
            }
        }

        if (encontrado) {
            System.out.println("El jugador ya existe");
        } else {
            llista.add(nuevoJugador);
            System.out.println("El jugador se ha creado correctamente");
        }
    }

    /**
     * La función "consultar" imprime una lista de jugadores si los hay, en caso
     * contrario imprime un mensaje indicando que no hay jugadores.
     */
    public static void consultar() {

        if (llista.isEmpty()) {
            System.out.println("No hay jugadores creados");
        } else {
            System.out.println("Lista de jugadores:");
            for (Jugador jugador : llista) {
                System.out.println(jugador);
            }
        }
    }

    /**
     * Esta función elimina un jugador de una lista de jugadores por su nombre.
     */
    public static void eliminar() {

        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        boolean Noeliminado = true;

        for (Jugador jugador : llista) {
            if (jugador.getNom().equals(nom)) {
                llista.remove(jugador);
                System.out.println("Se ha eliminado el jugador correctamente");
                Noeliminado = false;
                break;
            }
        }

        if (Noeliminado) {
            System.out.println("No se ha encontrado al jugador");
        }

    }

    /**
     * Esta función asigna un jugador a un equipo basándose en los datos
     * introducidos por el usuario y comprueba si tanto el jugador como el equipo
     * existen en el sistema.
     */
    public static void assignarEquip() {
        String nom = teclat.Teclat.lligString("Introduce el nombre del jugador: ");
        String nomEquip = teclat.Teclat.lligString("Introduce el nombre del equipo: ");

        Jugador jugador = null;
        Equip equip = null;

        for (int i = 0; i < llista.size(); i++) {
            jugador = llista.get(i);
            if (jugador.getNom().equals(nom)) {
                break;
            } else {
                jugador = null;
            }
        }

        for (int i = 0; i < Equip.llista.size(); i++) {
            equip = Equip.llista.get(i);
            if (equip.getNom().equals(nomEquip)) {
                break;
            } else {
                equip = null;
            }
        }

        if (equip != null && jugador != null) {
            equip.posa(jugador);
            System.out.println("El jugador " + nom +  " se ha asignado al equipo " + nomEquip + " correctamente");
        } else {
            System.out.println("El jugador o el equipo no existen");
        }
    }

    /**
     * Esta función Java asigna un poder a un jugador introduciendo el nombre
     * del jugador y el nombre del poder, y luego añade un poder a la lista de
     * poderes del jugador.
     */
    public static void assignarPoder() {

        if (!Poder.llista.isEmpty()) {
            System.out.println("Poderes disponibles: ");
            for (Poder poder : Poder.llista) {
                System.out.println(poder.toString());
            }
        } else {
            System.out.println("No hay poderes disponibles");
            return;
        }
        
        if(!llista.isEmpty()){
            System.out.println("Jugadores disponibles: ");
            for (Jugador jugador : llista) {
                System.out.println(jugador.toString());
            }
        }else{
            System.out.println("No hay jugadores disponibles");
            return;
        }

        String nomJugador = teclat.Teclat.lligString("Introduce el nombre del jugador: ");
        String poderNombre = teclat.Teclat.lligString("Introduce el nombre del poder: ");

        Jugador jugadorAsignar = null;
        Poder poderAsignar = null;

        for (Jugador jugador : llista) {
            if (jugador.getNom().equals(nomJugador)) {
                jugadorAsignar = jugador;
                break;
            }
        }

        for (Poder poder : Poder.llista) {
            if (poder.getNom().equals(poderNombre)) {
                poderAsignar = poder;
            }
        }

        if (jugadorAsignar != null && poderAsignar != null) {
            jugadorAsignar.poders.add(poderAsignar);
            System.out.println("Se ha asignado correctamente el poder " + poderAsignar.getNom() + " al jugador " + jugadorAsignar.getNom());

        } else {
            System.out.println("Error: El jugador no existe o no hay ningún poder");
        }

    }

    /**
     * La función permite al usuario asignar un jugador a un equipo introduciendo el
     * nombre del jugador y el nombre del equipo, y luego comprobando si ambos
     * existen en sus respectivas
     * listas antes de asignar el jugador al equipo.
     */
    public static void llevarEquip() {
        String nom = teclat.Teclat.lligString("Introduce el nombre del jugador: ");
        String nomEquip = teclat.Teclat.lligString("Introduce el nombre del equipo: ");

        Jugador jugador = null;
        Equip equip = null;

        for (int i = 0; i < llista.size(); i++) {
            jugador = llista.get(i);
            if (jugador.getNom().equals(nom)) {
                break;
            } else {
                jugador = null;
            }
        }

        for (int i = 0; i < Equip.llista.size(); i++) {
            equip = Equip.llista.get(i);
            if (equip.getNom().equals(nomEquip)) {
                break;
            } else {
                equip = null;
            }
        }

        if (equip != null && jugador != null) {
            equip.llevar(jugador);
            System.out.println(" El jugador se ha eliminado del equipo correctamente");
        } else {
            System.out.println("El jugador o el equipo no existen");
        }
    }

}
