package org.example.gestionale;

import java.io.*;
import java.util.Scanner;

public class GestionaleDipendenti {
    private String nome;
    private String cognome;
    private String data;
    private String sesso;
    private FileWriter file;
    BufferedWriter bw;


    /**
     * Metodo che inizializza i dipendenti.
     */
    public GestionaleDipendenti(){};

    /**
     * Metodo che permette l'aggiunta di un dipendente
     * @param nome
     * @param cognome
     * @param sesso
     * @param data
     */
    public void creaDipendente(String nome, String cognome, String sesso, String data){
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.data = data;
    }

    /**
     * Metodo che permette la modifica di dati anagrafici di un dipendente, passando i dati aggiornati.
     * @param nome
     * @param cognome
     * @param sesso
     * @param data
     */
    public void modificaDipendente(String nome, String cognome, String sesso, String data){
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.data = data;
    }

    /**
     * Metodo che permette il caricamento di dati dei dipendenti precedentemente salvati.
     * @throws IOException
     */
    // Nota: Da ridiscutere (molto probabilmente il read lo si deve fare nel Controller)
    public void caricaDipedenti() throws IOException {
        FileReader file = new FileReader("dipendenti.cvs");
        BufferedReader fileRead = new BufferedReader(file);
        String riga;
        String[] dati;
        riga = fileRead.readLine();
        dati = riga.split(";");
        this.nome = dati[0];
        this.cognome = dati[1];
        this.sesso = dati[2];
        this.data = dati[3];
    }

    /**
     * Metodo che permette di salvare i dati attuali in un file ".cvs" (*comma separated value*) in modo da poterli caricare alla prossima apertura senza dover ricompilare i dati.
     * @throws IOException
     */
    public void salvaDipendenti() throws IOException {
        file = new FileWriter("dipendenti.cvs");
        bw = new BufferedWriter(file);
        bw.write(this.nome + ";" + this.cognome + ";" + this.sesso + ";" + this.data);
        bw.close();
        file.close();
    }
}
