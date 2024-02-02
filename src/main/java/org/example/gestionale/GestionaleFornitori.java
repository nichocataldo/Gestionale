package org.example.gestionale;

import java.io.*;
import java.util.ArrayList;

public class GestionaleFornitori {
    private String nome;
    private String cognome;
    private String nomeAzienda;
    /**
     * Metodo che inizializza i fornitori.
     */
    public GestionaleFornitori(){}

    /**
     * Metodo che permette l'aggiunta di un fornitore.
     * @param nome
     * @param cognome
     * @param azienda
     * @return
     */
    static public GestionaleFornitori creaFornitori(String nome, String cognome, String azienda){
        GestionaleFornitori fornitori = new GestionaleFornitori();
        fornitori.nome = nome;
        fornitori.cognome = cognome;
        fornitori.nomeAzienda = azienda;
        return fornitori;
    }

    /**
     * Metodo che permette il caricamento di dati dei fornitori precedentemente salvati.
     * @throws IOException
     */
    static public ArrayList<GestionaleFornitori> caricaFornitori() throws IOException {
        ArrayList<GestionaleFornitori> fornitori = new ArrayList<>();
        FileReader fr = new FileReader("src/main/java/Module/fornitori.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        String[] dati = new String[0];
        if(riga != null) {
            dati = riga.split(";");
        }
        while (riga != null && dati.length == 3) {
            GestionaleFornitori fornitore = new GestionaleFornitori();
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
    static public void salvaFornitori(ArrayList<GestionaleFornitori> fornitori) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Module/fornitori.cvs");
        BufferedWriter bw = new BufferedWriter(file);
        for (int i = 0; i < fornitori.size(); i++){
            bw.write(fornitori.get(i).nome + ";" + fornitori.get(i).cognome + ";" + fornitori.get(i).nomeAzienda);
            if (fornitori.get(i).nomeAzienda == null){
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.close();
        file.close();
    }
    public String getNome() {return nome;}
    public String getCognome() {return cognome;}
    public String getNomeAzienda() {return nomeAzienda;}
}
