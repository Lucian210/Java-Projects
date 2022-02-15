public class client {
	int id;
    int arrival;
    int service;
    

    public client(int id, int arrival, int service) {
        this.id = id;
        this.arrival = arrival;
        this.service = service;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}
	
	public int getArrival() {
		return arrival;
	}

	public void setArrival(int arrival) {
		this.arrival = arrival;
	}


	public String toString() {
		return  "(" + this.getId() + "," + this.getArrival() + "," + this.getService() + ") ";
	}
}
