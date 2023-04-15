/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package altres;

/**
 *
 * @author Fernando
 */

public class AtacEllMateixException extends Exception {

    // Constructor passant-li el text de l’error
    public AtacEllMateixException(String msg){
        super(msg);
    }
// Constructor sense passar-li el text de l’error

    public AtacEllMateixException(){
        super("No es pot atacar a ell mateix");
    }

}
