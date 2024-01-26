package org.example.gestionale;

import java.io.*;
import java.util.ArrayList;

public class GestionaleFornitori {
    private String nome;
    private String cognome;
    private String nomeAzienda;
    public GestionaleFornitori(){}
    static public GestionaleFornitori creaFornitori(String nome, String cognome, String azienda){
        GestionaleFornitori fornitori = new GestionaleFornitori();
        fornitori.nome = nome;
        fornitori.cognome = cognome;
        fornitori.nomeAzienda = azienda;
        return fornitori;
    }
    static public ArrayList<GestionaleFornitori> caricaFornitori() throws IOException {
        ArrayList<GestionaleFornitori> fornitori = new ArrayList<>();
        FileReader fr = new FileReader("src/main/java/Module/fornitori.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        String[] dati;
        while (riga != null) {
            GestionaleFornitori fornitore = new GestionaleFornitori();
            System.out.println(riga);
            dati = riga.split(";");
            fornitore.nome = dati[0];
            //Se presenti questi due errore, crea un fornitore ,null, null
            //fornitore.cognome = dati[1];
            //fornitore.nomeAzienda = dati[2];
            fornitori.add(fornitore);
            System.out.println("Aggiunto " + fornitore);
            riga = br.readLine();
        }
        br.close();
        fr.close();
        return fornitori;
    }
    static public void salvaFornitori(ArrayList<GestionaleFornitori> fornitori) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Module/fornitori.cvs");
        BufferedWriter bw = new BufferedWriter(file);
        for (int i = 0; i < fornitori.size(); i++){
            bw.write(fornitori.get(i).nome + ";" + fornitori.get(i).cognome + ";" + fornitori.get(i).nomeAzienda + "\n");
        }
        bw.close();
        file.close();
    }
    public String getNome() {return nome;}
    public String getCognome() {return cognome;}
    public String getNomeAzienda() {return nomeAzienda;}
}
