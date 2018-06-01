import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
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


/*
 * Classe LevelDrop per creare un pop up con menu a tendina
 *  per scegliere il livello di difficoltà
 * */
public class LevelDrop extends StackPane
{
	Button button = new Button("Click");
	String level;
	int choising;
	
	public void display()
	{
		Stage window = new Stage();
		
		ChoiceBox<String> choiceLevel = new ChoiceBox<>();
		choiceLevel.getItems().addAll("Level 1", "Level 2", 
				"Level 3");

		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinHeight(200);
		window.setMinWidth(380);
		window.setResizable(false);
		window.setTitle("Maze Game");
		
		setAlignment(Pos.CENTER);
		
		button.setOnAction(e->
		{
			level = getChoice(choiceLevel);
			if(level.equals("Level 1"))
			{
				choising = 1;
			}
			
			if(level.equals("Level 2"))
			{
				choising = 2;
			}
			
			if(level.equals("Level 3"))
			{
				choising = 3;
			}
		});

		button.setOnMouseClicked(e->{
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(choiceLevel, button);

		Scene scene = new Scene(layout, 100 , 100);
		window.setScene(scene);
		window.showAndWait();
	}

	private String getChoice(ChoiceBox<String> choiceLevel)
	{
		String level = choiceLevel.getValue();
		return level;
	}
	
	public String takeLevel()
	{
		return level;
	}
}
