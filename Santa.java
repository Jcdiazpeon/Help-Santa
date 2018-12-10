import java.util.*;
import java.io.*;

public class Santa{
	public static void main(String[] arg)throws IOException{
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		//ArrayList<Kids> kids = new ArrayList<Kids>();

		getBudget();
		getDays();

	}

	public static void fillGiftList(ArrayList gifts)throws IOException{
		Scanner r = new Scanner(new File("kids.txt"));
		Scanner i = new Scanner(new File("gift.txt"));
		String g;
		Integer min;
		Integer max;
		Double p;
		Integer d;

		while(i.hasNext()){
			g=i.nextLine();
			min=Integer.parseInt(i.nextLine());
			max=Integer.parseInt(i.nextLine());
			p=Double.parseDouble(i.nextLine());
			d=Integer.parseInt(i.nextLine());

			gifts.add(new Gift(g,min,max,p,d));
		}

	}

	public static double getBudget(){
		Scanner r = new Scanner(System.in);
		double budget = 0.0;
		boolean validBudget = false;

		while(!validBudget){
			System.out.println("Please enter the budget for Christmas.");
			budget = r.nextDouble();

				if(budget>100){
					validBudget=true;
					return budget;
				}
				else{
					System.out.println("That was too low of a budget.");
				}
		}

			return budget;

	}

	public static int getDays(){
		Scanner r = new Scanner(System.in);
		int days=0;
		boolean validDays=false;

		while(!validDays){
			System.out.println("Please enter how many days are left until Christmas.");
			days = r.nextInt();

			if(days>3){
				validDays = true;
				return days;
			}
			else{
				System.out.println("Thats not enough time to build all the gifts before Christmas!");
			}
		}

		return days;
	}

}