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
    
    public Alien(String nom, int puntsAtac, int puntsDefensa, int vides) {
        
        super(nom, puntsAtac, puntsDefensa, vides);
        
        
    }

    public void enloquecer() {
        if (this.getVides() > 20) {
            this.setPuntsAtac(this.getPuntsAtac() + 3);
            this.setPuntsDefensa(this.getPuntsDefensa() - 3);
            enloquecido = true;
            System.out.println("Ha enloquecido");
        } else if (enloquecido) {
            this.setPuntsAtac(this.getPuntsAtac() - 3);
            this.setPuntsDefensa(this.getPuntsDefensa() + 3);
        }
    }
}
