package org.example.gestionale;

import java.io.*;
import java.util.ArrayList;

public class Dipendenti {
    private String nome;
    private String cognome;
    private String data;
    private String sesso;


    /**
     * Metodo che inizializza i dipendenti.
     */
    public Dipendenti(){}

    /**
     * Metodo che permette l'aggiunta di un dipendente
     * @param nome
     * @param cognome
     * @param sesso
     * @param data
     */
    static public Dipendenti creaDipendente(String nome, String cognome, String sesso, String data){
        Dipendenti dipendente = new Dipendenti();
        dipendente.nome = nome;
        dipendente.cognome = cognome;
        dipendente.sesso = sesso;
        dipendente.data = data;
        return dipendente;
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
    static public ArrayList<Dipendenti> caricaDipedenti() throws IOException {
        ArrayList<Dipendenti> dipendenti = new ArrayList<>();
        FileReader fr = new FileReader("src/main/java/Module/dipendenti.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        String[] dati;
        while (riga != null) {
            Dipendenti dipendente = new Dipendenti();
            System.out.println(riga);
            dati = riga.split(";");
            dipendente.nome = dati[0];
            dipendente.cognome = dati[1];
            dipendente.sesso = dati[2];
            dipendente.data = dati[3];
            dipendenti.add(dipendente);
            System.out.println("Aggiunto " + dipendente);
            riga = br.readLine();
        }
        br.close();
        fr.close();
        return dipendenti;
    }

    /**
     * Metodo che permette di salvare i dati attuali in un file ".cvs" (*comma separated value*) in modo da poterli caricare alla prossima apertura senza dover ricompilare i dati.
     * @throws IOException
     */
    static public void salvaDipendenti(ArrayList<Dipendenti> dipendenti) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Module/dipendenti.cvs");
        BufferedWriter bw = new BufferedWriter(file);
        for (int i = 0; i < dipendenti.size(); i++){
            bw.write(dipendenti.get(i).nome + ";" + dipendenti.get(i).cognome + ";" + dipendenti.get(i).sesso + ";" + dipendenti.get(i).data + "\n");
        }
        bw.close();
        file.close();
    }
    public String toString(){
        return this.nome + " " + this.cognome + " " + this.sesso + " " + this.data;
    }
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getData() {
        return data;
    }

    public String getSesso() {
        return sesso;
    }
}
