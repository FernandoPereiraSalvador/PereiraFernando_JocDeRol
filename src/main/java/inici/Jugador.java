/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inici;

/**
 *
 * @author Fernando
 */
public class Jugador {
    private String nom;
    private int puntsAtac;
    private int puntsDefensa;
    private int vides;

    // Constructor
    public Jugador(String nom, int puntsAtac, int puntsDefensa, int vides) {
        this.nom = nom;
        this.puntsAtac = puntsAtac;
        this.puntsDefensa = puntsDefensa;
        this.vides = vides;
    }
    // Getters

    public String getNom() {
        return nom;
    }

    public int getPuntsAtac() {
        return puntsAtac;
    }

    public int getPuntsDefensa() {
        return puntsDefensa;
    }

    public int getVides() {
        return vides;
    }
    
    // Setters

    protected void setNom(String nom) {
        this.nom = nom;
    }

    protected void setPuntsAtac(int puntsAtac) {
        this.puntsAtac = puntsAtac;
    }

    protected void setPuntsDefensa(int puntsDefensa) {
        this.puntsDefensa = puntsDefensa;
    }

    protected void setVides(int vides) {
        this.vides = vides;
    }
    
    // Metodo ataca
    public void ataca(Jugador jugador){

        System.out.println("ABANS DE L'ATAC");
        System.out.println("Atacant: " + this.toString());
        System.out.println("Atacat: " + jugador.toString());

        System.out.println("ATAC");
        jugador.esColpejatAmb(this.puntsAtac);
        this.esColpejatAmb(jugador.puntsAtac);
        
        System.out.println("DESPRÉS DE L'ATAC");
        System.out.println("Atacant: " + this.toString());
        System.out.println("Atacat: " + jugador.toString());
        
    }

    // Metodo esColpejatAmb
    // Pep Garcia és colpejat amb 27 punts i es defén amb 8. Vides: 39 - 19 = 20
    protected void esColpejatAmb(int quantitat) {

        int ataque = quantitat - puntsDefensa;
        int videsAnteriors = vides;

        if (ataque > 0) {
            vides = vides - ataque;

        }
        System.out.println(nom + " és colpejat amb " + quantitat + " punts i es defén amb " + puntsDefensa + ". Vides: " + videsAnteriors + " - " + ataque + "= " + vides);

    }

    //toString
    @Override
    public String toString() {
        return "Jugador{" + "nom=" + nom + ", puntsAtac=" + puntsAtac + ", puntsDefensa=" + puntsDefensa + ", vides=" + vides + '}';
    }

    
    
    
}
