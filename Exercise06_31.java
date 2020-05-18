import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Exercise06_31 {

	public static void main(String[] args) {
		
		int answer = 0;
		int stepTwoSum = 0;
		int stepThreeSum = 0;
		boolean valid;
		long ccNumber;
		
		while (true) {
			
			try {
				//initialize scanner
				Scanner input = new Scanner(System.in);

				ArrayList<Integer> numberList = new ArrayList<Integer>();
				
				//read in credit card number from user
				System.out.println();
				System.out.println("Enter a credit card number: ");
				
				ccNumber = input.nextLong();
				
				createListFromLong(numberList, ccNumber);
				
				//check to make sure CC number is 13-16 digits
				if(numberList.size() < 13 || numberList.size() > 16) {
					throw new InputMismatchException();
				}
				
				//check to make sure number starts with 4, 5, 6, or 37
				if(numberList.get(0) != 4 && numberList.get(0) != 5 && numberList.get(0) != 6 && (numberList.get(0) != 3 || numberList.get(1) != 7) ) {
					throw new InputMismatchException();
				}
				
				stepOne(numberList);
				stepTwoSum = stepTwo(numberList);
				stepThreeSum = stepThree(numberList);
				valid = stepFour(stepTwoSum, stepThreeSum);
				
				//display result
				if(valid) {
					System.out.println(ccNumber + " is valid");
				}
				else {
					System.out.println(ccNumber + " is invalid");
				}
				
				//ask if user would like to continue
				//if no, exit loop
				//if yes, restart loop
				System.out.println();
				System.out.println("Would you like to enter another credit card number? Enter 1 to continue, any other number to exit:");
				answer = input.nextInt();
			
				if (answer != 1) {
					System.out.println("Goodbye!");
					break;
					}
				
				}
			
			//if user enters invalid input, prompt for correct input
			catch(InputMismatchException e) {
				System.out.println("Invalid input. Please try again.");
				System.out.println();
				continue;
				}
		}
	}
	
	
	//double numbers in even places
	public static void stepOne(ArrayList<Integer> list) {
		
		//keep track of number of times list is passed through
		int passNumber = 0;
		
		//iterate through arraylist from right to left
		for(int i = list.size()-1; i >= 0; i--) {

			int newNum = list.get(i);
			passNumber++;
			
			//for every other number beginning with second from the right
			//double number -- if two digit number results, add digits together
			if (passNumber % 2 == 0) {
			
				newNum *= 2;
				
				//add digits together if two digit number 
				if (newNum > 9) {
					int sum = 0;
					
					while(newNum > 0) {
						sum = sum + newNum % 10;
						newNum = newNum / 10;
					}
					
					newNum = sum;
				}
				
				//replace number in list with new number
				list.set(i, newNum);
			}
			
		}
	}
	
	//sum numbers in even places
	public static int stepTwo(ArrayList<Integer> list) {
		
		int passNumber = 0;
		int sum = 0;
		
		//iterate through arraylist from right to left
		for(int i = list.size()-1; i >= 0; i--) {

			int num = list.get(i);
			passNumber++;
			
			//sum every other number beginning with second from the right
			if (passNumber % 2 == 0) {
				sum += num;
			}
		}
		
		return sum;
	}
	
	//sum numbers in odd places
	public static int stepThree(ArrayList<Integer> list) {
		
		int sum = 0;
		int passNumber = 0;
		
		//iterate through arraylist from right to left
		for(int i = list.size()-1; i >= 0; i--) {

			int num = list.get(i);
			passNumber++;
			
			//sum every other number beginning with first from the right
			if (passNumber % 2 == 1) {
				sum += num;
			}
		}
		
		return sum;
	}
	
	//determine if CC number is valid by taking mod 10 of sums from step two and three
	public static boolean stepFour(int firstSum, int secondSum) {
		boolean valid = false;
		
		if ((firstSum + secondSum) % 10 == 0) {
			valid = true;
		}
		
		return valid;
	}
	
	//convert long entered by user into ArrayList
	public static void createListFromLong(ArrayList<Integer> list, long l) {

		int individualNum = 0;
	
		while(l > 0) {
			individualNum = (int) (l % 10);
			l /= 10;
			
			list.add(individualNum);
		}
		
		Collections.reverse(list);
	}
	
}
