package org.example.gestionale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class GestionaleController {
    public javafx.scene.control.DatePicker DatePicker;
    public RadioButton rbMaschio;
    public RadioButton rbFemmina;
    public ToggleGroup Sesso;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;
    

    @FXML
    void initialize() {
        txtNome.setPromptText("Nome");
        txtCognome.setPromptText("Cognome");
        DatePicker.setPromptText("Data");
    }

    public void onButtonCreaDipendente(ActionEvent event) {
        if (txtNome.getText().equals("") || txtCognome.getText().equals("") || DatePicker.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        }


    }

    public void onButtonMostraDati(ActionEvent event) {
    }
}