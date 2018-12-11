/*
Jacob Marsh, 12/11/18, Pd. 6
This is my own work, JAM
PROJECT DESCRIPTION -- Class for the creation of gift objects to be utilised in the Help Santa group project.
*/

public class Gift
{
	//variables
	private String giftName;
	private double price;
	private int minAge, maxAge, daysToBuild;

	//overloaded compiler
	public Gift ()
	{
		giftName = "Twister";
		minAge = 5;
		maxAge = 80;
		price = 9.99;
		daysToBuild = 3;
	}

	//compiler
	public Gift (String g, int min, int max, double p, int d)
	{
		giftName = g;
		minAge = min;
		maxAge = max;
		price = p;
		daysToBuild = d;
	}

	//get methods
	public String getGiftName()
	{
		return giftName;
	}
	public int getMinAge()
	{
		return minAge;
	}
	public int getMaxAge()
	{
		return maxAge;
	}
	public double getPrice()
	{
		return price;
	}
	public int getDaysToBuild()
	{
		return daysToBuild;
	}

	//set methods
	public void setGiftName(String g)
	{
		giftName = g;
	}
	public void setMinAge(int min)
	{
		minAge = min;
	}
	public void setMaxAge(int max)
	{
		maxAge = max;
	}
	public void setPrice(double p)
	{
		price = p;
	}
	public void getDaysToBuild(int d)
	{
		daysToBuild = d;
	}

	//print method for displaying object info
	public String toString()
	{
		String str;
		str = "\nName: " + giftName + "\nMinimum Age: " + minAge + "\nMaximum Age: " + maxAge + "\nPrice: " + price + "\nDays to Build: " + daysToBuild;
		return str;
	}
}
