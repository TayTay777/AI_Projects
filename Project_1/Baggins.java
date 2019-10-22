import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * working class calling other created classes implements DFS and BFS
 * 
 * @author Taylor Roberts
 * @author Hailee Kiesecker
 */
public class Baggins {

	File file; // taking in contents of cart and restrictions

	public Baggins(File file) {
		this.file = file;

	}

	void LS() {

		Items lsItems = new Items(file);
		ArrayList<Item> items = lsItems.createItems();
		int numSacks = lsItems.numSacks;
		Cart lsCart = new Cart(items, numSacks, lsItems.sackSize);
		boolean solution = false;

		// initializes the cart by placing items into a random sack
		for (int i = 0; i < items.size(); i++) {
			Random rand = new Random();
			int randomSackNum = rand.nextInt(numSacks);
			lsCart.addItem(randomSackNum, lsCart.unpackedItems.remove(items.size() - (i + 1)));
		}

		while (!solution) {
			// Randomly picks a sack for a conflict
			Random rand = new Random();
			int randomSack = rand.nextInt(numSacks);
			
			
			if (lsCart.isConflicted(randomSack)){
				// removes incompatible items
				//TODO: remove conflicted item from cart items
				lsCart.removeConflict(randomSack);
				
			}

			// checks if cart is a solution
			

		}

	}

	void DFS() {
		boolean solved = false;
		Items dfsItems = new Items(file);
		ArrayList<Item> items = dfsItems.createItems();
		int numSacks = dfsItems.numSacks;

		Cart dfsCart = new Cart(items, numSacks, dfsItems.sackSize);
		Stack<Cart> dfsStack = new Stack<Cart>();

		for (int i = 0; i < dfsCart.sacks.size(); i++) {
			Cart startCart = new Cart(items, numSacks, dfsItems.sackSize);

			if (startCart.addItem(i, items.get(items.size() - 1))) {
				dfsStack.push(startCart);
			}
		}

		while (!dfsStack.isEmpty()) {
			for (int i = 0; i < dfsCart.sacks.size(); i++) {
				Cart sCart = dfsStack.peek(); // getting top cart
				Cart newCart = new Cart(items, numSacks, dfsItems.sackSize);
				// solution found get out
				if (sCart.solution()) {
					solved = true;
					sCart.printGroceries();
					System.exit(0);

				}

				// checks to make sure item will fit in sack number 'i'
				if (sCart.addItem(i, sCart.unpackedItems.get(sCart.unpackedItems.size() - 1))) {
					// for loop for going through every sack in sCart
					for (int z = 0; z < numSacks; z++) {
						// for loop for going through every item in a sack from sCart
						for (int x = 0; x < sCart.sacks.get(z).contents.size(); x++) {
							// fills in new cart with data from sCart
							newCart.addItem(z, sCart.sacks.get(z).contents.get(x));
						}
					}
					// Adds Copied cart onto stack
					dfsStack.push(newCart);
				}
			}

			dfsStack.pop();
		}
		if (!solved) {
			System.out.println("failure");
		}
	}

	void BFS() {

		boolean solved = false;
		Items bfsItems = new Items(file);
		ArrayList<Item> items = bfsItems.createItems();
		int numSacks = bfsItems.numSacks;

		// Cart bfsCart = new Cart(items, numSacks, bfsItems.sackSize);

		Queue<Cart> bfsQueue = new LinkedList<>();

		/*
		 * Starts the queue with the same item in each bag. Example: 2 4 item0 2 item1 2
		 * item2 2
		 *
		 * item2, being at the back of the item list, would be put into 3 different
		 * carts, each time being put in a different bag This puts the item2 in every
		 * possible place it could be.
		 */

		// Start the queue
		for (int i = 0; i < numSacks; i++) {
			Cart startCart = new Cart(items, numSacks, bfsItems.sackSize);

			// items are removed from back of item list
			if (startCart.addItem(i, items.get(items.size() - 1))) {
				bfsQueue.add(startCart);
			}
		}

		while (!bfsQueue.isEmpty()) {
			// Adds an unpacked item into the cart
			// places this same item into every possible sack
			// making a new copy for every possible sack
			for (int i = 0; i < numSacks; i++) {
				Cart queCart = bfsQueue.peek();
				Cart newCart = new Cart(items, numSacks, bfsItems.sackSize);

				if (queCart.solution()) {
					solved = true;
					queCart.printGroceries();
				}

				// checks to make sure item will fit in sack number 'i'
				// If it can, it's added, then the contents are copied to
				// a new Cart called newCart. Afterwards the queCart is removed
				// once all the other sacks from the first for loop (i) are checked.
				// changed if to else if
				else if (queCart.canAdd(i, queCart.unpackedItems.get(queCart.unpackedItems.size() - 1))) {
					// for loop for going through every sack in queCart
					for (int z = 0; z < numSacks; z++) {
						// for loop for going through every item in a sack from queCart
						for (int x = 0; x < queCart.sacks.get(z).contents.size(); x++) {
							// fills in new cart with data from queCart
							newCart.addItem(z, queCart.sacks.get(z).contents.get(x));
						}
					}
					// Adds the new item
					newCart.addItem(i, queCart.unpackedItems.get(queCart.unpackedItems.size() - 1));
					// Adds Copied cart into queue
					// Only if an item can be added,
					// will a new copy be
					// made and then added into this queue
					bfsQueue.add(newCart);
				}
			}
			// here is where queCart is removed.
			bfsQueue.remove();
		}
		if (!solved) {
			System.out.println("failure");
		}
	}

}
