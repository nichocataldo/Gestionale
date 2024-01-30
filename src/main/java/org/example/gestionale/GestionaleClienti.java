package org.example.gestionale;

import java.io.*;
import java.util.ArrayList;

public class GestionaleClienti {
    private String nome;
    private String cognome;
    private String nomeAzienda;
    public GestionaleClienti(){}
    static public org.example.gestionale.GestionaleClienti creaClienti(String nome, String cognome, String azienda){
        org.example.gestionale.GestionaleClienti clienti = new org.example.gestionale.GestionaleClienti();
        clienti.nome = nome;
        clienti.cognome = cognome;
        clienti.nomeAzienda = azienda;
        return clienti;
    }
    static public ArrayList<org.example.gestionale.GestionaleClienti> caricaClienti() throws IOException {
        ArrayList<org.example.gestionale.GestionaleClienti> clienti = new ArrayList<>();
        FileReader fr = new FileReader("src/main/java/Module/clienti.cvs");
        BufferedReader br = new BufferedReader(fr);
        String riga = br.readLine();
        String[] dati = new String[0];
        if(riga != null) {
            dati = riga.split(";");
        }
        while (riga != null && dati.length == 3) {
            org.example.gestionale.GestionaleClienti cliente = new org.example.gestionale.GestionaleClienti();
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
    static public void salvaClienti(ArrayList<org.example.gestionale.GestionaleClienti> clienti) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Module/clienti.cvs");
        BufferedWriter bw = new BufferedWriter(file);
        for (int i = 0; i < clienti.size(); i++){
            bw.write(clienti.get(i).nome + ";" + clienti.get(i).cognome + ";" + clienti.get(i).nomeAzienda);
            if (clienti.get(i).nomeAzienda == null){
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

