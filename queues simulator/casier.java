import java.util.ArrayList;

public class casier {
	ArrayList<client> lista_clienti;
	int TimpLaCoada;
	
	
	//constructor
	public casier() {
		this.lista_clienti = new ArrayList<client>();
		this.TimpLaCoada = 0;
	}
	
	
	
	public ArrayList<client> getClienti() {
		return lista_clienti;
	}
	
	public void setClienti(ArrayList<client> lista_clienti) {
		this.lista_clienti = lista_clienti;
	}
	
	
	public Integer getTimpLaCoada() {
		return TimpLaCoada;
	}
	
	public void setTimpLaCoada(Integer TimpLaCoada) {
		this.TimpLaCoada = TimpLaCoada;
	}

	public void addClient(client e) {
		this.lista_clienti.add(e);
		this.TimpLaCoada += e.getService();
	}
	
	public void removeClient() {
		if (!this.lista_clienti.isEmpty()) {
			this.lista_clienti.remove(0);
		}
	}
	
	public void refresh() {
	
			this.TimpLaCoada--;
			this.lista_clienti.get(0).setService(this.lista_clienti.get(0).getService() - 1);		//trece o secunda la coada
			
			if (0 == this.lista_clienti.get(0).getService()) {
				this.removeClient();								//daca timpul de servire a unui client e 0 atunci el e scos de la coada
			}
	
	}
}
