import java.util.*;
import java.io.*;

public class Santa{
	public static void main(String[] arg)throws IOException{
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		ArrayList<Kid> kids = new ArrayList<Kid>();
		ArrayList<Gift> availableGifts = new ArrayList<Gift>();
		ArrayList<Kid> availableKids = new ArrayList<Kid>();

		fillKidsList(kids);
		fillGiftList(gifts);
		gifts = sortGifts(gifts);
		kids = sortKids(kids);
		double budget = getBudget();
		int days = getDays();

		availableGifts = filterGifts(gifts, budget, days);

		//eliminate naughty kids
		for(Kid element: kids){
			if(element.getGoodness().equals("nice")){
				availableKids.add(element);
			}
		}

	printList(availableKids, availableGifts);
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

				if(budget > 0){
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
				for(Kid sortedTemp : sortedKids){  // finds the correct spot of each kid in the sorted list
					if(temp.getAge() <= sortedTemp.getAge()){
						sortedKids.add(index, temp);
						index++;
						break;
					}
					if(index >= sortedKids.size()-1){  //If kid is the oldest
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

	public static ArrayList filterGifts(ArrayList<Gift> gifts, double budget, int days){ //keep toys as long as less than budget and within days to build (Amanda and Jose)
		ArrayList<Gift> availableGifts = new ArrayList<Gift>();
		ArrayList<Gift> withinDays = new ArrayList<Gift>();
		double total = 0;
		int numOfGifts = 0;
		boolean enoughMoney = true;

		for(Gift element: gifts){
			if(element.getDaysToBuild() <= days)
				withinDays.add(element); //Adds gifts that are within days to arrayList
		}

		while(enoughMoney){
			for(Gift element: withinDays){
				if(total + element.getPrice() <= budget){ //There is still money
					availableGifts.add(element);  //adds element as available to give
					total += element.getPrice();
					numOfGifts++;
				}
				else if(total + withinDays.get(0).getPrice() > budget)
					enoughMoney = false;
			}
		}
		return availableGifts;
	}

	//Prints final list of kids and their gifts to file and console (Amanda and Jose)
	public static void printList(ArrayList<Kid> availableKids, ArrayList<Gift> availableGifts)throws IOException{
		PrintWriter writer = new PrintWriter(new File("list.txt"));
		double total = 0;

		for(int i = 0; i < availableGifts.size(); i++){
			int kidIndex = i % availableKids.size(); //One kid might get more than one gift
			Kid tempKid = availableKids.get(kidIndex);
			Gift tempGift = availableGifts.get(i);

			writer.println(tempKid.getName() + " --> " + tempGift.getGiftName());  //Prints results
			System.out.println(tempKid.getName() + " --> " + tempGift.getGiftName());
			total += tempGift.getPrice();
		}
		System.out.println("\nTotal: " + total);
		writer.println("\nTotal: " + total);
		writer.close();
	}
}
