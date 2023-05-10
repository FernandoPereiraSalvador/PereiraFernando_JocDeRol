/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package altres;

/**
 *
 * @author Fernando
 */
public class AtacAMortException extends Exception {

  
    /**
     * Constructor pasandole el texto del error
     * @param msg El mensaje del error
     */
    public AtacAMortException(String msg) {
        super(msg);
    }

    /**
     * Constructor sin pasarle el texto del error
     */
    public AtacAMortException() {
        super("Un jugador muerto no puede atacar ni ser atacado");
    }

}
