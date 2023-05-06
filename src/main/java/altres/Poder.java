/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package altres;

import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class Poder {

    private String nom;
    private int bonusAtac;
    private int bonusDefensa;
    public static ArrayList<Poder> llista = new ArrayList<Poder>();

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

    /**
     * Esta función muestra un menú con opciones para crear, consultar o eliminar
     * potencias y realiza un bucle hasta que el
     * usuario decida salir.
     */
    public static void menu() {
        int opcion = -1;

        while (opcion != 0) {
            inici.PereiraFernando_JocDeRol.printMenuTitle("PODERS");
            System.out.println("1.Crear");
            System.out.println("2.Consultar");
            System.out.println("3.Eliminar");
            System.out.println("0.Eixir");

            opcion = teclat.Teclat.lligInt("Introduce la opcion: ");

            switch (opcion) {
                case 1 ->
                    crear();
                case 2 ->
                    consultar();
                case 3 ->
                    eliminar();
                default ->
                    System.out.println("No se ha encontrado la opcion");
            }
        }
    }

    /**
     * Esta función Java crea un nuevo objeto "Poder" con el nombre, bono de ataque
     * y bono de defensa introducidos por el usuario.
     * , y lo añade a una lista si no existe ya.
     */
    public static void crear() {
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        int bonusAtac = teclat.Teclat.lligInt("Introduce el bono de ataque: ");
        int bonusDefensa = teclat.Teclat.lligInt("Introduce el bono de defensa: ");

        Poder nuevoPoder = new Poder(nom, bonusAtac, bonusDefensa);

        if (!llista.contains(nuevoPoder)) {
            llista.add(nuevoPoder);
        } else {
            System.out.println("El equipo ya existe");
        }
    }

    /**
     * La función "consultar" comprueba si una lista de potencias está vacía e
     * imprime un mensaje en consecuencia, o bien
     * imprime cada potencia de la lista.
     */
    public static void consultar() {
        if (llista.isEmpty()) {
            System.out.println("No hay poderes creados");
        } else {
            System.out.println("Lista de poderes: ");
            for (Poder poder : llista) {
                System.out.println(poder.toString());
            }
        }

    }

    /**
     * Esta función Java elimina una potencia de una lista si existe, basándose en
     * los datos introducidos por el usuario para la potencia.
     */
    public static void eliminar() {
        String nom = teclat.Teclat.lligString("Introduce el nombre: ");
        int bonusAtac = teclat.Teclat.lligInt("Introduce el bono de ataque: ");
        int bonusDefensa = teclat.Teclat.lligInt("Introduce el bono de defensa: ");

        Poder nuevoPoder = new Poder(nom, bonusAtac, bonusDefensa);

        if (!llista.contains(nuevoPoder)) {
            llista.remove(nuevoPoder);
        } else {
            System.out.println("El poder no existe");
        }
    }

}
