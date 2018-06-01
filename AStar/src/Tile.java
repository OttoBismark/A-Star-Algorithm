/*
 * Progetto Programmazione 3
 * 
 * Anno Accademico 2017-2018
 * 
 * a cura di Pauciullo Valerio
 * matricola 0124001023
 * 
 * */

public class Tile
{

	/*Costante che determina il costo ortogonale di movimento da un nodo all'altro*/
	protected static final int MOVEMENT_COST = 10;


	/*Posizione in coordinate X del Tile*/
	private int x;


	/*Posizione in coordinate X del Tile*/
	private int y;
	
	/*Variabile booleana che setta se quel Tile è percorribile o no*/
	private boolean walkable;


	/*E' usata per assegnare il nodo padre del tile corrente nel path*/
	private Tile parent;

	/*Costo g che valuta il valore del nodo corrente al nodo start*/
	private int g;


	/*Variabile che conserva il valore della funzione euristica, calcolata come:
	 * 
	 * f = g + h
	 * 
	 */
	
	private int h;

	
	/*Costruttore della classe Tile*/
	public Tile(int x, int y, boolean walkable)
	{
		this.x = x;
		this.y = y;
		this.walkable = walkable;
	}


	public void setG(Tile parent)
	{
		g = (parent.getG() + MOVEMENT_COST);
	}

	
	public int calculateG(Tile parent)
	{
		return (parent.getG() + MOVEMENT_COST);
	}

	/*Calcolo della funzione euristica */
	public void setH(Tile goal)
	{
		h = (Math.abs(getX() - goal.getX()) + Math.abs(getY() - goal.getY())) * MOVEMENT_COST;
	}
	
	/*Ritorna il valore X del nodo sulla mappa*/
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}

	
	/*Ritorna il valore Y del nodo sulla mappa*/
	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}


	/*Ritorna true se il nodo non è un muro, falso altrimento*/
	public boolean isWalkable()
	{
		return walkable;
	}


	public void setWalkable(boolean walkable)
	{
		this.walkable = walkable;
	}
	

	/*Ritorna il nodo opadre del nodo corrente*/
	public Tile getParent()
	{
		return parent;
	}

	
	/*Setta il nodo padre del nodo corrente*/
	public void setParent(Tile parent)
	{
		this.parent = parent;
	}
	
	/*Ritorna il valore della funzione f = g + h*/
	public int getF()
	{
		return g + h;
	}


	public int getG()
	{
		return g;
	}


	public int getH()
	{
		return h;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null)
			return false;
		if (!(o instanceof Tile))
			return false;
		if (o == this)
			return true;

		Tile n = (Tile) o;
		if (n.getX() == x && n.getY() == y && n.isWalkable() == walkable)
			return true;
		return false;
	}

}
