public class MonitoredData {
	String start_time;
	String end_time;
	String activity_label;
	
	public MonitoredData(String s, String e, String a)
	{
		this.start_time=s;
		this.end_time=e;
		this.activity_label=a;
	}
	
	public String getS()
	{
		return this.start_time;
	}
	
	public String getE()
	{
		return this.end_time;
	}
	
	public String getA()
	{
		return this.activity_label;
	}
	
	public void setS(String S)
	{
		this.start_time=S;
	}
	
	public void setE(String E)
	{
		this.end_time=E;
	}
	
	public void setA(String A)
	{
		this.activity_label=A;
	}
	
	
}


