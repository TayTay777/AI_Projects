import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * an Items class that parces through the given file and creates an array list
 * of multiple item objects that include their individual constraints, names,
 * and weights.
 * @author Taylor Roberts
 * @author Hailee Kiesecker
 * @return Items ArrayList
 */


public class Items {

		ArrayList<Item> items = new ArrayList<Item>();
		File file;
		Scanner fileScan;
		int numSacks;
		int sackSize;

		public Items(File file) {
			this.file = file;

			// initialize the file
			try {
				fileScan = new Scanner(file);
			
			} catch (Exception e) {
				System.out.println("failure");
				System.exit(0);
				// TODO: handle exception
			}

		}

		// @return: items arraylist

		ArrayList<Item> createItems() {

			// Gets all the groceries items for reference
			ArrayList<String> baseItems = new ArrayList<String>();
			// Scans past the # of bags and max bag size

			numSacks = fileScan.nextInt(); // setting number of sacks
			fileScan.nextLine();
			sackSize = fileScan.nextInt(); // setting size of each bag
			fileScan.nextLine();

			// creates an item list of Strings
			while (fileScan.hasNextLine()) {
				baseItems.add(fileScan.next());
				fileScan.nextLine();

			}

			fileScan.reset(); // goes to top of file

			// Scans past the # of bags and max bag size
			try {
				fileScan = new Scanner(file);
			} catch (Exception e) {
				//TODO: handle exception
			}

			fileScan.nextLine();
			fileScan.nextLine();

			// scans over every item line each while iteration
			while (fileScan.hasNextLine()) {

				String itemName;
				ArrayList<String> compatible = new ArrayList<String>();
				int itemSize;

				String itemLine = fileScan.nextLine();
				Scanner itemLineScan = new Scanner(itemLine);
				boolean plus = false;
				boolean hasCons = false;

				itemName = itemLineScan.next();
				itemSize = itemLineScan.nextInt();
				if (itemLineScan.hasNext()) {
					hasCons = true;
					if (itemLineScan.next().equals("+")) {
						plus = true;
					}
				}

				// dealing with constraints
				if (hasCons) {

					if (plus) {
						while (itemLineScan.hasNext()) {
							compatible.add(itemLineScan.next()); // for each time it can be with add
						}
					}

					else {
						compatible.addAll(baseItems); // add all items into array
						while (itemLineScan.hasNext()) {
							compatible.remove(itemLineScan.next()); // for each item it cannot be with --remove
						}
					}

				}
				else compatible.addAll(baseItems);

				Item newItem = new Item(itemName, compatible, itemSize); // creating an item with all information
				items.add(newItem); // add new item into items array
				itemLineScan.close();

			}
			return items; // returning the array of grocery items with their constrains and sizes
		}

	}