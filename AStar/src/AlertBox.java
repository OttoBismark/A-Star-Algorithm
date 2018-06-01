import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * Progetto Programmazione 3
 * 
 * Anno Accademico 2017-2018
 * 
 * a cura di Pauciullo Valerio
 * matricola 0124001023
 * 
 * */

/*Classe AlertBox
 * 
 * La classe AlertBox è una classe che ci permette di costruire un pop-up a display
 * in modo da riceve un messaggio mostrato a video all'interno di una finestra 
 * per segnalare l'errore
 * 
 * */
public class AlertBox
{
	/*Metodo display della classe AlertBox*/
	public void display(String message)
	{
		Stage window = new Stage();
		
		/*
		 * Inizializzazione per l'applicazione modale, in modo da non 
		 * interferire con le due finestre
		 * */
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Alert : Error Message");
		
		window.setMinHeight(200);
		window.setMinWidth(350);
		
		Label label = new Label();
		
		label.setText(message);
		Button closeButton = new Button("Go back");
		closeButton.setPrefSize(100, 50);
		
		/*
		 * Espressione Lambda: in caso di azione del button, verrà
		 * chiusa la finestra di dialogo
		 * */
		closeButton.setOnAction(e->window.close());
		
		VBox layout = new VBox(20);
		
		layout.getChildren().addAll(label, closeButton);
		
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
		
		window.showAndWait();
	}
}
