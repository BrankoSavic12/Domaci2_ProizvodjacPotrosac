/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.proizvodjacpotrosac;

/**
 *
 * @author MyPC
 */

public class Domaci2_ProizvodjacPotrosac {
    public static void main(String[] args) {
        Skladiste skladiste = new Skladiste(10); 

        // Kreiranje proizvođača
        for (int i = 0; i < 20; i++) {
            new Proizvodjac(skladiste, 500, 1500).start();
        }

        for (int i = 0; i < 30; i++) {
            new Potrosac(skladiste, 1000, 2000).start();
        }

        for (int i = 1; i <= 3; i++) {
            new Izvestac(skladiste, "src/main/java/rs/ac/fink/proizvodjacpotrosac/raspored.txt").start();
        }
    }
}
