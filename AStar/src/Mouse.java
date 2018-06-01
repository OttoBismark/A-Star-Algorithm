
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/*
 * Progetto Programmazione 3
 * 
 * Anno Accademico 2017-2018
 * 
 * a cura di Pauciullo Valerio
 * matricola 0124001023
 * 
 * */


/*Classe Mouse per creare il controller di gioco*/
public class Mouse implements MouseListener, MouseMotionListener
{

	private static boolean buttons[] = new boolean[5];
	private static int x, y;

	/*Costruttore privato della classe Mouse*/
	private Mouse()
	{}

	public static int getX()
	{
		return x;
	}

	public static int getY()
	{
		return y;
	}

	public static boolean isButtonDown(int button)
	{
		if (button >= 0 && button < buttons.length)
		{
			return buttons[button];
		}
		return false;
	}


	/*Desing Pattern Singleton*/
	private static Mouse instance = null;

	public static Mouse instance()
	{
		if (instance == null)
			instance = new Mouse();
		return instance;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		buttons[e.getButton()] = true;
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{}

	@Override
	public void mouseEntered(MouseEvent e)
	{}

	@Override
	public void mouseExited(MouseEvent e)
	{}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int button = e.getButton();
		if (button >= 0 && button < buttons.length)
		{
			buttons[button] = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		int button = e.getButton();
		if (button >= 0 && button < buttons.length)
		{
			buttons[button] = false;
		}
	}

}
