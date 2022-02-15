import static java.lang.Thread.sleep;

public class runCasier implements Runnable{		//threads
	
	boolean wait;		//nu are clienti
	casier cashier;		
	boolean exit;		//se termina
	
	public runCasier() {
		this.wait = true;
		this.cashier = new casier();
		this.exit = false;
	}
	
	public casier getCashier() {
		return cashier;
	}

	public void setWait(boolean wait) {
		this.wait = wait;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}


	public void run() {
		while (!this.exit) {
			do {		
				try {
						sleep(1000);
				} catch (InterruptedException e) {
						
					e.printStackTrace();
				}
				
			}while (wait);
			
			cashier.refresh();
		}
	}

}
