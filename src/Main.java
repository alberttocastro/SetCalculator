import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class Main {
	
	static final String askContinue = "Type 'y' to continue";
	static final String noValidInput = "Sorry. That's not a valid input.";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		boolean toContinue = true;
		
		String userInput;
		
		
		while(toContinue) {
			System.out.print("Please, insert the set of non negative integers: ");
			scanner = new Scanner(System.in);
			userInput = scanner.nextLine();
			
			try {
				System.out.println(calculate(userInput));
			} catch(IllegalArgumentException e) {
				System.out.println("Error in calculation.");
			}
			
			
			String wish = "";
			
			System.out.println(askContinue);
			wish = scanner.next();
			
			if(wish.equals("y")) {
				toContinue = true;
			} else {
				toContinue = false;
			}
			
		}

		
	}
	
	private static String calculate(String input) {
		
		String[] sets = input.replaceAll("\\s", "").split("\\*|\\+|\\-");
		
		String[] firstStringSet = sets[0].replaceAll("\\[|\\]", "").split(",");
		String[] secondStringSet = sets[1].replaceAll("\\[|\\]", "").split(",");
		
		TreeSet<Integer> firstSet = parser(firstStringSet);
		TreeSet<Integer> secondSet = parser(secondStringSet);
		
		if(input.contains("*")) {
			firstSet.retainAll(secondSet);
		} else if(input.contains("-")){
			firstSet.removeAll(secondSet);
		} else if(input.contains("+")) {
			firstSet.addAll(secondSet);
		} else {
			
		}
		
		String result = "";
		result += "[";
		
		Iterator<Integer> iterator = firstSet.iterator();
		while(iterator.hasNext()){
			result += iterator.next();
			
			if(iterator.hasNext())
				result += ",";
		}
		
		result += "]";	
		
		return result;
	}
	
	private static TreeSet<Integer> parser(String[] set) throws IllegalArgumentException{
		
		TreeSet<Integer> treeset = new TreeSet<Integer>();
		
		for(int ix = 0; ix < set.length; ix++) {
			int value = Integer.parseInt(set[ix]);
			if(value >= 0) {
				treeset.add(value);
			} else {
				throw new IllegalArgumentException("The set cannot hold negative values");
			}
			
		}
		
		return treeset;		
	}
	
	

}
