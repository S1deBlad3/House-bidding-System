import java.util.Random;
import java.util.Scanner;

public class Main {



	
	//Objects
	public Scanner scanner = new Scanner(System.in);
	public Random random = new Random();
	
	

	
	
	/*
	 * The main constuctor (don't want to work with static methods and variables)
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. I then call the method to make all the lists "empty" so that i can use it to compare the lists
	 * 2. I then start the reular method start().
	 */
	public Main(String[][] list, String[][] bidhist, String[][] bidpricehist) {
		
		
		
		emptyAllList(list, bidhist, bidpricehist);
		start(list, bidhist, bidpricehist);
	}
	/*
	 * This is the main method that keeps the loops going.
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. I start by always setting the objId to "0" in case something were to go wrong it will always be 0
	 * 2. We start by asking what the user wants to do
	 * 3. We then take his input as an integer
	 * 4. We use a switch statment to see what the user wanted
	 * 
	 */
	public void start(String[][] list, String[][] bidhist, String[][] bidpricehist) {
		String objId;
		System.out.println();
		System.out.print("What would you like to do? \n 1. Register a new object \n 2. Register bid \n 3. End bidding process \n 4. Print bidding history for object \n 5. Print all unsold objects \n 6. Print all sold objects \n q - end program");
		System.out.println();
		int choice = input(list, bidhist, bidpricehist);
		
		
		
		switch (choice) {
		case 0:
			System.out.println("Ending program");
			System.exit(0);
			break;
		case 1:
			
			/*
			 * 1.We check what the name of the property, what type and how much it is gonna cost
			 * 2. We check if the type is the same as the ones we have in our parameters, so that the user cant type anything else
			 * 3. We then check if the price can be turned from a string to int. If it cant that means that there is letters in the numbers and we throw an error.
			 * 4. We then call the functuion registerAdress() with our variables
			 * 5. We thow an error if anything happens and restarts the loop
			 */
			
			
			System.out.println("Please type the name of the property");
			String name = stringInput();
			System.out.println("What is the property type (apartment, house or commercial)");
			String type = stringInput();
			
			if (type.equals("house") || type.equals("apartment") || type.equals("commercial")) {
				
				
				
				System.out.println("What is the starting price");
				String price = stringInput();
				
				try {
					int temp = Integer.parseInt(price);
				}catch(Exception e) {
					System.out.println("You have not typed a valid number, please try again");
					start(list, bidhist, bidpricehist);
				}
				
			
				registerAdress(name, type, price, list, bidhist, bidpricehist);
				
				start(list, bidhist, bidpricehist);
			}else {
				System.out.println("You have typed an invalid type, please try again");
				start(list, bidhist, bidpricehist);
			}
			
			
			
		
			
			
			
			
			break;
		case 2:
			
			
			/*
			 * 1. We ask what object the user wants to bid on
			 * 2. we check if the obj even exists if true we continue else we restart
			 * 
			 */
			
			
			System.out.println("What object would you like to add a bid on?");
			objId = stringInput();
			
			if (checkIfNumExist(objId, list, bidhist, bidpricehist)) {
				addBid(objId, list, bidhist, bidpricehist);
				start(list, bidhist, bidpricehist);
			}else {
				System.out.println("This id does not exist, please try again");
				start(list, bidhist, bidpricehist);
			}
			
		
			
			
			
			break;
		case 3:
			
			/*
			 * 1. We check if the object exists else we restart the loop
			 * 
			 */
			
			System.out.println("Object id?");
			objId = stringInput();
			
			if (checkIfNumExist(objId, list, bidhist, bidpricehist)) {
				endBiddingProcess(objId, list, bidhist, bidpricehist);
				start(list, bidhist, bidpricehist);
			}else {
				System.out.println("This id does not exist, please try again");
				start(list, bidhist, bidpricehist);
			}
			
			
			
			
			
			break;
		case 4:
			
			/*
			 * 1. We check if the object exists else we restart the loop
			 * 
			 */
			
			System.out.println("Object id?");
			objId = stringInput();
			
			if (checkIfNumExist(objId, list, bidhist, bidpricehist)) {
				printBiddingHistory(objId, list, bidhist, bidpricehist);
				start(list, bidhist, bidpricehist);
			}else {
				System.out.println("This id does not exist, please try again");
				start(list, bidhist, bidpricehist);
			}
			
		
			break;
		case 5:
			
			/*
			 * 1. We clear some space for the console, then we call the fucntion with the global variable items
			 * 
			 */
			
			
			System.out.println();
			
			printUnSold(list, bidhist, bidpricehist);
			
			start(list, bidhist, bidpricehist);
			
			
			
			
			break;
		case 6:
			
			/*
			 * 1. We clear some space for the console, then we call the fucntion with the global variable items
			 * 
			 */
			
			System.out.println();
			printAllSold(list);
			start(list, bidhist, bidpricehist);
			break;
		}
		
		
		
		
		
		
	}
	
	/*
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. We first take in whatever the user types
	 * 2. We check for q and if so we return 0 which is our exit code
	 * 3. We then try to convert the string to an int. If not we restart the loop.
	 * @Return - stringToInt - used for selection
	 */
	
