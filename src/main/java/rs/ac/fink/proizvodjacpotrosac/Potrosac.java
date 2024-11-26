/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.fink.proizvodjacpotrosac;

/**
 *
 * @author MyPC
 */
public class Potrosac extends Thread {
    private static int statId = 0;
    private final int id = ++statId;

    private final Skladiste skladiste;
    private final int minTime;
    private final int maxTime;

    public Potrosac(Skladiste skladiste, int minTime, int maxTime) {
        this.skladiste = skladiste;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public void run() {
        //System.out.println("Potrošač " + id + " je počeo sa radom.");
        try {
            while (!interrupted()) {
                int trajanje = minTime + (int) (Math.random() * (maxTime - minTime));
                sleep(trajanje);
                int proizvod = skladiste.Uzmi();
                //System.out.println("Potrošač " + id + " je preuzeo proizvod " + proizvod);
            }
        } catch (InterruptedException ex) {
            //System.out.println("Potrošač " + id + " je završio sa radom.");
        }
    }
}
