package org.example.gestionale;

import java.io.BufferedReader;
import java.util.Scanner;

public class GestionaleDipendenti {
    private String nome;
    private String cognome;
    private int gg, mm, anno;
    private String data;
    private String sesso;

    /**
     * Metodo che inizializza i dipendenti.
     */
    public GestionaleDipendenti(){};

    public void creaDipendente(String no, String co, String se, String da){
        this.nome = no;
        this.cognome = co;
        this.sesso = se;
        this.data = da;
    }

    public void modificaDipendente(String no, String co, String se, String da){
        this.nome = no;
        this.cognome = co;
        this.sesso = se;
        this.data = da;
    }

    public void caricaDipedente(){

    }

    public void salvaDipendente(){

    }
}
