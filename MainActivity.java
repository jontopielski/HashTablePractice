import java.util.Scanner;

/* Main: Where the Hash Table is initialized and used */
public class MainActivity {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.print("Enter size for Hash Table. ");
		String tabSize = input.next();
		HashBrown table;

		/* Test for correct format */
		try {
			table = new HashBrown(Integer.parseInt(tabSize));
		}
		catch (Exception e) {
			System.err.println(tabSize + " is incorrect format!");
			table = new HashBrown();
		}

		while (true) {
			System.out.println("Choose one: (1) insert   (2) delete   (3) lookup   (4) exit");
			String choice = input.next();

			try { // invalid choice
				if (!validChoices(choice)) {
					System.err.println("Invalid choice!");
					continue;
				}
			}

			catch (Exception e) { // invalid string input
				System.err.println("Invalid input entered!");
				continue;
			}

			if (choice.equals("4")) break; // Exit

			System.out.println("Enter a word to " + printChoice(choice) + ".");
			String item = input.next();

			if (choice.equals("1")) {
				table.insertItem(item);
			}

			if (choice.equals("2")) {
				table.deleteItem(item);
			}

			if (choice.equals("3")) {
				table.lookupItem(item);
			}

			System.out.println(table);
		}

		System.out.println("Exiting..");
	}

	/* Simple helper method that simplifies the choice selection */
	public static boolean validChoices(String str) {
		if ((!str.equals("1")) && (!str.equals("2")) && 
			  (!str.equals("3")) && (!str.equals("4"))) {
			return false;
		}

		return true;
	}

	public static String printChoice(String str) {
		if (str.equals("1")) return "insert";
		if (str.equals("2")) return "delete";
		if (str.equals("3")) return "lookup";
		return "";
	}
}