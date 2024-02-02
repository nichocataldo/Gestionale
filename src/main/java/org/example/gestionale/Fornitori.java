package org.example.gestionale;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe che gestisce i fornitori che collaborano con l'azienda.
 */
public class Fornitori {
    private String nome;
    private String cognome;
    private String nomeAzienda;
    /**
     * Costruttore inizializza i fornitori.
     */
    public Fornitori(){}

    /**
     * Metodo che permette l'aggiunta di un fornitore.
     * @param nome
     * @param cognome
     * @param azienda
     * @return
     */
    static public Fornitori creaFornitori(String nome, String cognome, String azienda){
        Fornitori fornitori = new Fornitori();
        fornitori.nome = nome;
        fornitori.cognome = cognome;
        fornitori.nomeAzienda = azienda;
        return fornitori;
    }

    /**
     * Metodo che permette il caricamento di dati dei fornitori precedentemente salvati.
     * @throws IOException
     */
    static public ArrayList<Fornitori> caricaFornitori() throws IOException {
        ArrayList<Fornitori> fornitori = new ArrayList<>();
        FileReader fr = new FileReader("src/main/java/Module/fornitori.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        String[] dati = new String[0];
        if(riga != null) {
            dati = riga.split(";");
        }
        while (riga != null && dati.length == 3) {
            Fornitori fornitore = new Fornitori();
            System.out.println(riga);
            fornitore.nome = dati[0];
            fornitore.cognome = dati[1];
            fornitore.nomeAzienda = dati[2];
            fornitori.add(fornitore);
            System.out.println("Aggiunto " + fornitore);
            riga = br.readLine();
            if (riga != null) {
                dati = riga.split(";");
            }
        }
        br.close();
        fr.close();
        return fornitori;
    }

    /**
     * Metodo che permette di salvare i dati attuali in un file ".cvs" (*comma separated value*) in modo da poterli caricare alla prossima apertura senza dover ricompilare i dati.
     * @param fornitori
     * @throws IOException
     */
    static public void salvaFornitori(ArrayList<Fornitori> fornitori) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Module/fornitori.cvs");
        BufferedWriter bw = new BufferedWriter(file);
        for (Fornitori fornitore : fornitori) {
            bw.write(fornitore.nome + ";" + fornitore.cognome + ";" + fornitore.nomeAzienda);
            if (fornitore.nomeAzienda == null) {
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.close();
        file.close();
    }

    /**
     * Metodo per ottenere il nome del fornitore.
     * @return Nome del fornitore
     */
    public String getNome() {return nome;}

    /**
     * Metodo per ottenere il cognome del fornitore.
     * @return Cognome del fornitore.
     */
    public String getCognome() {return cognome;}

    /**
     * Metodo per ottenere l'azienda del fornitore.
     * @return Nome dell'azidenda del fornitore.
     */
    public String getNomeAzienda() {return nomeAzienda;}
}
