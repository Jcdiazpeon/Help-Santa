import java.util.*;
import java.io.*;

public class Santa{
	public static void main(String[] arg)throws IOException{
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		ArrayList<Kid> kids = new ArrayList<Kid>();

		fillKidsList(kids);
		fillGiftList(gifts);
		gifts = sortGifts(gifts);
		kids = sortKids(kids);
		getBudget();
		getDays();

	}

	public static void fillKidsList(ArrayList kids)throws IOException{ //(ER) Fills the kids array list
		Scanner r = new Scanner(new File("kids.txt"));
		String name;
		String in;
		String good;
		String sub;
		int age;

		while(r.hasNext()){
			in=r.nextLine();
			int comma1=in.indexOf(',');
			int comma2=in.indexOf(',',comma1+1);
			name=in.substring(0,comma1);
			good=in.substring(comma1+2,comma2);
			sub=in.substring(comma2+2);
			age=Integer.parseInt(sub);

			kids.add(new Kid(name,good,age));

		}

	}


	public static void fillGiftList(ArrayList gifts)throws IOException{//(ER) fills the gift list
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

	public static double getBudget(){//(ER) Gets user input for the budget.
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
		boolean isFirst = true;

		for(Gift temp : gifts){
			if(isFirst){
				sortedGifts.add(0, temp); //adds first gift to the list
				isFirst = false;
			}
			else{
				index = 0;
				for(Gift sortedTemp : sortedGifts){ // finds the correct spot of each gift in the sorted list
					if(temp.getPrice() <= sortedTemp.getPrice()){ //bad
						sortedGifts.add(index, temp);
						index = 0;
						break;
					}
					if(index >= sortedGifts.size()-1){
						sortedGifts.add(temp);
						index = 0;
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
		boolean isFirst = true;

		for(Kid temp : kids){
			if(isFirst){
				sortedKids.add(0, temp);
				isFirst = false;
			}
			else{
				index = 0;
				for(Kid sortedTemp : sortedKids){
					if(temp.getAge() <= sortedTemp.getAge()){
						sortedKids.add(0, temp);
						index++;
						break;
					}
					if(index >= sortedKids.size()-1){
						sortedKids.add(temp);
						index = 0;
						break;
					}
					index++;
				}
			}
		}
		return sortedKids;
	}
}
