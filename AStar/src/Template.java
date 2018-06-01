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
 * Classe Astratta per la costruzione del design pattern
 * Template Method.
 * Attraverso questa classe, possiamo ereditate tante quante sottoclassi
 * vogliamo, mantenendo i metodi non implementati.
 * In questo modo, possiamo "conservare" i metodi per poi costruire
 * una futura implementazione basandoci su un template già
 * esistente
 * */
public abstract class Template
{
	private static void init(){};
	private static void start(){};
	public static void stop(){};
	
	public final void go()
	{
		init();
		start();
		stop();
	}
	
	public void play(int level){};
}
