/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.proizvodjacpotrosac;

/**
 *
 * @author MyPC
 */
import java.util.logging.Level;
import java.util.logging.Logger;

public class Proizvodjac extends Thread {
    private static int statId = 0;
    private int id = ++statId;

    private Skladiste skladiste;
    private int brojac = 0;
    private int minTime;
    private int maxTime;

    public Proizvodjac(Skladiste skladiste, int minTime, int maxTime) {
        this.skladiste = skladiste;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public void run() {
        //System.out.println("Proizvodjac " + id + " je krenuo sa proizvodnjom");
        try {
            while (!interrupted()) {
                int trajanje = minTime + (int) (Math.random() * (maxTime - minTime));
                sleep(trajanje);

                int proizvod = id * 1000 + brojac++;
                skladiste.Stavi(proizvod);

                //System.out.println("Proizveden je proizvod " + proizvod + " i dodat u skladiste");
            }
        } catch (InterruptedException ex) {
            //System.out.println("Proizvodjac " + id + " je zavrsio sa radom");
        }
    }
}
