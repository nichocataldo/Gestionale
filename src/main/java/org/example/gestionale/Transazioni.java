package org.example.gestionale;

import java.io.*;
import java.util.ArrayList;

public class Transazioni {
    private String importo;
    private String cliente;
    public Transazioni(){}
    static public Transazioni nuovaTransazione(String importo, String cliente){
        Transazioni transazioni = new Transazioni();
        transazioni.importo = importo;
        transazioni.cliente = cliente;
        return transazioni;
    }
    static public ArrayList<Transazioni> caricaTransazioni() throws IOException {
        ArrayList<Transazioni> transazioni = new ArrayList<>();
        FileReader fr = new FileReader("src/main/java/Module/Transazioni.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        String[] dati = new String[0];
        if(riga != null) {
            dati = riga.split(";");
        }
        while ((riga = br.readLine()) != null) {
            Transazioni transazione = new Transazioni();
            System.out.println(riga);
            transazione.importo = dati[0];
            transazione.cliente = dati[1];
            transazioni.add(transazione);
            System.out.println("Aggiunto " + transazione);
            riga = br.readLine();
            if (riga != null) {
                dati = riga.split(";");
            }
        }
        br.close();
        fr.close();
        return transazioni;
    }
    static public void salvaTransazioni(ArrayList<Transazioni> transazioni) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Module/Transazioni.cvs");
        BufferedWriter bw = new BufferedWriter(file);
        for (int i = 0; i < transazioni.size(); i++){
            bw.write(transazioni.get(i).importo + ";" + transazioni.get(i).cliente);
            bw.write("\n");
        }
        bw.close();
        file.close();
    }
}
