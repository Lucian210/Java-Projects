
public class Items extends IRestaurantProcessing{
	
	String nume;
	int pret;
	
	public Items(String nume, int pret)
	{
		this.nume = nume;
		this.pret = pret;
	}
	
	public void setNume(String nume)
	{
		this.nume=nume;
	}

	public void setPret(int pret)
	{
		this.pret = pret;
	}
	
	public String getNume()
	{
		return nume;
	}
	
	public int getPret()
	{
		return pret;
	}

	
}
