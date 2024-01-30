package org.example.gestionale;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GestionaleController {
    public javafx.scene.control.DatePicker DatePicker;
    public RadioButton rbMaschio;
    public RadioButton rbFemmina;
    public ToggleGroup Sesso;
    public CheckBox chkTypeClienti;
    public TextField txtNomeCliente;
    public TextField txtCognomeCliente;
    public TextField txtNomeAziendaCliente;
    public TextField txtNomeFornitore;
    public TextField txtCognomeFornitore;
    public TextField txtNomeAziendaFornitore;
    public CheckBox ChkTypeFornitori;
    public TextField txtFornitorePagamento;
    public SearchableComboBox ListaFornitori;
    public SearchableComboBox ListaClienti;
    public TextField txtFatturaClienti;
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
    private int indexD = -1;
    private int indexF = -1;
    private int indexC = -1;
    private Dipendenti Gestionale;
    private String sesso;
    private ArrayList<Dipendenti> dipendenti = new ArrayList<>();
    private ArrayList<GestionaleFornitori> fornitori = new ArrayList<>();
    private ArrayList<Transazioni> transazioni = new ArrayList<>();
    private ArrayList<GestionaleClienti> clienti = new ArrayList<>();

    @FXML
    void initialize() throws IOException {
        txtNome.setPromptText("Nome");
        txtCognome.setPromptText("Cognome");
        DatePicker.setPromptText("Data");

        txtNomeCliente.setPromptText("Nome");
        txtCognomeCliente.setPromptText("Cognome");
        txtNomeAziendaCliente.setPromptText("Nome Azienda");
        Gestionale = new Dipendenti();
        dipendenti.addAll(Dipendenti.caricaDipedenti());
        for (int i = 0; i < dipendenti.size(); i++){
            ListaDipendenti.getItems().add(dipendenti.get(i).getNome() + " " + dipendenti.get(i).getCognome()); // Aggiunge al CheckBox
        }
        fornitori.addAll(GestionaleFornitori.caricaFornitori());
        for (int i = 0; i < fornitori.size() ; i++) {
            if(fornitori.get(i).getNomeAzienda().equals(" "))
                ListaFornitori.getItems().add(fornitori.get(i).getNome() + " " + fornitori.get(i).getCognome());
            else
                ListaFornitori.getItems().add(fornitori.get(i).getNomeAzienda());
        }

        clienti.addAll(GestionaleClienti.caricaClienti());
        for (int i = 0; i < clienti.size() ; i++) {
            if(clienti.get(i).getNomeAzienda().equals(" "))
                ListaClienti.getItems().add(clienti.get(i).getNome() + " " + clienti.get(i).getCognome());
            else
                ListaClienti.getItems().add(clienti.get(i).getNomeAzienda());
        }
        transazioni.addAll(Transazioni.caricaTransazioni());
        System.out.println(dipendenti);
        txtNomeFornitore.setPromptText("Nome");
        txtCognomeFornitore.setPromptText("Cognome");
        txtNomeAziendaFornitore.setPromptText("Nome Azienda");
        txtFornitorePagamento.setPromptText("Importo");

    }
    public void onButtonCreaDipendente(ActionEvent event) throws IOException {
        if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || DatePicker.getValue() == null || (!rbFemmina.isSelected() && !rbMaschio.isSelected())){
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
            DatePicker.setValue(null);
            rbMaschio.setSelected(false);
            rbFemmina.setSelected(false);
        }
    }
    public void onButtonModificaDipendente(ActionEvent actionEvent) throws IOException {
        Dipendenti dipendente = null;
        if (indexD != -1) {
            dipendente = dipendenti.get(indexD);
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
        dipendenti.get(indexD).modificaDipendente(txtModificaNome.getText(),txtModificaCognome.getText(),sesso,txtModificaData.getText());
        Dipendenti.salvaDipendenti(dipendenti);
        ListaDipendenti.getSelectionModel().select(-1);
        indexD = -1;
        txtModificaNome.clear();
        txtModificaCognome.clear();
        txtModificaData.clear();
        rbModificaMaschio.setSelected(false);
        rbModificaFemmina.setSelected(false);
        sessoDipendente.setText("-");
        /*
        txtModificaNome.setText(dipendente.getNome());
        txtModificaCognome.setText(dipendente.getCognome());
        txtModificaData.setText(dipendente.getData());
        sessoDipendente.setText(dipendente.getSesso());
        */
    }
    public void onButtonRimuoviDipendente(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (indexD != -1) {
            Dipendenti dipendente = dipendenti.get(indexD);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Conferma rimozione");
            if (dipendente.getSesso().equals("Maschio")) {
                alert.setContentText("Rimuovere il dipendente " + dipendente.getNome() + " " + dipendente.getCognome() + "?");
            } else if (dipendente.getSesso().equals("Femmina")) {
                alert.setContentText("Rimuovere la dipendente " + dipendente.getNome() + " " + dipendente.getCognome() + "?");
            }
            Optional<ButtonType> result = alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                dipendenti.remove(indexD);
                ListaDipendenti.getItems().remove(indexD);
                Dipendenti.salvaDipendenti(dipendenti);
                ListaDipendenti.getSelectionModel().select(-1);
                indexD = -1;
                txtModificaNome.clear();
                txtModificaCognome.clear();
                txtModificaData.clear();
                rbModificaMaschio.setSelected(false);
                rbModificaFemmina.setSelected(false);
                sessoDipendente.setText("-");
            }
        }
    }
    public void onListaDipendentiAction(ActionEvent actionEvent) {
        indexD = ListaDipendenti.getSelectionModel().getSelectedIndex();
        Dipendenti dipendente = dipendenti.get(indexD);
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
        else if(!ChkTypeFornitori.isSelected() && txtNomeAziendaFornitore.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        }
        else{
            if(ChkTypeFornitori.isSelected()) {
                fornitori.add(GestionaleFornitori.creaFornitori(txtNomeFornitore.getText(), txtCognomeFornitore.getText()," "));
            }
            else {
                fornitori.add(GestionaleFornitori.creaFornitori(" ", " ",txtNomeAziendaFornitore.getText()));
            }
            if(!fornitori.isEmpty()) {
                if (fornitori.get(fornitori.size() - 1).getNomeAzienda().equals(" ")) {
                    ListaFornitori.getItems().add(fornitori.get(fornitori.size() - 1).getNome() + " " + fornitori.get(fornitori.size() - 1).getCognome());
                } else {
                    ListaFornitori.getItems().add(fornitori.get(fornitori.size() - 1).getNomeAzienda());
                }
            }
            GestionaleFornitori.salvaFornitori(fornitori);
            txtNomeFornitore.clear();
            txtCognomeFornitore.clear();
            txtNomeAziendaFornitore.clear();
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

    public void onListaFornitoriPaga(ActionEvent actionEvent) {
        indexF = ListaFornitori.getSelectionModel().getSelectedIndex();
        System.out.println(indexF);
    }
    public void onButtonPaga(ActionEvent event) throws IOException {
        if (txtFornitorePagamento.getText().equals("") || ListaFornitori.getSelectionModel().getSelectedIndex() == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Importo o fornitore mancante!.");
            alert.showAndWait();
        } else if (!Transazioni.isNumber(txtFornitorePagamento.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Importo inserito non valido!.");
            alert.showAndWait();
        } else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Conferma pagamento");
            alert.setContentText("Sei sicuro di volere pagare?");
            Optional<ButtonType> result = alert.showAndWait();
            //sostituire "fornitore"
            if (result.get() == ButtonType.OK) {
                transazioni.add(Transazioni.nuovaTransazione("-"+ txtFornitorePagamento.getText(), ListaFornitori.getSelectionModel().getSelectedItem().toString()));
                Transazioni.salvaTransazioni(transazioni);
                txtFornitorePagamento.clear();
                ListaFornitori.getSelectionModel().select(-1);
                indexF = -1;
                System.out.println(Transazioni.totaleConto());
            } else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Pagamento annullato");
                alert.setContentText("Il tuo pagamento è stato annullato!");
                alert.showAndWait();
            }
        }
    }

    public void onButtonCreaCliente(ActionEvent actionEvent) throws IOException {
        if (chkTypeClienti.isSelected() && (txtNomeCliente.getText().equals("") || txtCognomeCliente.getText().equals(""))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        }
        else if(!chkTypeClienti.isSelected() && txtNomeAziendaCliente.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Inserire tutti i campi richiesti.");
            alert.showAndWait();
        }
        else{
            if(chkTypeClienti.isSelected()) {
                clienti.add(GestionaleClienti.creaClienti(txtNomeCliente.getText(), txtCognomeCliente.getText()," "));
            }
            else {
                clienti.add(GestionaleClienti.creaClienti(" ", " ",txtNomeAziendaCliente.getText()));
            }
            if(!clienti.isEmpty()) {
                if (clienti.get(clienti.size() - 1).getNomeAzienda().equals(" ")) {
                    ListaClienti.getItems().add(clienti.get(clienti.size() - 1).getNome() + " " + clienti.get(clienti.size() - 1).getCognome());
                } else {
                    ListaClienti.getItems().add(clienti.get(clienti.size() - 1).getNomeAzienda());
                }
            }
            GestionaleClienti.salvaClienti(clienti);
            txtNomeCliente.clear();
            txtCognomeCliente.clear();
            txtNomeAziendaCliente.clear();
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


    public void onListaClientiPaga(ActionEvent actionEvent) {
        indexC = ListaClienti.getSelectionModel().getSelectedIndex();
        System.out.println(indexC);
    }

    public void onButtonFattura(ActionEvent actionEvent) throws IOException {
        if (txtFatturaClienti.getText().equals("") || ListaClienti.getSelectionModel().getSelectedIndex() == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Importo o cliente mancante!.");
            alert.showAndWait();
        } else if (!Transazioni.isNumber(txtFatturaClienti.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("ERRORE D'INSERIMENTO");
            alert.setContentText("Importo inserito non valido!.");
            alert.showAndWait();
        } else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Conferma pagamento");
            alert.setContentText("Sei sicuro di volere inviare la fattura?");
            Optional<ButtonType> result = alert.showAndWait();
            //sostituire "fornitore"
            if (result.get() == ButtonType.OK) {
                transazioni.add(Transazioni.nuovaTransazione("+"+ txtFatturaClienti.getText(), ListaClienti.getSelectionModel().getSelectedItem().toString()));
                Transazioni.salvaTransazioni(transazioni);
                txtFatturaClienti.clear();
                ListaClienti.getSelectionModel().select(-1);
                indexC = -1;
                System.out.println(Transazioni.totaleConto());
            } else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText("Pagamento annullato");
                alert.setContentText("Il tuo pagamento è stato annullato!");
                alert.showAndWait();
            }
        }
    }
}