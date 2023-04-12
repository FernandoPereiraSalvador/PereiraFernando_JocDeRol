/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package inici;
import altres.Equip;
import altres.Poder;

/**
 *
 * @author Fernando
 */
public class PereiraFernando_JocDeRol {

    static void provaFase1() {
        System.out.println("Vaig a crear un huma");
        Huma NuevoHumano = new Huma("Pepe", 4, 3, 500);
        System.out.println(NuevoHumano.toString());

        System.out.println("Vaig a crear un Guerrer");
        Guerrer NuevoGuerrer = new Guerrer("Guerrer", 9, 3, 9);
        System.out.println(NuevoGuerrer.toString());

        System.out.println("Vaig a crear un Alien");
        Alien NuevoAlien = new Alien("Alien", 3, 4, 22);
        System.out.println(NuevoAlien.toString());

    }

    static void provaFase2() {
        Alien NuevoAlien = new Alien("Alien", 3, 4, 15);
        Guerrer NuevoGuerrer = new Guerrer("Guerrer", 9, 3, 9);

        NuevoAlien.ataca(NuevoGuerrer);
    }

    static void provaFase3() {
        Alien NuevoAlien = new Alien("Alien", 3, 4, 15);
        Guerrer NuevoGuerrer = new Guerrer("Guerrer", 9, 3, 9);

        NuevoAlien.ataca(NuevoGuerrer);
    }

    static void provaFase4() {
        
        Equip pepes = new Equip("Pepes");
        
        Alien NuevoAlien = new Alien("Alien", 3, 4, 15);

        NuevoAlien.setEquip(pepes);
        pepes.llevar(NuevoAlien);
    }

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