	public int input(String[][] list, String[][] bidhist, String[][] bidpricehist) {
		
		String in = scanner.next();
		int stringToInt = 0;
		
		
		if (in.equals("q")) {
			return 0;
		}
		
		
		try {
			stringToInt = Integer.parseInt(in);	
		} catch(Exception e) {
			System.out.println("You have typed something wrong, please try again");
			start(list, bidhist, bidpricehist);
		}
		
		
		
		
		
		
		return stringToInt;
	}
	
	/*
	 * 1. We get the userinput and return it
	 * 
	 * @Return - strinIn - used as a common funktion to get something from the user.
	 */
	
	public String stringInput() {
		
		String stringIn = scanner.next();
		
		
		
		return stringIn;
	}
	
	
	
	/*
	 * This method makes sure all the lists are filled with "empty"
	 * 1. we go through all the list items and set them equal to "empty".
	 * 2. This goes for all except the 6th value in the items array which is a hidden value used for printing the array on the screen.
	 */
	public void emptyAllList(String[][] list, String[][] bidhist, String[][] bidpricehist) {
		for (int i = 0; i < list.length; i++) {
			
			
			
			for (int j = 0; j < list[i].length; j++) {
				list[i][j] = "empty";
				
			}
	
		}
		
		for (int i = 0; i < bidhist.length; i++) {
			
			
			
			for (int j = 0; j < bidhist[i].length; j++) {
				bidhist[i][j] = "empty";
				bidpricehist[i][j] = "empty";
				
			}
	
		}
	
	
		for (int i = 0; i < list.length; i++) {
			list[i][6] = "free";
		}

	
	
	}
	
	

	/*This method prints all the unSold items
	 * @param list - takes the list item with the items we want to use
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. We go through all the lists and we check if they are "free".
	 * 2. We then print out the inner variable from each item.
	 */
	public void printUnSold(String[][] list, String[][] bidhist, String[][] bidpricehist) {
	
		for (int i = 0; i < list.length; i++) {
			
			if (list[i][6].equals("free")) {
				System.out.println();
				System.out.printf("Id: %s  Name: %s  Type: %s  Intial Price: %s  Bidder: %s  current bid: %s", list[i][3], list[i][0], list[i][1], list[i][2], list[i][4], list[i][5]);
			}
			System.out.println();
		
	}
}
	
	/*This method prints all the sold items
	 * @param list - takes the list item with the items we want to use
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. We go through all the lists and we check if they are "Sold".
	 * 2. We then print out the inner variable from each item.
	 */
	public void printAllSold(String[][] list) {
		
		
		for (int i = 0; i < list.length; i++) {
			
			if (list[i][6].equals("Sold")) {
				System.out.println();
				System.out.printf("Id: %s  Name: %s  Type: %s  Intial Price: %s  Buyer: %s  Sold for: %s", list[i][3], list[i][0], list[i][1], list[i][2], list[i][4], list[i][5]);
				
			}
		}
		System.out.println();
		
		
		
		
		
	}
		
	
	
	/*This method adds a bid to the array
	 * @param bid - the id of the bid item
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. We start by checking all the items and see if one of them matches the id the user typed
	 * 2. Now we have what index/itteration it is in
	 * 3. We can then ask for more information
	 * 4. We then do some error checking by converting the bids to int. If it is empty then we turn k to 0.
	 * 5. We then check if the bid (l) is bigger than the existing bid (k). If it is bigger we contine, else we restart
	 * 6. We then set the correct values to the array.
	 * 7. We then check if the bid exists in the bid history and get the index
	 * 8. We then set the name and price in to the correct history arrays.
	 */
	public void addBid(String bid, String[][] list, String[][] bidhist, String[][] bidpricehist) {
		
		
		int index = 0;
		
		for (int i = 0; i < list.length; i++) {
			if (list[i][3].equals(bid)) {
				index = i;
			}
		}
		
		
		
		System.out.println("What is the name of the bidder?");
		String bidName = stringInput();
		
		
		System.out.println("How big is the bid?");
		String bidPrice = stringInput();
		
		int k;
		int l = Integer.parseInt(bidPrice);
		
		try {
			 k = Integer.parseInt(list[index][5]);
			 
		}catch (Exception e) {
			
			k = 0;
			
			
		}
		
		
		if (l < k) {
			System.out.printf("%s has a higher bid than you. The current bid is %s \n", list[index][4], list[index][5]);
			start(list, bidhist, bidpricehist);
		}
		
		list[index][4] = bidName;
		list[index][5] = bidPrice;
		
		
		
		
		
		
		for (int i = 0; i < bidhist.length; i++) {
			
			if (bidhist[i][0].equals(bid)) {
				index = i;
			}
			
		}
		
		
		for (int j = 1; j < bidhist[index].length; j++) {
			
			if (bidhist[index][j].equals("empty") && bidpricehist[index][j].equals("empty")) {
				
				bidhist[index][j] = bidName;
				bidpricehist[index][j] = bidPrice;
				break;
				
				
			}
			
			
			
		}
		

		System.out.println("Bid registered");
	
	}
	
