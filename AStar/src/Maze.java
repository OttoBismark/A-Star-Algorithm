import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/*
 * Progetto Programmazione 3
 * 
 * Anno Accademico 2017-2018
 * 
 * a cura di Pauciullo Valerio
 * matricola 0124001023
 * 
 * */

public class Maze extends Template
{
	/* 
	 * Dichiarazioni delle variabili, costanti e istanze degli oggetti 
	 * 
	 * */
	
	private static Game game;
	/*
	 * Variabile di tipo "BufferedImage" per settare il tipo di immagine che verrà
	 * mostrata nel JFrame di Java
	 * */
	private static BufferedImage image;
	/*Variabile di tipo Graphics2D per creare la componente grafica nel frame di gioco*/
	private static Graphics2D g;
	/*Variabile per il livello di gioco scelto dall'utente */
	private static int leveling;
	private static boolean forceQuit;
	/*Costanti per la dimensione fissa del frame di gioco*/
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	/*Metoto play che prepara la schermata principare del gioco e le sue componenti connesse*/
	public void play(int level)
	{
		leveling = level;
		Maze.init(leveling);
		
		/*Istanza dell'oggetto frame di tipo JFrame*/
		JFrame frame = new JFrame();
		frame.setTitle("Maze Level : level " + level);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.pack();
		frame.setContentPane(game);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		Maze.start();
	}
	
	/*Metodo per l'inizializzazione del gioco*/
	private static void init(int leveling)
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setBackground(Color.BLACK);

		/*Istanza del gioco*/
		game = new Game(leveling);
	}
	
	/*Metodo per avviare il lo start del gioco*/
	private static void start()
	{
		run();
	}
	
	/*Metodo per fermare il gioco in caso di chiusura forzata*/
	public static void stop()
	{
		forceQuit = true;
	}

	/*Metodo run() per avviare il gioco in modo continuo*/
	public static void run()
	{
		/*FPS del gioco*/
		@SuppressWarnings("unused")
		int frames = 0;

		double unprocessedSeconds = 0;
		long lastTime = System.nanoTime();
		double secondsPerTick = 1.0 / 60.0;
		int tickCount = 0;

		while (!forceQuit)
		{
			/*
			 * In questa fase setteremo un click di tempo dato dal clock dell'orologio
			 * per avviare l'esecuzione del gioco in modo continuo (fin quando forceQuit non è false).
			 * In questo blocco di codice verranno settati i frame al secondo (Frame Per Second)
			 * per poi passarli alla componente grafica (Graphic2D)
			 * */
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;
			if (passedTime < 0)
				passedTime = 0;
			if (passedTime > 100000000)
				passedTime = 100000000;

			unprocessedSeconds += passedTime / 1000000000.0;

			boolean ticked = false;
			while (unprocessedSeconds > secondsPerTick)
			{
				/*Update del gioco chiamato dall'oggetto game*/
				game.update();
				unprocessedSeconds -= secondsPerTick;
				ticked = true;

				tickCount++;
				if (tickCount % 60 == 0)
				{
					// System.out.println("FPS:" + frames);
					lastTime += 1000;
					frames = 0;
				}
			}

			if (ticked)
			{
				/*Metodo  di rendering dell'oggetto gioco*/
				game.render(g);

				Graphics gg = game.getGraphics();
				gg.drawImage(image, 0, 0, null);
				gg.dispose();

				frames++;
			}
			else
			{
				try
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}