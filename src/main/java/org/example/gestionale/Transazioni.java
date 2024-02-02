package org.example.gestionale;

import javafx.util.converter.FloatStringConverter;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe che gestisce i movimenti monetari dell'azienda.
 */
public class Transazioni {
    private String importo;
    private String cliente;

    /**
     * Costruttore che inizializza la classe.
     */
    public Transazioni() {
    }

    /**
     * Crea una nuova transazione da aggiungere a quelle attualmente esistenti.
     * @param importo
     * @param cliente
     * @return Ritorna una variabile di tipo Transazione da aggiungere alla lista.
     */
    static public Transazioni nuovaTransazione(String importo, String cliente) {
        Transazioni transazioni = new Transazioni();
        transazioni.importo = importo;
        transazioni.cliente = cliente;
        return transazioni;
    }

    /**
     * Metodo che controlla se l'importo inserito è un numero.
     * @param str
     * @return Un booleano che dice se il numero inserito è un numero.
     */
    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Metodo che carica le transazioni fatte in precedenza da un file su cui sono salvate le transazioni.
     * @return Ritorna la lista delle transazioni fatte in precedenza.
     * @throws IOException
     */
    static public ArrayList<Transazioni> caricaTransazioni() throws IOException {
        ArrayList<Transazioni> transazioni = new ArrayList<>();
        FileReader fr = new FileReader("src/main/java/Module/Transazioni.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        System.out.println(riga);
        String[] dati = new String[0];
        if (riga != null) {
            dati = riga.split(";");
        }
        // System.out.println(riga.length());
        while ((riga = br.readLine()) != null) {
            if (!riga.isEmpty()) {
                Transazioni transazione = new Transazioni();
                System.out.println(riga);
                transazione.importo = dati[0];
                transazione.cliente = dati[1];
                transazioni.add(transazione);
                System.out.println("Aggiunto " + transazione);
                dati = riga.split(";");
            }
        }
        br.close();
        fr.close();
        return transazioni;
    }

    /**
     * Metodo che salva l'ultima transazione fatta in un file apposito, che verrà caricato alla prossima sessione.
     * @param transazioni
     * @throws IOException
     */
    static public void salvaTransazioni(ArrayList<Transazioni> transazioni) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Module/Transazioni.cvs", true);
        BufferedWriter bw = new BufferedWriter(file);
        int index = transazioni.size() - 1;
        FileReader fr = new FileReader("src/main/java/Module/Transazioni.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        if (riga != null) {
            bw.write("\n");
        }
        bw.write(transazioni.get(index).importo + ";" + transazioni.get(index).cliente);
        bw.close();
        file.close();
    }

    /**
     * Metodo che calcola il saldo attualmente disponibile dell'azienda.
     * @param transazioni
     * @return Il saldo attualmente disponibile.
     */
    public static float totaleConto(ArrayList<Transazioni> transazioni) {
        float somma = 0;
        for(Transazioni transazione:transazioni){
            if (transazione.importo.charAt(0) == '+'){
                somma += Float.parseFloat(transazione.importo.substring(1));
            } else if (transazione.importo.charAt(0) == '-'){
                somma -= Float.parseFloat(transazione.importo.substring(1));
            }
        }
        return somma;
    }

    /**
     * Metodo che calcola le entrate totali dell'azienda.
     * @param transazioni
     * @return Totale delle entrate dell'azienda.
     */
    public static float totaleEntrate(ArrayList<Transazioni> transazioni) {
        float entrate = 0;
        for(Transazioni transazione:transazioni){
            if (transazione.importo.charAt(0) == '+'){
                entrate += Float.parseFloat(transazione.importo.substring(1));
            }
        }
        return entrate;
    }

    /**
     * Metodo che calcola tutte le uscite dell'azienda.
     * @param transazioni
     * @return Totale delle uscite dell'azienda.
     */
    public static float totaleUscite(ArrayList<Transazioni> transazioni) {
        float uscite = 0;
        for(Transazioni transazione:transazioni){
            if (transazione.importo.charAt(0) == '-'){
                uscite += Float.parseFloat(transazione.importo.substring(1));
            }
        }
        return uscite;
    }

    /**
     * Metodo per ottenere l'importo pagato o ricevuto di una transazione.
     * @return L'importo pagato/ricevuto.
     */
    public String getImporto() {
        return importo;
    }

    /**
     * Metodo per ottenere l'ente con cui è stato fatto il pagamento o da cui si è ricevuta la somma.
     * @return Ente terzo della transazione.
     */
    public String getCliente() {
        return cliente;
    }
}
