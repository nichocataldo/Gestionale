package org.example.gestionale;

import java.io.*;

public class GestionaleFornitori {
    private String nome;
    private String cognome;
    private String nomeAzienda;


    public GestionaleFornitori(){}

    public void creaFornitori(String nome, String cognome, String azienda){
        this.nome = nome;
        this.cognome = cognome;
        this.nomeAzienda = azienda;
    }
    public void salvaFornitori() throws IOException {
        FileWriter file = new FileWriter("src/main/java/Module/fornitori.cvs");
        BufferedWriter bw = new BufferedWriter(file);
        bw.close();
        file.close();
    }

}
