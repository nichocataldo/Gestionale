package org.example.gestionale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionaleController {
    public javafx.scene.control.DatePicker DatePicker;
    public RadioButton rbMaschio;
    public RadioButton rbFemmina;
    public ToggleGroup Sesso;
    public CheckBox chkTypeClienti;
    public TextField txtNomeCliente;
    public TextField txtCognomeCliente;
    public TextField txtNomeAziendaCliente;
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
        txtNomeCliente.setPromptText("Nome");
        txtCognomeCliente.setPromptText("Cognome");
        txtNomeAziendaCliente.setPromptText("Nome Azienda");


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
        Gestionale.creaDipendente(txtNome.getText(), txtCognome.getText(),sesso, String.valueOf(DatePicker.getValue()));
        Gestionale.salvaDipendenti();

    }
    public void onButtonMostraDati(ActionEvent event) {
    }

    public void onButtonCreaCliente(ActionEvent actionEvent) {
        if (chkTypeClienti.isSelected() && (txtNomeCliente.getText().equals("") || txtCognomeCliente.getText().equals(""))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        }
        else if(!chkTypeClienti.isSelected() &&txtNomeAziendaCliente.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        }

    }

    public void checkCliente(ActionEvent actionEvent) {
        if (chkTypeClienti.isSelected()){
            txtNomeCliente.setDisable(false);
            txtCognomeCliente.setDisable(false);
            txtNomeAziendaCliente.setDisable(true);
            txtNomeAziendaCliente.setText("");
        }else{
            txtNomeCliente.setDisable(true);
            txtCognomeCliente.setDisable(true);
            txtNomeAziendaCliente.setDisable(false);
            txtNomeCliente.setText("");
            txtCognomeCliente.setText("");

        }
    }


}