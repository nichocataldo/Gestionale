package org.example.gestionale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionaleController {
    public javafx.scene.control.DatePicker DatePicker;
    public RadioButton rbMaschio;
    public RadioButton rbFemmina;
    public ToggleGroup Sesso;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;
    private GestionaleDipendenti Gestionale;
    private String sesso;
    

    @FXML
    void initialize() {
        txtNome.setPromptText("Nome");
        txtCognome.setPromptText("Cognome");
        DatePicker.setPromptText("Data");
        Gestionale = new GestionaleDipendenti();
    }

    public void onButtonCreaDipendente(ActionEvent event) throws IOException {
        if (txtNome.getText().equals("") || txtCognome.getText().equals("") || DatePicker.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        }
        if(rbMaschio.isSelected())
            sesso = "Maschio";
        if(rbFemmina.isSelected())
            sesso = "Femmina";
        Gestionale.creaDipendente(txtNome.getText(), txtCognome.getText(),sesso, DatePicker.toString());
        Gestionale.salvaDipendenti();

    }
    public void onButtonMostraDati(ActionEvent event) {
    }
}