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
     * Metodo che crea un dipendente, passato i dati anagrafici del dipendente.
     * @param nome
     * @param cognome
     * @param sesso
     * @param data
     * @return Ritorna un "dipendente" da aggiungere alla lista dei dipendenti esistenti.
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
    static public ArrayList<Dipendenti> caricaDipedenti() throws IOException {
        ArrayList<Dipendenti> dipendenti = new ArrayList<>();
        FileReader fr = new FileReader("src/main/java/Module/dipendenti.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        String[] dati = null;
        if (riga != null) {
            dati = riga.split(";");
        }
        while (riga != null && dati.length == 4) {
            Dipendenti dipendente = new Dipendenti();
            dipendente.nome = dati[0];
            dipendente.cognome = dati[1];
            dipendente.sesso = dati[2];
            dipendente.data = dati[3];
            dipendenti.add(dipendente);
            // System.out.println("Aggiunto " + dipendente);
            riga = br.readLine();
            if (riga != null){
                dati = riga.split(";");
            }
            System.out.println(riga);
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

    /**
     * Metodo che stampa i dati del dipendente.
     * @return Ritorna una stringa che contiene i dati del dipendente.
     */
    public String toString(){
        return this.nome + " " + this.cognome + " " + this.sesso + " " + this.data;
    }

    /**
     * Metodo per ottenere il nome del dipendente.
     * @return Il nome del dipendente scelto.
     */
    public String getNome() {return nome;}

    /**
     * Metodo per ottenere il cognome del dipendente.
     * @return Il cognome del dipendente scelto.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo per ottenere la data di nascita del dipendente.
     * @return La data di nascita del dipendente scelto.
     */
    public String getData() {
        return data;
    }

    /**
     * Metodo per ottenere il sesso del dipendente scelto.
     * @return Il sesso del dipendente scelto.
     */
    public String getSesso() {
        return sesso;
    }
}
