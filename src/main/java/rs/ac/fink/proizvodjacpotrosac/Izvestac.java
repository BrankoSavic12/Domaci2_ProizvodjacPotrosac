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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Izvestac extends Thread {
    private static int statId = 0;
    private final int id = ++statId;

    private final Skladiste skladiste;
    private final String rasporedDatoteka;

  
    private static int currentReport = 1; 
    private static final Lock lock = new ReentrantLock(); 

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
                int oglaseniIzvestacRedniBroj = Integer.parseInt(delovi[0]); 
                int izvestacId = Integer.parseInt(delovi[1].replace("Izvestac", ""));

             
                if (izvestacId == id) {
                   
                    synchronized (lock) {
                       
                        while (oglaseniIzvestacRedniBroj != currentReport) {
                            lock.wait();
                        }
                     
                        sleep(10 * 1000L);  

                        synchronized (skladiste) {
                            synchronized (System.out) {
                                System.out.println("Izvestac " + id + " - Sadrzaj skladista: " + skladiste.getStanje());
                            }
                        }

                        currentReport++;
                        lock.notifyAll();
                    }
                }
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Došlo je do greške u radu izveštača " + id);
        }
    }
}

