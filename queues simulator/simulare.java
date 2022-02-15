import java.util.ArrayList;
import static java.lang.System.exit;


public class simulare implements Runnable {
	ArrayList<client> lista_clienti;
	ArrayList<runCasier> threads;
	int N, moment, durata;

	public simulare(ArrayList<runCasier> threads, ArrayList<client> lista_clienti, Integer durata) {
		this.threads = threads;
		this.lista_clienti = lista_clienti;
		this.N = lista_clienti.size();
		this.moment = -1;
		this.durata = durata;
	}


	public runCasier CelMaiScurtQueue() {
		runCasier minim = this.threads.get(0);			//primul queue
		int toate = this.threads.size();				//toate cozile

		for (int i = 1; i < toate; ++i) {
			if (minim.getCashier().getTimpLaCoada() > this.threads.get(i).getCashier().getTimpLaCoada()) {
				minim = this.threads.get(i);													//imi compara prima coada cu toate cozile si imi pune in minim coada cea mai scurta
			}
		}
		return minim;
	}
	
	


	public void ClientNou() {
		for (runCasier casier : threads) {		//update clienti pentru fiecare casier
			if (casier.getCashier().getClienti().isEmpty()) {
				casier.setWait(true);
			} else {
				casier.setExit(false);
			}
		}
	}


	public double AverageWaitingTime() {
		double timp = 0;

		for (runCasier casier : this.threads) {
			timp += casier.getCashier().getTimpLaCoada();
		}
		timp = timp/N;
		return timp;
	}


	
	public void QuitThreads() {
		for (runCasier casier : threads) {
			casier.setExit(true);
		}
	}
	

	public void run() {
		
		outFile result = new outFile();	// fisier pentru scriere	
		result.Fileout();				//inchide fisier
		
		while (true) {
			moment++;
			result.scriereFisier("\nTime " + moment + "\n");
			result.scriereFisier("Waiting Clients: ");
			for(int i=0; i<lista_clienti.size(); i++)
			{
			result.scriereFisier(lista_clienti.get(i) + " ");
			}
			

			
			if (moment > durata) {				//daca momentul ajunge sa fie mai mare ca durata maxima a simularii
				result.scriereFisier("\nLimita maxima de timp depasita\n");
				QuitThreads();
				result.scriereFisier("\nAverage waiting time:" + AverageWaitingTime() + '\n');
				result.closeFileout();
				exit(0);
			} 
				
			ClientNou();		//update la lista de clienti pentru toti casierii

			
			if (0 == this.lista_clienti.size()) {
				result.scriereFisier("\nToti clientii au ajuns la casieri\n");
				QuitThreads();
				result.scriereFisier("\nAverage waiting time:" + AverageWaitingTime() + '\n');
				result.closeFileout();
				exit(0);
			}
			

			// Cauta coada cu timp de asteptare cel mai scurt
			if (lista_clienti.get(0).getArrival() <= moment) {
				
				runCasier QueueMinim = CelMaiScurtQueue();
				
				QueueMinim.getCashier().setTimpLaCoada(QueueMinim.getCashier().getTimpLaCoada() + moment - lista_clienti.get(0).getArrival());		//update la noul timp total de asteptare la coada

				QueueMinim.getCashier().addClient(lista_clienti.get(0));					
																				//adauga noul client in coada
				result.scriereFisier("\n" + lista_clienti.get(0) + "a fost adaugat");				

				lista_clienti.remove(0);		//scoate din lista de asteptare clientul adaugat la coada
			}
		}
	}
}
