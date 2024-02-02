package org.example.gestionale;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe che gestisce i clienti dell'azienda.
 */
public class Clienti {
    private String nome;
    private String cognome;
    private String nomeAzienda;
    /**
     * Costruttore che inizializza i clienti.
     */
    public Clienti(){}

    /**
     * Metodo che permette l'aggiunta di un cliente.
     * @param nome
     * @param cognome
     * @param azienda
     * @return
     */
    static public Clienti creaClienti(String nome, String cognome, String azienda){
        Clienti clienti = new Clienti();
        clienti.nome = nome;
        clienti.cognome = cognome;
        clienti.nomeAzienda = azienda;
        return clienti;
    }

    /**
     * Metodo che permette il caricamento di dati dei clienti precedentemente salvati.
     * @throws IOException
     */
    static public ArrayList<Clienti> caricaClienti() throws IOException {
        ArrayList<Clienti> clienti = new ArrayList<>();
        FileReader fr = new FileReader("src/main/java/Module/clienti.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        String[] dati = new String[0];
        if(riga != null) {
            dati = riga.split(";");
        }
        while (riga != null && dati.length == 3) {
            Clienti cliente = new Clienti();
            System.out.println(riga);
            cliente.nome = dati[0];
            cliente.cognome = dati[1];
            cliente.nomeAzienda = dati[2];
            clienti.add(cliente);
            System.out.println("Aggiunto " + cliente);
            riga = br.readLine();
            if (riga != null) {
                dati = riga.split(";");
            }
        }
        br.close();
        fr.close();
        return clienti;
    }

    /**
     *  Metodo che permette di salvare i dati attuali in un file ".cvs" (*comma separated value*) in modo da poterli caricare alla prossima apertura senza dover ricompilare i dati.
     * @param clienti
     * @throws IOException
     */
    static public void salvaClienti(ArrayList<Clienti> clienti) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Module/clienti.cvs");
        BufferedWriter bw = new BufferedWriter(file);
        for (Clienti cliente : clienti) {
            bw.write(cliente.nome + ";" + cliente.cognome + ";" + cliente.nomeAzienda);
            if (cliente.nomeAzienda == null) {
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.close();
        file.close();
    }

    /**
     * Metodo per ottenere il nome di un cliente.
     * @return Nome del cliente.
     */
    public String getNome() {return nome;}

    /**
     * Metodo per ottenere il cognome di un cliente.
     * @return Cognome del cliente.
     */
    public String getCognome() {return cognome;}

    /**
     * Metodo per ottenere l'azienda del cliente.
     * @return Nome dell'azienda del cliente.
     */
    public String getNomeAzienda() {return nomeAzienda;}
}

