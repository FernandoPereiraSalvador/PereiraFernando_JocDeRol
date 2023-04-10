/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.pereirafernando_jocderol;

import inici.Jugador;
import inici.Huma;
import inici.Alien;
import inici.Guerrer;

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

    public static void main(String[] args) {

        provaFase2();

    }
}
