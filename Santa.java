import java.util.*;
import java.io.*;

public class Santa{
	public static void main(String[] arg)throws IOException{
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		ArrayList<Kid> kids = new ArrayList<Kid>();

		//fillKidsList(kids);
		fillGiftList(gifts);
		getBudget();
		getDays();

	}

	public static void fillKidsList(ArrayList kids)throws IOException{
		Scanner r = new Scanner(new File("kids.txt"));
		String n;
		String g;
		int a;

		r.useDelimiter(",");

		while(r.hasNext()){
			n=r.nextLine();
			g=r.nextLine();
			a=r.nextInt();

			kids.add(new Kid(n, g, a));

		}

	}


	public static void fillGiftList(ArrayList gifts)throws IOException{
		Scanner reader = new Scanner(new File("gifts.txt"));
		String g;
		int min;
		int max;
		Double p;
		int d;

	while(reader.hasNext()){
			g=reader.nextLine();
			min=reader.nextInt();
			max=reader.nextInt();
			p=reader.nextDouble();
			d=reader.nextInt();
			reader.nextLine();

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

	public static int getDays(){//(ER) gets user input for how many days until christmas
		Scanner r = new Scanner(System.in);
		int days=0;
		boolean validDays=false;

		while(!validDays){
			System.out.println("Please enter how many days are left until Christmas.");
			days = r.nextInt();

			if(days>0){
				validDays = true;
				return days;
			}
			else{
				System.out.println("Thats not enough time to build all the gifts before Christmas!");
			}
		}

		return days;
	}
	
	public static ArrayList sortGifts(ArrayList<Gift> gifts){  //returns a sorted gift array list by price (Jose)
		ArrayList<Gift> sortedGifts = new ArrayList<Gift>();
		int index = 0;

		for(Gift temp : gifts){
			if(index == 0){
				sortedGifts.add(index, temp); //adds first gift to the list
				index++;
			}
			else{
				for(Gift sortedTemp : sortedGifts){ // finds the correct spot of each gift in the sorted list
					if(temp.getPrice() <= sortedTemp.getPrice()){
						sortedGifts.add(index, temp);
						index++;
						break;
					}
					index++;
				}
			}
		}
		return sortedGifts;
	}

	public static ArrayList sortKids(ArrayList<Kid> kids){  //Sorts arrayList of Kids according to age and returns arrayList (Jose)
		ArrayList<Kid> sortedKids = new ArrayList<Kid>();
		int index = 0;

		for(Kid temp : kids){
			if(index == 0){
				sortedKids.add(temp);
				index++;
			}
			for(Kid sortedTemp : sortedKids){
				if(temp.getAge() <= sortedTemp.getAge()){
					sortedKids.add(0, temp);
					index++;
					break;
				}
				index++;
			}
		}
		return sortedKids;
	}
}


}
