//kid objects
public class Kid
{
	//instance variables
	private String name, goodness;
	private int age;


	//constuctor
	public Kid (String n, String g, int a){
		name = n;
		goodness = g;
		age = a;
	}

	//get varables
	public String getName(String n)
	{
		return name;
	}

	public String getGoodness(String g)
	{
		return goodness;
	}

	 public int getAge(int a)
	 {
	    return age;
	 }
}