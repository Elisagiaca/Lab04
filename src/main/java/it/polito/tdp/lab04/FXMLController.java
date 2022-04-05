/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscritti;
    
    @FXML
    private Button btnCerca;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmbCorso;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	txtArea.clear();
    	String mat = txtMatricola.getText();
    	int matricola = Integer.parseInt(mat);
    	
    	if (this.model.getCorsiModel(matricola).size()==0) {
    		txtArea.setText("Errore. Matricola non esistente nel database.");
    	}
    	for (Corso c : this.model.getCorsiModel(matricola)) {
    		txtArea.appendText(c.toString() + "\n");
    	}
    	
    	
    }

    @FXML
    void handleCercaIscritti(ActionEvent event) {
    	txtArea.clear();
    	
    	String[] vett = this.cmbCorso.getValue().split(" ");
    	String codice = vett[vett.length-1];
    	
    	if(this.model.getStudentiModel(codice).size()==0) {
    		txtArea.setText("Errore. Inserisci un corso.");
    	}
   
    	
    	for(Studente s : this.model.getStudentiModel(codice)) {
    		txtArea.appendText(s.toString()+"\n");
    	}
    	
    	
    }

    @FXML
    void handleCheck(ActionEvent event) {
    	txtNome.setEditable(true);
    	txtCognome.setEditable(true);
    	String matricola = txtMatricola.getText();
    	Integer matr = Integer.parseInt(matricola);
    	Studente s = this.model.getStudenteModel(matr);
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    	txtNome.setEditable(false);
    	txtCognome.setEditable(false);
    }

    @FXML
    void handleIscrivi(ActionEvent event) {

    }
    
    @FXML
    void handleReset(ActionEvent event) {

    }

    public void setModel(Model m) {
    	this.model = m;
    	 cmbCorso.getItems().clear();
         for (Corso c : model.getCorsi()) {
         	cmbCorso.getItems().add(c.getNome()+" "+ c.getCodins());
          }
         cmbCorso.getItems().add("");
    }
    
    @FXML
    void handleCerca(ActionEvent event) {
    	txtArea.clear();
    	String stringaBox = cmbCorso.getValue();
    	String[] vett = stringaBox.split(" ");
    	String codiceCorso = vett[vett.length-1];
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	boolean trovato = false;
    
    	if (this.model.getCorsiModel(matricola).size()==0) {
    		txtArea.setText("Errore. Matricola non esistente nel database.");
    		return;
    	}
    	
    	for (Corso c : this.model.getCorsiModel(matricola)) {
    		if (c.getCodins().compareTo(codiceCorso)==0) {
    			txtArea.setText("Studente iscritto a questo corso.");
    			trovato=true;
    			break;
    		}
    	}
    	if (trovato==false) {
    		txtArea.setText("Studente non iscritto a questo corso.");
    	}
    
    	
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorso != null : "fx:id=\"cmbCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";

        
      
    }
    
}
