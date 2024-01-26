package org.example.gestionale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.controlsfx.control.SearchableComboBox;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;

public class GestionaleController {
    public javafx.scene.control.DatePicker DatePicker;
    public RadioButton rbMaschio;
    public RadioButton rbFemmina;
    public ToggleGroup Sesso;
    public TextField txtNomeFornitore;
    public TextField txtCognomeFornitore;
    public TextField txtNomeAziendaFornitore;
    public CheckBox ChkTypeFornitori;
    @FXML
    private SearchableComboBox ListaDipendenti;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtModificaNome, txtModificaCognome, txtModificaData;
    @FXML
    private RadioButton rbModificaMaschio, rbModificaFemmina;
    @FXML
    private Label sessoDipendente;
    private int index = -1;
    private Dipendenti Gestionale;
    private String sesso;
    private ArrayList<Dipendenti> dipendenti = new ArrayList<>();
    private GestionaleFornitori Fornitori;


    @FXML
    void initialize() throws IOException {
        txtNome.setPromptText("Nome");
        txtCognome.setPromptText("Cognome");
        DatePicker.setPromptText("Data");
        Gestionale = new Dipendenti();
        dipendenti.addAll(Dipendenti.caricaDipedenti());
        for (int i = 0; i < dipendenti.size(); i++){
            ListaDipendenti.getItems().add(dipendenti.get(i).getNome() + " " + dipendenti.get(i).getCognome()); // Aggiunge al CheckBox
        }
        System.out.println(dipendenti);

        Fornitori = new GestionaleFornitori();
        ListaDipendenti.getItems().add("Mattia Montini");
        txtNomeFornitore.setPromptText("Nome");
        txtCognomeFornitore.setPromptText("Cognome");
        txtNomeAziendaFornitore.setPromptText("Nome Azienda");

    }
    public void onButtonCreaDipendente(ActionEvent event) throws IOException {
        if (txtModificaNome.getText().isEmpty() || txtCognome.getText().isEmpty() || DatePicker.getValue() == null || (!rbFemmina.isSelected() && !rbMaschio.isSelected())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        } else {
            if(rbMaschio.isSelected())
                sesso = "Maschio";
            if(rbFemmina.isSelected())
                sesso = "Femmina";
            dipendenti.add(Dipendenti.creaDipendente(txtNome.getText(), txtCognome.getText(),sesso, String.valueOf(DatePicker.getValue())));
            ListaDipendenti.getItems().add(dipendenti.get(dipendenti.size()-1).getNome() + " " + dipendenti.get(dipendenti.size()-1).getCognome());
            Dipendenti.salvaDipendenti(dipendenti);
            txtNome.clear();
            txtCognome.clear();
            rbMaschio.setSelected(false);
            rbFemmina.setSelected(false);
        }
    }
    public void onButtonModificaDipendente(ActionEvent actionEvent) throws IOException {
        Dipendenti dipendente = null;
        if (index != -1) {
            dipendente = dipendenti.get(index);
        }
        if (txtModificaNome.getText().isEmpty() || txtModificaCognome.getText().isEmpty() || txtModificaData.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        } else {
            if (rbModificaMaschio.isSelected())
                sesso = "Maschio";
            else if (rbModificaFemmina.isSelected())
                sesso = "Femmina";
            else
                sesso = dipendente.getSesso();
        }
        dipendenti.get(index).modificaDipendente(txtModificaNome.getText(),txtModificaCognome.getText(),sesso,txtModificaData.getText());
        Dipendenti.salvaDipendenti(dipendenti);
        txtModificaNome.setText(dipendente.getNome());
        txtModificaCognome.setText(dipendente.getCognome());
        txtModificaData.setText(dipendente.getData());
        sessoDipendente.setText(dipendente.getSesso());
    }
    public void onListaDipendentiAction(ActionEvent actionEvent) {
        index = ListaDipendenti.getSelectionModel().getSelectedIndex();
        Dipendenti dipendente = dipendenti.get(index);
        txtModificaNome.setText(dipendente.getNome());
        txtModificaCognome.setText(dipendente.getCognome());
        txtModificaData.setText(dipendente.getData());
        sessoDipendente.setText(dipendente.getSesso());
    }


    void shutdown() throws IOException {
        Dipendenti.salvaDipendenti(dipendenti);
    }




    public void onButtonCreaFornitore(ActionEvent event) throws IOException {
        if (ChkTypeFornitori.isSelected() && (txtNomeFornitore.getText().equals("") || txtCognomeFornitore.getText().equals(""))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        }
        else if(!ChkTypeFornitori.isSelected() &&txtNomeAziendaFornitore.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        }
        else{
            if(ChkTypeFornitori.isSelected()) {
                Fornitori.creaFornitori(txtNomeFornitore.getText(), txtCognomeFornitore.getText(), " ");
                Fornitori.salvaFornitori();
            }
            else {
                Fornitori.creaFornitori("", "", txtNomeAziendaFornitore.getText());
                Fornitori.salvaFornitori();
            }

        }

    }
    public void CheckPersona(ActionEvent event) {
        if (ChkTypeFornitori.isSelected()){
            txtNomeAziendaFornitore.setDisable(true);
            txtCognomeFornitore.setDisable(false);
            txtNomeFornitore.setDisable(false);
            txtNomeAziendaFornitore.setText("");
        }
        else{
            txtNomeAziendaFornitore.setDisable(false);
            txtCognomeFornitore.setDisable(true);
            txtNomeFornitore.setDisable(true);
            txtNomeFornitore.setText("");
            txtCognomeFornitore.setText("");
        }

    }
}