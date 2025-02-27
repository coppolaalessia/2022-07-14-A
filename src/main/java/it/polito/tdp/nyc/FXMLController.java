package it.polito.tdp.nyc;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.nyc.model.Collegati;
import it.polito.tdp.nyc.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController {

	Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="clPeso"
    private TableColumn<Collegati, Integer> clPeso; // Value injected by FXMLLoader

    @FXML // fx:id="clV1"
    private TableColumn<Collegati, String> clV1; // Value injected by FXMLLoader

    @FXML // fx:id="clV2"
    private TableColumn<Collegati, String> clV2; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBorough"
    private ComboBox<String> cmbBorough; // Value injected by FXMLLoader

    @FXML // fx:id="tblArchi"
    private TableView<Collegati> tblArchi; // Value injected by FXMLLoader

    @FXML // fx:id="txtDurata"
    private TextField txtDurata; // Value injected by FXMLLoader

    @FXML // fx:id="txtProb"
    private TextField txtProb; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doAnalisiArchi(ActionEvent event) {
    	String borgo = this.cmbBorough.getValue();
    	
    	if(borgo==null) {
    		txtResult.appendText("Errore: devi selezionare un borgo\n");
    		return;
    	}
    	
    	if(this.model.getVertici().size()==0) {
    		txtResult.appendText("Errore: devi creare il grafo\n");
    		return;
    	}
    	List<Collegati> archi = model.getVerticiPesoMaggioreDiN();
    	Collections.sort(archi);
    	
    	tblArchi.setItems(FXCollections.observableArrayList(archi));
    	this.txtResult.clear();
    	this.txtResult.appendText("Peso medio: "+ this.model.getPesoMedio()+"\n");
    	this.txtResult.appendText("Archi con peso maggiore del peso medio: "+ (this.model.getVerticiPesoMaggioreDiN().size()/2)+"\n");
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String borgo = this.cmbBorough.getValue();
    	
    	if(borgo==null) {
    		txtResult.appendText("Errore: devi selezionare un borgo\n");
    		return;
    	}
    	
    	String msg = model.creaGrafo(borgo);
    	txtResult.appendText(msg);
    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clPeso != null : "fx:id=\"clPeso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clV1 != null : "fx:id=\"clV1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clV2 != null : "fx:id=\"clV2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBorough != null : "fx:id=\"cmbBorough\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tblArchi != null : "fx:id=\"tblArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDurata != null : "fx:id=\"txtDurata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtProb != null : "fx:id=\"txtProb\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

        this.clV1.setCellValueFactory(new PropertyValueFactory<Collegati, String>("vertice1"));
		this.clV2.setCellValueFactory(new PropertyValueFactory<Collegati, String>("vertice2"));
        this.clPeso.setCellValueFactory(new PropertyValueFactory<Collegati, Integer>("peso"));
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbBorough.getItems().addAll(this.model.getBorghi());
    }

}
