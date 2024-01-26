package org.example.gestionale;

import java.io.*;

public class GestionaleFornitori {
    private String nome;
    private String cognome;
    private String nomeAzienda;
    private FileWriter file;
    BufferedWriter bw;
    FileReader fr;
    BufferedReader br;

    public GestionaleFornitori(){}

    public void creaFornitori(String nome, String cognome, String azienda){
        this.nome = nome;
        this.cognome = cognome;
        this.nomeAzienda = azienda;
    }
    public void salvaFornitori() throws IOException {
        file = new FileWriter("fornitori.cvs");
        bw = new BufferedWriter(file);
        bw.write(this.nome + ";" + this.cognome + ";" + this.nomeAzienda);
        bw.close();
        file.close();
    }

}
