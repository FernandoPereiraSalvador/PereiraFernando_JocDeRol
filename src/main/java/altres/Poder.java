/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package altres;

/**
 *
 * @author Fernando
 */
public class Poder {

    private String nom;
    private int bonusAtac;
    private int bonusDefensa;

    // Getters
    /**
     * El getter del nombre de la clase poder
     * 
     * @return El nombre del poder
     */
    public String getNom() {
        return nom;
    }

    /**
     * El getter del bono de ataque de la clase poder
     * 
     * @return El int del bono de ataque
     */
    public int getBonusAtac() {
        return bonusAtac;
    }

    /**
     * El getter del bono de ataque de la clase poder
     * 
     * @return El int del bono de defensa
     */
    public int getBonusDefensa() {
        return bonusDefensa;
    }

    // Setters
    /**
     * El setter del nombre del poder
     * 
     * @param nom El nombre del poder
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * El setter del bono de ataque
     * 
     * @param bonusAtac El int del bono de ataque
     */
    public void setBonusAtac(int bonusAtac) {
        this.bonusAtac = bonusAtac;
    }

    /**
     * El setter del bono de defensa
     * 
     * @param bonusDefensa El int del bono de defensa
     */
    public void setBonusDefensa(int bonusDefensa) {
        this.bonusDefensa = bonusDefensa;
    }

    // Constructor
    /**
     * El constructor de la clase poder
     * 
     * @param nom          El nombre del poder
     * @param bonusAtac    El int del bono de ataque
     * @param bonusDefensa El int del bono de defensa
     */
    public Poder(String nom, int bonusAtac, int bonusDefensa) {
        this.nom = nom;
        this.bonusAtac = bonusAtac;
        this.bonusDefensa = bonusDefensa;
    }

    /**
     * Esta es una función Java que sobrescribe el método por defecto toString()
     * para devolver una cadena
     * del nombre, bono de ataque y bono de defensa de un objeto.
     * 
     * @return Una representación de cadena del nombre de un objeto y sus atributos
     *         bonusAtac y bonusDefensa.
     */
    @Override
    public String toString() {
        return nom + " { bonusAtac=" + bonusAtac + ", bonusDefensa=" + bonusDefensa + '}';
    }

}