	/*This method registers the adress
	 * @param name, type, price - The name, type and price of the object that is being added
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. We loop trough the array of items, and we find a empty slot and that is our index.
	 * 2. We then set the correct name, type and price to the array.
	 * 3. We create a random number between 1000-9999 and convert it to a string.
	 * 4. we then set this to position 3 in the array.
	 * 5. We then check if any other item has the same id and create a new one if it does, otherwise we contine.
	 * 6. last we add the id to the bider history arrays.
	 */
	
	public void registerAdress(String name, String type, String price, String[][] list, String[][] bidhist, String[][] bidpricehist) {
		
		int index = 0;
		
		for (int i = 0; i < list.length; i++) {
			if (!list[i][0].equals("empty")) {
				index++;
				continue;
			}
	
		}
		
		
		
		list[index][0] = name;
		list[index][1] = type;
		list[index][2] = price;
		
		final int low = 1000;
		final int high = 9999;
		int result = random.nextInt(high-low) + low;
		
		
		
		String idtoString = String.valueOf(result);
		
		
		list[index][3] = idtoString;
		
		//System.out.println(result);
		
		for (int i = 0; i < list.length; i++) {
			
			
			if (i == index) continue;
			
			if (list[i][3].equals(idtoString)) {
				result = random.nextInt(high-low) + low;
				idtoString = String.valueOf(result);
				list[i][3] = idtoString;
				
			}
		}
		
		
		
		
		bidhist[index][0] = idtoString;
		bidpricehist[index][0] = idtoString;
		
	}
	
	/*This method ends the bidding
	 * @param obj - the object id that we want to end
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. We then again get the index that has the object id
	 * 2. we check if the bid is empty and return
	 * 3. we ask the user if they want to accept the bid / the highest bid will always show
	 * 4. We then check if it is yes or no, and add that to the items array.
	 */
	
	public void endBiddingProcess(String obj, String[][] list, String[][] bidhist, String[][] bidpricehist) {

		int index = 0;
		
		for (int i = 0; i < list.length; i++) {
			if (list[i][3].equals(obj)) {
				index = i;
			}
		}
		
		
		if (list[index][5].equals("empty")) {
			System.out.println("Currently no one has bided on this object");
			start(list, bidhist, bidpricehist);
		}
		
		
		
		System.out.printf("Will you accept the bid from %s with the price %s  (yes or no)", list[index][4], list[index][5]);
		
		String yesorNo = stringInput();
		
		if (yesorNo.equals("yes")) {
			System.out.println("Bid has been accepted");
			list[index][6] = "Sold";	
		}
		else if (yesorNo.equals("no")) {
			System.out.println("Going back to the menu");
			start(list, bidhist, bidpricehist);
		}else {
			System.out.println("You have typed wrong, returning to meny");
			start(list, bidhist, bidpricehist);
		}
		
		
		
	}
	
	
	/*This methods prints out the bidding history
	 * @param obj - the object id
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. We get the index
	 * 2. then we print out the id correct history for each bid
	 */
	public void printBiddingHistory(String obj, String[][] list, String[][] bidhist, String[][] bidpricehist) {
		
		int sum = 0;
		
		for (int i = 0; i < bidhist.length; i++) {
			if (bidhist[i][0].equals(obj)) {
				sum = i;
			}
		}
		
	
	
		
			
			
		for (int i = 1; i < 10; i++) {
			System.out.println();
			System.out.printf("Bidder name:  %s   Bid price:  %s", bidhist[sum][i], bidpricehist[sum][i]);
		}
			
	}
	
	
	/*This method checks if the obj exists
	 * @param obj - Id of the object
	 * @param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	 * 1. We loop trough all the items and check if the obj id exists
	 * 2. we then return true of false.
	 * 
	 * 
	 */
	public boolean checkIfNumExist(String obj, String[][] list, String[][] bidhist, String[][] bidpricehist) {
		
		for (int i = 0; i < list.length; i++) {
			
			if (list[i][3].equals(obj)) {
				return true;
			}
			
		}
		
		return false;
		
		
		
		
	}
	
	//Method that gets index. Couldnt use because i manually had to controll the items.
	//@param list, bidhist, bidpricehist = The items list, the bidhistory and the bid price history
	public int getIndex(String[][] list, String[][] bidhist, String[][] bidpricehist, String obj) {
		
		int index = 0;
		
		for (int i = 0; i < list.length; i++) {
			if (list[i][3].equals(obj)) {
				index = i;
			}
		}
		
		
		
		
		
		return 0;
	}
	
	
	
	//Main method that starts the program
	public static void main(String[] args) {
		
		String items[][] = new String[10][7]; //I have decided to only have 10 objects at a time, altough this can increase how much you like.
		String bidderHistory[][] = new String [10][10]; //I decided to only list 10 but this can increase aswell
		String bidderPriceHistory[][] = new String[10][10]; //I decided to only list 10 but this can increase aswell
		
		
		new Main(items, bidderHistory, bidderPriceHistory);
	}

}
