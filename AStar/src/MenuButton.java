import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/*
 * Progetto Programmazione 3
 * 
 * Anno Accademico 2017-2018
 * 
 * a cura di Pauciullo Valerio
 * matricola 0124001023
 * 
 * */

/*Classe MenuButton per creare un bottone ad hoc per la schermata di login*/
public class MenuButton extends StackPane
{
	private Text text;

	public MenuButton(String name)
	{
		text = new Text(name);
		text.getFont();
		text.setFont(Font.font(20));
		text.setFill(Color.WHITE);

		Rectangle bg = new Rectangle(100, 20);
		bg.setOpacity(0.6);
		bg.setFill(Color.BLACK);

		GaussianBlur blur = new GaussianBlur(3.5);
		bg.setEffect(blur);
		setAlignment(Pos.CENTER);

		DropShadow drop = new DropShadow(50, Color.WHITE);
		drop.setInput(new Glow());
		setOnMousePressed(e->
		{
			setEffect(drop);
		});
		
		setOnMouseReleased(e->
		{
			setEffect(null);
		});

		getChildren().addAll(bg, text);
	}
}
