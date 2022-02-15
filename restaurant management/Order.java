public class Order extends IRestaurantProcessing {
	

	int OrderId; 
	int Table;
	String data;
	String mancare;
	
	public Order(int id, int masa, String data, String mancare)
	{
		this.OrderId=id;
		this.Table=masa;
		this.data=data;
		this.mancare=mancare;
	}
	
	public void setOrder(int order)
	{
		this.OrderId=order;
	}

	public void setTable(int table)
	{
		this.Table = table;
	}
	
	public void setDate(String fata)
	{
		this.data = fata;
	}
	
	public int getId()
	{
		return OrderId;
	}
	
	public int getTable()
	{
		return Table;
	}
	
	public String getDate()
	{
		return data;
	}
	

}
