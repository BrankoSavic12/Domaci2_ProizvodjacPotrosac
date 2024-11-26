/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.proizvodjacpotrosac;

/**
 *
 * @author MyPC
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Izvestac extends Thread {
    private static int statId = 0;
    private final int id = ++statId;

    private final Skladiste skladiste;
    private final String rasporedDatoteka;

    public Izvestac(Skladiste skladiste, String rasporedDatoteka) {
        this.skladiste = skladiste;
        this.rasporedDatoteka = rasporedDatoteka;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(rasporedDatoteka))) {
            String linija;
            while ((linija = reader.readLine()) != null) {
                String[] delovi = linija.split(" ");
                int sekunde = Integer.parseInt(delovi[0]);
                int izvestacId = Integer.parseInt(delovi[1].replace("Izvestac", ""));
                if (izvestacId == id) {
                    sleep(sekunde * 1000L); 
                    synchronized (skladiste) {
                        synchronized (System.out) {  
                            System.out.println("Izvestac " + id + " - Sadrzaj skladista: " + skladiste.getStanje());
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException ex) {
            // System.out.println("Izveštač " + id + " je završio sa radom.");
        }
    }
}
