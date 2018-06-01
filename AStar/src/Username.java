import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.*;
import java.util.List;

/*
 * Progetto Programmazione 3
 * 
 * Anno Accademico 2017-2018
 * 
 * a cura di Pauciullo Valerio
 * matricola 0124001023
 * 
 * */


/*Classe Username per creare l'istanza del giocatore*/
public class Username
{
	private static Username username = null;
	private static String user;

	private Username(){}

	/*
	 * Metodo per effettuare il login del giocatore da registrare.
	 * Per effettuare la registrazione del giocatore è stato
	 * utilizzato il design pattern Singleton per creare una e
	 * solo una istanza del giocatore da registrare
	 * 
	 * */
	public static Username login()
	{	
		Stage window = new Stage();
		Label label = new Label();

		if(username == null)
		{
			username = new Username();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("User Log");
			
			window.setMinHeight(200);
			window.setMinWidth(350);

			label.setText("Username create successfully");
			
			Button closeButton = new Button("Go play");
			closeButton.setPrefSize(100, 50);
			closeButton.setOnAction(e->window.close());

			VBox layout = new VBox(20);
			layout.getChildren().addAll(label, closeButton);
			layout.setAlignment(Pos.CENTER);
			
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();

		}
		return username;
	}
	
	/*Metodo per inserire l'username del giocatore nel database*/
	public void insertUsernameInto(String username)
	{
		
		user = username;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		/*
		 * Connesione al database
		 * Inserimento username e stampa temporanea 
		 * dell'attuale situazione del database
		 * */
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game", "username", "password");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from gamescore");
			String insertion = "insert into gamescore values ('" + username + "', '0')";
			stat.executeUpdate(insertion);
			rs = stat.executeQuery("select * from gamescore order by totalScore asc"); 

			mostraRes(rs);


		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/*Metodo privato per mostrare in console i risulati attuali*/
	private static void mostraRes(ResultSet rs)
	{
		int i;
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();
			for(i = 1; i <= columns; i++)
			{
				System.out.print(meta.getColumnLabel(i));
				System.out.print(", ");
			}
			
			System.out.println(" ");
			System.out.println("----------------------------------------");
			
			while(rs.next())
			{
				for(i = 1; i <= columns; i++)
				{
					System.out.print(rs.getString(i) + ", ");
				}
				System.out.println("\n");
			}
			System.out.println("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Metodo per aggiornare il punteggio alla fine della partita*/
	public void updateScore(List<Tile>path)
	{
		int size = path.size();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/game", "username", "password");
			Statement stat = conn.createStatement();
			String insertion = "update gamescore set totalScore=" + size + " where username='" + user + "'";
			stat.executeUpdate(insertion);
			ResultSet rs = stat.executeQuery("select * from gamescore order by totalScore asc");
			showScore(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/*Metodo privato per mostrare in console i risulati finali a gioco concluso*/
	private void showScore(ResultSet rs)
	{
		int i;
		try {
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();
			for(i = 1; i <= columns; i++)
			{
				System.out.print(meta.getColumnLabel(i));
				System.out.print(", ");
			}
			
			System.out.println(" ");
			System.out.println("----------------------------------------");
			
			while(rs.next())
			{
				for(i = 1; i <= columns; i++)
				{
					System.out.print(rs.getString(i) + ", ");
				}
				System.out.println("\n");
			}
			System.out.println("");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}