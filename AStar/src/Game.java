import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPanel;

/*
 * Progetto Programmazione 3
 * 
 * Anno Accademico 2017-2018
 * 
 * a cura di Pauciullo Valerio
 * matricola 0124001023
 * 
 * */
/*Classe Gioco, sottoclasse di JPanel, che implementa il Mouse Listenere(Interfaccia)*/
public class Game extends JPanel implements MouseListener
{
	/*Istanza dell'usernmane attraverso il Design Pattern "Singleton"*/
	private Username username = Username.login();
	private static final long serialVersionUID = -8395759457708163217L;
	/*Oggetto della classe Player*/
	private Player player;
	/*Oggetto della classe Map*/
	private Map map;
	/*Lista di Tile che rappresenterà il path che il giocatore deve percorrere nel labirinto*/
	private List<Tile> path;


	/*Costruzione della matrice di interi per il primo livello di difficoltà*/
	int[][] m0 = { //
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, //
			{ 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1 }, //
			{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1 }, //
			{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1 }, //
			{ 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1 }, //
			{ 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 }, //
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	/*Costruzione della matrice di interi per il secondo livello di difficoltà*/

	int[][] m1 = { //
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, //
			{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 }, //
			{ 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1 }, //
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1 }, //
			{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1 }, //
			{ 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1 }, //
			{ 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1 }, //
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
	
	/*Costruzione della matrice di interi per il terzo livello di difficoltà*/

	int[][] m2 = { //
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, //
			{ 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1 }, //
			{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1 }, //
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 }, //
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1 }, //
			{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1 }, //
			{ 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 }, //
			{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1 }, //
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
	
	/*Costruttore della classe Game()*/
	public Game(int level)
	{
		
		if(level == 1)
		{
			int[][] m = m0;
			map = new Map(m); //Istanza della oggetto map
			setPreferredSize(new Dimension(m[0].length * 32, m.length * 32));
		}
		
		if(level == 2)
		{
			int[][] m = m1;
			map = new Map(m); //Istanza della oggetto map
			setPreferredSize(new Dimension(m[0].length * 32, m.length * 32));
		}
		
		if(level == 3)
		{
			int[][] m = m2;
			map = new Map(m);//Istanza della oggetto map
			setPreferredSize(new Dimension(m[0].length * 32, m.length * 32));
		}
		
		addMouseListener(this);
		

		player = new Player(1, 1);
	}
	
	/*Metodo update del gioco per caricare l'update grafico sul giocatore*/
	public void update()
	{
		player.update();
	}
	
	/*
	 * Metodo render() della classe Game che disegna la mappa (cioè il labirinto), il giocatore,
	 * le mura e il percorso che il giocatore deve percorrere.
	 * */
	public void render(Graphics2D g)
	{
		/*Metodo drawMap() per disegnare le tassellature del labirinto (parenti e caselle percorribili)*/
		map.drawMap(g, path);
		g.setColor(Color.GRAY);
		for (int x = 0; x < getWidth(); x += 32)
		{
			g.drawLine(x, 0, x, getHeight());
		}
		for (int y = 0; y < getHeight(); y += 32)
		{
			g.drawLine(0, y, getWidth(), y);
		}
		/*Creazione del colore del nostro player 1*/
		g.setColor(Color.RED);
		g.fillRect(player.getX() * 32 + player.getSx(), player.getY() * 32 + player.getSy(), 32, 32);
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{

	}
	@Override
	public void mouseEntered(MouseEvent e)
	{
	
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}
	
	@Override
	/*Attraverso il "press" del mouse definiamo la destinazione nel labirinto*/
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX() / 32;
		int my = e.getY() / 32;
		/*
		 * Se la destinazione scelta dall'utente tramite mouse è percorribile allora
		 * è possibile procedere con l'algoritmo e il coloramento del percorso
		 * */
		if (map.getNode(mx, my).isWalkable())
		{
			/*
			 * Metodo che ritorna la lista del percorso che il giocatore deve percorrere.
			 * Percorso dato all'algoritmo A* (A Star)
			 * */
			path = map.findPath(player.getX(), player.getY(), mx, my);
			player.followPath(path);
		}
		else
		{
			System.out.println("Can't walk to that node!");
		}
		/*
		 * Assegnazione del percorso al giocatore per poter 
		 * aggiornare il puntegggio ottenuto nel database
		 * */
		username.updateScore(path);
		System.out.println("Congratulation! Your score is: " + path.size());
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{}
}