import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;


public class test {

	public static void main(String[] args) {
		
		
		outFile fisier = new outFile();	// obiect pentru fisierul in care scriu/citesc
		data input = new data();		// obiect pentru datele citite
		fisier.Filein();					//deschidem fisierul din care citim
		input = fisier.scan();		// citesc data
		
		

		
		runCasier[] casieri = new runCasier[input.getQ()];
																	// definim Q casieri-uri si le alocam memorie
		for (int i = 0; i < input.getQ(); ++i) {
			casieri[i] = new runCasier();
		}
		
		Thread[] thread = new Thread[input.getQ()];
																// aloc memorie thread-urilor si le pornesc
		for (int i = 0; i < input.getQ(); ++i) {
			thread[i] = new Thread(casieri[i]);
			thread[i].start();
		}

		
		ArrayList<client> lista_clienti_random = new ArrayList<client>();		//lista pentru clientii ce vor fi generati random
		lista_clienti_random = GenerareClienti(input.getN(), input.getDurata(), input.getMax_arrival(), input.getMin_arrival(), input.getMax_service(), input.getMin_service());		//adaugare in lista a clientilor generati
		lista_clienti_random.sort(Comparator.comparingInt(client::getArrival));		//sortarea clientilor in functie de arrival time


		
		simulare sim = new simulare(new ArrayList<>(Arrays.asList(casieri)), lista_clienti_random, input.getDurata());
		Thread schedulerThread = new Thread(sim);				//pornesc thread-ul pentru simulare
		schedulerThread.start();
	}
	
	
	public static ArrayList<client> GenerareClienti(Integer N, Integer durata, Integer arrivalMax, Integer arrivalMin, Integer serviceMax, Integer serviceMin) {
		ArrayList<client> clients = new ArrayList<client>();

		for (int i = 0; i < N; ++i) {
			client element = new client(i, new Random().nextInt(arrivalMax + 1) + arrivalMin,
					new Random().nextInt(serviceMax + 1) + serviceMin);
			clients.add(element);
		}

		return clients;
	}
}
