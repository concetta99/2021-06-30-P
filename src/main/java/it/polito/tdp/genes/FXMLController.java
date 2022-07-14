package it.polito.tdp.genes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStatistiche;

    @FXML
    private Button btnRicerca;

    @FXML
    private ComboBox<String> boxLocalizzazione;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRicerca(ActionEvent event) {
    	
    	
    	

    }

    @FXML
    void doStatistiche(ActionEvent event) {
    	
    	txtResult.clear();
    	//boxLocalizzazione.getItems().clear();
    	//boxLocalizzazione.getItems().addAll(model.getLocalizzazioni());

    	String localizzazione = boxLocalizzazione.getValue();
    	if(localizzazione.compareTo("")==0)
    	{
    		model.creaGrafo();
    		txtResult.appendText("Grafo creato!\n");
    		txtResult.appendText("VERTICI: " + model.nVertivi()+"\n");
    		txtResult.appendText("ARCHI: " +model.nArchi());
    	}
    	else {
    		model.creaGrafo();
    		for (Adiacenza a: model.getComponenteConnessa(localizzazione))
    		{
    				if(a.getL1().compareTo(localizzazione)==0 )
    				{
    					txtResult.appendText(a.getL2()+"    " + a.getPeso()+ "\n");
    				}
    				else if(a.getL2().compareTo(localizzazione)==0)
    				{
    					txtResult.appendText(a.getL1()+"    " + a.getPeso()+ "\n");
    				}
    			}
    		
    	}

    }

    @FXML
    void initialize() {
        assert btnStatistiche != null : "fx:id=\"btnStatistiche\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLocalizzazione != null : "fx:id=\"boxLocalizzazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
		boxLocalizzazione.getItems().addAll(model.getLocalizzazioni());
	}
}
