package org.example.gestionale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GestionaleController {
    public javafx.scene.control.DatePicker DatePicker;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;

    @FXML
    void initialize() {
        txtNome.setPromptText("Nome");
        txtCognome.setPromptText("Cognome");
    }

    public void onButtonCreaDipendente(ActionEvent event) {
    }
}