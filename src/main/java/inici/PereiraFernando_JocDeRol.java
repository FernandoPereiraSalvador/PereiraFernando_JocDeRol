/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package inici;
import altres.Equip;
import altres.Poder;
import altres.*;
import java.util.Random;

/**
 *
 * @author Fernando
 */
public class PereiraFernando_JocDeRol {

    public static void menuConfiguracio() {
        int opcionMenu = -1;

        while (opcionMenu != 0) {
            System.out.println("1.Jugadors");
            System.out.println("2.Equips");
            System.out.println("3.Poders");
            System.out.println("0.Eixir");

            opcionMenu = teclat.Teclat.lligInt("Introduce la opcion: ");
            
            switch (opcionMenu) {
                case 1 -> Jugador.menu();
                case 2 -> Equip.menu();
                case 3 -> Poder.menu();
                default -> System.out.println("Has introducido una opción erronea");
            }

        }

    }
    
    public static void jugar(){
        
        int opcion = 0;
        
        System.out.println("1.Automatizat");
        System.out.println("2.Manual");
        
        opcion = teclat.Teclat.lligInt("Introduce la opcion: ");
        
        switch (opcion) {
            case 1 -> Automatizat();
            case 2 -> Manual();
            default -> System.out.println("Opcion incorrecta");
        }
    }
    
    public static void Automatizat(){
        while(Jugador.llista.size()>1){
        // crea un objeto Random
        Random randJugador1 = new Random();
        Random randJugador2 = new Random();
        
        // genera un índice al azar dentro del rango de los índices del ArrayList
        int randomIndexJugador1 = randJugador1.nextInt(Jugador.llista.size());
        int randomIndexJugador2 = randJugador2.nextInt(Jugador.llista.size());
        
        // obtiene el elemento correspondiente del ArrayList utilizando el índice generado al azar
        Jugador randomJugador1 = Jugador.llista.get(randomIndexJugador1);
            Jugador randomJugador2 = Jugador.llista.get(randomIndexJugador2);
            try {
                randomJugador1.ataca(randomJugador2);
            } catch (AtacAMortException | AtacEllMateixException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public static void Manual() {
        while (Jugador.llista.size() > 1) {
            for (Jugador jugador : Jugador.llista) {
                System.out.println("Turno de " + jugador.getNom());
                int jugadorAtacado = teclat.Teclat.lligInt("¿A que jugador deseas atacar?", 0, Jugador.llista.size());

                try {
                    jugador.ataca(Jugador.llista.get(jugadorAtacado));
                } catch (AtacAMortException | AtacEllMateixException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {

        int opcion = -1;
        
        while(opcion != 0){
            System.out.println("JOC DE ROL");
            System.out.println("1.Configuració");
            System.out.println("2.Jugar");
            System.out.println("0.Eixir");
            
            opcion = teclat.Teclat.lligInt("Introduce la opcion: ");
            
            if(opcion == 1){
                menuConfiguracio();
            }
        }
        

    }
}
