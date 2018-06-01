import java.util.LinkedList;
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

/*
 * Classe Player per definire il giocatore che attraverserà
 * 
 * */
public class Player
{

	private int x;
	private int y;
	private int sx;
	private int sy;

	private int speed;

	private boolean walking;
	private boolean fixing;
	private List<Tile> path;

	/*Costruttore della classe Player*/
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
		sx = 0;
		sy = 0;
		speed = 2;

		walking = false;
		fixing = false;
		path = null;
	}

	/*Metodo update per l'aggiornamento dei valori nel rendering del gioco*/
	public void update()
	{
		if (fixing)
		{
			fix();
		}
		if (walking)
		{
  			walk();
		}
	}

	/*
	 * Metodo per l'inseguimento del path calcolato attraverso
	 * l'algoritmo A*
	 * */
	public void followPath(List<Tile> path)
	{
		this.path = path;
		if (walking)
		{
			fixing = true;
			walking = false;
		}
		else
		{
			walking = true;
		}
	}

	private void fix()
	{
		if (sx > 0)
		{
			sx -= speed;
			if (sx < 0)
			{
				sx = 0;
			}
		}
		if (sx < 0)
		{
			sx += speed;
			if (sx > 0)
			{
				sx = 0;
			}
		}
		if (sy > 0)
		{
			sy -= speed;
			if (sy < 0)
			{
				sy = 0;
			}
		}
		if (sy < 0)
		{
			sy += speed;
			if (sy > 0)
			{
				sy = 0;
			}
		}
		if (sx == 0 && sy == 0)
		{
			fixing = false;
			walking = true;
		}
	}
	
	private void walk()
	{
		if (path == null)
		{
			walking = false;
			return;
		}
		if (path.size() <= 0)
		{
			walking = false;
			path = null;
			return;
		}
		Tile next = ((LinkedList<Tile>) path).getFirst();
		if (next.getX() != x)
		{
			sx += (next.getX() < x ? -speed : speed);
			if (sx % 32 == 0)
			{
				((LinkedList<Tile>) path).removeFirst();
				if (sx > 0)
					x++;
				else
					x--;
				sx %= 32;
			}
		}
		else if (next.getY() != y)
		{
			sy += (next.getY() < y ? -speed : speed);
			if (sy % 32 == 0)
			{
				((LinkedList<Tile>) path).removeFirst();
				if (sy > 0)
					y++;
				else
					y--;
				sy %= 32;
			}
		}
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getSx()
	{
		return sx;
	}

	public void setSx(int sx)
	{
		this.sx = sx;
	}

	public int getSy()
	{
		return sy;
	}

	public void setSy(int sy)
	{
		this.sy = sy;
	}
}