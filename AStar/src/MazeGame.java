import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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

public class MazeGame extends Application
{
	Stage window;
	MenuButton button = new MenuButton("Login");
	TextField text = new TextField();
	LevelDrop level = new LevelDrop();
	String message;
	Username username;
	AlertBox alertBox = new AlertBox();
	private String difficulty;
	private int choising;
	private Template maze = new Maze();

	/*Main del programma*/
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	/*Launcher avviato dal main*/
	public void start(Stage primaryStage) throws Exception
	{
		window = primaryStage;
		window.setTitle("Maze Game");
		window.setResizable(false);

		Image img = new Image("machinarium.jpg");
		ImageView iv = new ImageView(img);

		Pane layout = new Pane();

		button.setTranslateX(300);
		button.setTranslateY(250);

		text.setTranslateX(280);
		text.setTranslateY(120);
		text.setText(null);

		button.setOnMousePressed(e->
		{
			if(text.getText() == null)
			{
				alertBox.display("Insert a username");
			}
			else
			{				
				Username username = Username.login();
				username.insertUsernameInto(text.getText());

				level.display();
				difficulty = level.takeLevel();

				if(difficulty.equals("Level 1"))
				{
					choising = 1;
				}

				if(difficulty.equals("Level 2"))
				{
					choising = 2;
				}

				if(difficulty.equals("Level 3"))
				{
					choising = 3;
				}
				window.close();
				/*Lancio della collezione del gioco*/
				maze.go();
				/*
				 * Entry point del metodo play con passaggio del livello di
				 * difficoltà.
				 * */
				maze.play(choising);
			}
		});

		layout.getChildren().addAll(iv, button, text);

		Scene scene = new Scene(layout,750, 500);

		window.setScene(scene);
		window.show();
	}
}