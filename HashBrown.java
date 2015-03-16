import java.util.Arrays;

/* HashBrown: Implementation of HashTable / HashSet. I believe it is a Hash Set
 * because I did not add values to each key. */
public class HashBrown {

	private String[] theArray;
	private int tableSize;
	private int numOfElements;

	/* 0 parameter constructor */
	public HashBrown() {

		theArray = new String[10]; // default size
		tableSize = 10;
		numOfElements = 0;
		Arrays.fill(theArray, "");

		System.out.println("Initializing a new hash table of size 10!");
	}

	/* Size specified constructor */
	public HashBrown(int size) {

		if (size > 30) {
			System.out.println("Specified size too large! Initializing to default value 10..");
			size = 10;
		}

		theArray = new String[size];
		tableSize = size;
		numOfElements = 0;
		Arrays.fill(theArray, "");

		System.out.println("Initializing a new hash table of size " + size + ".");
	}

	/* Insert item into the table */
	public void insertItem(String item) {

		int asciiSum = 0;

		if (numOfElements == tableSize) {
			System.err.println("Table is full.. Cannot add any items!");
			return;
		}

		asciiSum = sumChars(item);
		asciiSum %= tableSize;

		while (true) {

			if (theArray[asciiSum] == "") {
				theArray[asciiSum] = item;
				System.out.println("Inserting " + item + " at position " + asciiSum + "!");
				numOfElements++;
				break;
			}

			if (theArray[asciiSum].equals(item)) { // duplicate found
				System.out.println("Overriding duplicate item!");
				break;
			}

			asciiSum++; // keep incrementing by 1 until we find a spot

			if (asciiSum == tableSize) { // reach end of table
				asciiSum = 0;
			}
		}
	}

	/* Deletes an item in the Hash Table */
	public void deleteItem(String item) {

		/* iterations is used to count the number of times we search. Since the hashing
		 * algorithm indexes the current location if it is full, we know we've checked
		 * the entire table once the iterations == the table size */
		int iterations = 0;

		if (numOfElements == 0) {
			System.err.println("Cannot delete from an empty table!");
			return;
		}

		int asciiSum = sumChars(item);
		asciiSum %= tableSize; // ensure bucket is within table range

		while (iterations < tableSize) {

			if (theArray[asciiSum].equals(item)) {
				theArray[asciiSum] = "";
				System.out.println("Deleting " + item + " at position " + asciiSum + "!");
				numOfElements--;					
				break;
			}

			asciiSum++; // keep incrementing by 1 until we find the item

			if (asciiSum == tableSize) { // reach end of table
				asciiSum = 0;
			}

			iterations++;
		}

		if (iterations >= tableSize) {
			System.out.println(item + " not found in table!");
		}			
	}

	/* Looks an item up in the Hash Table */
	public void lookupItem(String item) {

		/* iterations is used to count the number of times we search. Since the hashing
		 * algorithm indexes the current location if it is full, we know we've checked
		 * the entire table once the iterations == the table size */
		int iterations = 0;

		if (numOfElements == 0) {
			System.err.println("Table empty.. No items to lookup!");
			return;
		}

		int asciiSum = sumChars(item);			
		asciiSum %= tableSize; // ensure bucket is within table range

		while (iterations < tableSize) {

			if (theArray[asciiSum].equals(item)) {
				System.out.println("Found " + item + " at position " + asciiSum + "!");
				break;
			}

			asciiSum++; // keep incrementing by 1 until we find the item

			if (asciiSum == tableSize) { // reach end of table
				asciiSum = 0;
			}

			iterations++;
		}

		if (iterations >= tableSize) {
			System.out.println(item + " not found in table!");
		}
	}

	/* Returns the ASCII Sum of the characters of the input string */
	public int sumChars(String s) {
		int sum = 0;

		for (int i = 0; i < s.length(); i++) {
			sum += s.charAt(i);
		}

		return sum;
	}

	public int getTableSize() { // tableSize getter
		return tableSize;
	}

	public int getNumOfElements() { // numOfElements getter
		return numOfElements;
	}

	/* Override this object's default toString() method */
	@Override public String toString() {

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < tableSize; i++) {
			result.append(String.format("| %8s |\n", theArray[i])); // default size of input
		}

		return result.toString();
	}

}