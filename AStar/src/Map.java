import java.awt.Color;
import java.awt.Graphics;
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


public class Map
{

	/*Variabile per memorizzare la larghezza della mappa, in colonne*/
	private int width;


	/*Variabile per memorizzare la altezza della mappa, in righe*/

	private int height;


	/*Matrice di tile*/
	private Tile[][] nodes;
	
	
	/*
	 * Costruttore della classe Map che ci permettere di costruire una mappa a partire
	 * da un array 2D di interi, dove un valore zero significa "casella (o tile)
	 * percorribile"; invece il valore uno "casella non percorribile"
	 * */
	
	public Map(int[][] map)
	{
		this.width = map[0].length;
		this.height = map.length;
		nodes = new Tile[width][height];

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				nodes[x][y] = new Tile(x, y, map[y][x] == 0);
			}
		}
	}

	/*
	 * Metodo per disegnare la mappa una volta creata dal costruttore.
	 * Se nella matrice c'è una casella percorribile viene colorata di nero.
	 * Se nella matrice c'è una casella non percorribile viene colorata di bianco
	 * */
	public void drawMap(Graphics g, List<Tile> path)
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if (!nodes[x][y].isWalkable())
				{
					g.setColor(Color.WHITE);
				}
				else if (path != null && path.contains(new Tile(x, y, true)))
				{
					g.setColor(Color.YELLOW);
				}
				else
				{
					g.setColor(Color.BLACK);
				}
				g.fillRect(x * 32, y * 32, 32, 32);
			}
		}
	}


	/*Metodo per stampare la mappa dallo standart out*/
	public void printMap(List<Tile> path)
	{
		for (int j = 0; j < height; j++)
		{
			for (int i = 0; i < width; i++)
			{
				if (!nodes[i][j].isWalkable())
				{
					System.out.print(" #");
				}
				else if (path.contains(new Tile(i, j, true)))
				{
					System.out.print(" @");
				}
				else
				{
					System.out.print("  ");
				}
			}
			System.out.print("\n");
		}
	}

	
	/*
	 * Metodo per ottenere il nodo (o tile) della matrice secondo le coordinate X e Y.
	 * Se X e Y sono nei limiti minimi e massimi della mappa, ritornerà il suddetto
	 * nodo nelle coordinate X e Y
	 * */
	public Tile getNode(int x, int y)
	{
		if (x >= 0 && x < width && y >= 0 && y < height)
		{
			return nodes[x][y];
		}
		else
		{
			return null;
		}
	}

	
	/*
	 * Metodo per la ricerca del cammino minimo all'interno del labirinto.
	 * Vengono passate le coordinate della matrice (x,y) del punto di partenza
	 * e le coordinte (x, y) della matrice del punto di destinazione dettato dall'utente
	 * tramite mouse.
	 * In questo metodo è implementato l'algoritmo di A* e ritornerà una lista, ossia
	 * un path con costo minimo calcolato attraverso distanza euristica.
	 * Tale percorso, verrà poi seguito dal player che ne effettuerà la simulazione
	 * 
	 * */
	public final List<Tile> findPath(int startX, int startY, int goalX, int goalY)
	{
		/* Se la posizione iniziale in coordinate (x,y) 
		 * coincide con l'obiettivo, allora abbiamo concluso.
		 * */
		if (startX == goalX && startY == goalY)
		{
			System.exit(1);
			//Ritorna una nuova lista vuota, perché non dobbiamo muoverci.
			return new LinkedList<Tile>();
		}

		/*Questa lista è l'insieme dei nodi da visitare*/
		List<Tile> openList = new LinkedList<Tile>();
		/*Questa lista è l'insieme dei nodi già visitati*/
		List<Tile> closedList = new LinkedList<Tile>();

		/*
		 * Viene aggiunto il nodo con coordinate passare al metodo come nodo che deve
		 * essere vistato, quindi come nodo start
		 * 
		 * */
		openList.add(nodes[startX][startY]);


		/*
		 * Questo loop verrà interrotto quando il giocatore avrà raggiunto 
		 * il nodo obiettivo.
		 * */
		while (true)
		{		
			/*
			 * Calcolo della funzione fScore che contiene il più basso
			 * costo nella openList.
			 * */
			Tile current = lowestFInList(openList);
			/*Rimozione del nodo corrente dalla openList*/
			openList.remove(current);
			/*Aggiunta del nodo corrente nella closedList*/
			closedList.add(current);

			/*Se la posizione del nodo corrente è uguale alla posizione del nodo obiettivo
			 * allora verrà calcolato il path dal nodo corrente al nodo obiettivo*/
			if ((current.getX() == goalX) && (current.getY() == goalY))
			{
				/*Ritorna il path calcolato */
				return calcPath(nodes[startX][startY], current);
			}
			
			/*Calcolo dei nodi adiacenti al nodo corrente appartenente alla closedList*/
			List<Tile> adjacentNodes = getAdjacent(current, closedList);
			
			for (Tile adjacent : adjacentNodes)
			{
				/*Se il nodo non è nella openList, ai nodi adiacenti verranno settati:
				 * il nodo padre, cioè il nodo corrente, calcolati la funzione di stima H
				 * cioè la stima euristica. e la distanza gScore, cioè la distanza
				 * tra il nodo start, il nodo iniziare e il nodo corrente.
				 * Infine verrà aggiunto alla openList*/
				if (!openList.contains(adjacent))
				{
					adjacent.setParent(current);
					adjacent.setH(nodes[goalX][goalY]);
					adjacent.setG(current);
					openList.add(adjacent);
				}
				
				/*
				 * Altrimenti, se il nodo è nell'openList e il valore gScore è maggiore
				 * del gScore calcolato, allora non è un cammino migliore.
				 * 
				 * */
				else if (adjacent.getG() > adjacent.calculateG(current))
				{
					/*Si aggiunge il nodo corrente come padre del nodo adiacente*/
					adjacent.setParent(current);
					/*
					 * E si imposta il nodo corrente come attuale nodo adiacente,
					 * cioè il costo del nodo start al nodo attuale
					 * */
					adjacent.setG(current);
				}
			}

			if (openList.isEmpty())
			{				

				return new LinkedList<Tile>();
			}
		}
	}
	

	/*
	 * Metodo per il calcolo del percorso dal nodo start al nodo goal
	 * In questo metodo, si itera fin quando il nodo start coincide con il nodo
	 * goal e ritornerà il path, ossia il percorso.
	 * */
	private List<Tile> calcPath(Tile start, Tile goal)
	{
		LinkedList<Tile> path = new LinkedList<Tile>();

		Tile node = goal;
		boolean done = false;
		while (!done)
		{
			path.addFirst(node);
			node = node.getParent();
			if (node.equals(start))
			{
				done = true;
			}
		}
		
		System.out.println(path.size());
		return path;
	}


	
	/*
	 * Metodo per il calcolo della fScore minima.
	 * Ritorna il più piccolo valore F nella lista a partire dal primo
	 * */
	private Tile lowestFInList(List<Tile> list)
	{
		Tile cheapest = list.get(0);
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getF() < cheapest.getF())
			{
				cheapest = list.get(i);
			}
		}
		return cheapest;
	}


	
	/*
	 * Metodo per prendere i nodi adiacenti al nodo corrente.
	 * I parametri del metodo sono il nodo bersaglio per verificare i nodi adiacenti
	 * e la lista closedList dei nodi già visitati*/
	private List<Tile> getAdjacent(Tile node, List<Tile> closedList)
	{
		List<Tile> adjacentNodes = new LinkedList<Tile>();
		int x = node.getX();
		int y = node.getY();

		Tile adjacent;

		/*Controllo a sinistra del nodo*/
		if (x > 0)
		{
			adjacent = getNode(x - 1, y);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
			{
				adjacentNodes.add(adjacent);
			}
		}

		/*Controllo a destra del nodo*/
		if (x < width)
		{
			adjacent = getNode(x + 1, y);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
			{
				adjacentNodes.add(adjacent);
			}
		}

		/*Controllo al di sopra del nodo*/
		if (y > 0)
		{
			adjacent = this.getNode(x, y - 1);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
			{
				adjacentNodes.add(adjacent);
			}
		}

		/*Controllo al di sotto del nodo*/
		if (y < height)
		{
			adjacent = this.getNode(x, y + 1);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
			{
				adjacentNodes.add(adjacent);
			}
		}
		return adjacentNodes;
	}
	
}
