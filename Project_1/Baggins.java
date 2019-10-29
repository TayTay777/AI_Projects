import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
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
	Boolean arc;

	public Baggins(File file, Boolean arc) {
		this.file = file;
		this.arc = arc;

	}

	void LS() {

		boolean solution = false;
		Items lsItems = new Items(file);
		ArrayList<Item> items = lsItems.createItems();
		int numSacks = lsItems.numSacks;
		Cart lsCart = new Cart(items, numSacks, lsItems.sackSize);
		int randRestart = 0;

		// initializes the cart by placing items into a random sack
		for (int i = 0; i < items.size(); i++) {
			Random rand = new Random();
			int randomSackNum = rand.nextInt(numSacks);
			lsCart.addItemLS(randomSackNum, lsCart.unpackedItems.remove(items.size() - (i + 1)));
		}

		if (lsCart.solutionLS()) {
			System.out.println("Solution made!");
			lsCart.printGroceries();
			solution = true;
		}

		while (!solution) {
			// System.out.println(hi++);
			if (randRestart == 100) {
				lsCart = new Cart(items, numSacks, lsItems.sackSize);
				randRestart = 0;

				// initializes the cart by placing items into a random sack
				for (int i = 0; i < items.size(); i++) {
					Random rand = new Random();
					int randomSackNum = rand.nextInt(numSacks);
					lsCart.addItemLS(randomSackNum, lsCart.unpackedItems.remove(items.size() - (i + 1)));
				}

				if (lsCart.solutionLS()) {
					// System.out.println("Solution made!");
					lsCart.printGroceries();
					solution = true;
				}
			}
			// Randomly picks a sack for a conflict
			Random rand = new Random();
			int randomSack = rand.nextInt(numSacks);
			Item conflictedItem;

			if (lsCart.isConflicted(randomSack)) {
				// removes incompatible items
				conflictedItem = lsCart.removeConflict(randomSack);

				lsCart.addConflict(randomSack, conflictedItem);
			}

			// checks if cart is a solution
			if (lsCart.solutionLS()) {
				// System.out.println("Solution made!\n");
				lsCart.printGroceries();
				solution = true;
			}

			randRestart++;
		}
	}

	// Boolean for turning arc consistantcy on or off
	void MrvLcvDFS(boolean arcConsistancy) {
		long startTime = System.nanoTime();
		Items dfsItems = new Items(file);
		ArrayList<Item> items = dfsItems.createItems();
		int numSacks = dfsItems.numSacks;
		Stack<Cart> MrvLcvStack = new Stack<Cart>();

		PriorityQueue<Item> MRVitems = new PriorityQueue<Item>(items.size(), new MRVComp());
		// PriorityQueue<Sack> LCVsacks = new PriorityQueue<Sack>(numSacks, new
		// LCVComp());

		// Setting up MRV priority queue
		for (int i = 0; i < items.size(); i++) {
			items.get(i).MRV = numSacks;
			MRVitems.add(items.get(i));
		}

		// Initial set-up of the stack
		// (number of items) * (number of sacks) = states

		// item with smallest MRV value is used first
		// because we are using a stack, so the the highest
		// MRV value will appear on top
		int MRVitemInitSize = MRVitems.size();
		for (int z = 0; z < MRVitemInitSize; z++) {

			for (int x = 0; x < numSacks; x++) {

				// New cart

				Cart startCart = new Cart(items, numSacks, dfsItems.sackSize);
				PriorityQueue<Sack> LCVsacks = new PriorityQueue<Sack>(numSacks, new LCVComp());

				for (int i = 0; i < numSacks; i++) {
					startCart.calLCV(MRVitems.peek(), startCart.sacks.get(i));
					// System.out.println("Sack #" + i + " LCV Value: " +
					// startCart.sacks.get(i).LCV);
					LCVsacks.add(startCart.sacks.get(i));
				}

				// Add item to new cart, then add to stack
				// Adding item with the smallest LCV first

				int xAlias = x;
				while (xAlias != 0) {
					LCVsacks.remove();
					xAlias--;
				}

				if (startCart.canAdd(LCVsacks.peek(), MRVitems.peek())) {

					startCart.addItem(LCVsacks.peek(), MRVitems.peek());
					// Adds to stack
					MrvLcvStack.push(startCart);

				}
			}
			MRVitems.remove();
		}

		// DFS implementation that stops after finding a solution
		// arc consistancy can be turned on or off
		int solutions = 0;

		while (!MrvLcvStack.empty()) {

			Cart poppedCart = MrvLcvStack.pop();
			PriorityQueue<Item> MRVitemsInner = new PriorityQueue<Item>(items.size(), new MRVComp());

			// Recalculates new MRV values for this iteration
			for (int x = 0; x < poppedCart.unpackedItems.size(); x++) {
				// Reset MRV
				poppedCart.unpackedItems.get(x).MRV = 0;
				// Then recalculate MRV
				for (int zz = 0; zz < numSacks; zz++) {
					if (poppedCart.canAdd(zz, poppedCart.unpackedItems.get(x))) {
						poppedCart.unpackedItems.get(x).MRV++;
					}
				}

				MRVitemsInner.add(poppedCart.unpackedItems.get(x));
			}

			// item with smallest MRV value is used first
			// because we are using a stack, so the the highest
			// MRV value will appear on top
			int MRVitemInitSizeInner = MRVitemsInner.size();
			for (int z = 0; z < MRVitemInitSizeInner; z++) {

				for (int x = 0; x < numSacks; x++) {

					// New cart is made then contents from top
					// of stack cart are copied over
					// New cart is made then contents from top
					// of stack cart are copied over
					Cart newCart = new Cart(items, numSacks, dfsItems.sackSize);

					for (int t = 0; t < numSacks; t++) {
						// for loop for going through every item in a sack from sCart
						for (int w = 0; w < poppedCart.sacks.get(t).contents.size(); w++) {
							// fills in new cart with data from sCart
							newCart.addItem(t, poppedCart.sacks.get(t).contents.get(w));
						}
					}

					PriorityQueue<Sack> LCVsacks = new PriorityQueue<Sack>(numSacks, new LCVComp());

					// Calculates MRV of every sack
					for (int i = 0; i < numSacks; i++) {
						newCart.calLCV(MRVitemsInner.peek(), newCart.sacks.get(i));
						// System.out.println("Sack #" + i + " LCV Value: " +
						// startCart.sacks.get(i).LCV);
						LCVsacks.add(newCart.sacks.get(i));
					}

					// Add item to new cart, then add to stack
					// Adding item with the smallest LCV first

					int xAlias = x;
					while (xAlias != 0) {
						LCVsacks.remove();
						xAlias--;
					}

					if (newCart.canAdd(LCVsacks.peek(), MRVitemsInner.peek())) {

						newCart.addItem(LCVsacks.peek(), MRVitemsInner.peek());

						if (newCart.solution()) {
							newCart.printGroceries();
							solutions++;
							long endTime = System.nanoTime();
							System.out.println((endTime - startTime) / 1000000);
							//System.out.println("Solutions: " + solutions);
							System.exit(0);
						}

						// Where state gets added to the stack
						// arc consistency check goes here as well
						else {

							// if (arcConsistancy) {

							// 	// in the end, this item's value must match the number
							// 	// of unpacked items in order to be arc consistant
							// 	int itemArcCheck = 0;
							// 	boolean itemArcPass = false;

							// 	for (int u = 0; u < newCart.unpackedItems.size(); u++) {

							// 		if (!itemArcPass) {

							// 			for (int q = 0; q < numSacks; q++) {

							// 				// Creates a brand new cart everytime an item is placed into the cart
							// 				Cart newCartArc = new Cart(items, numSacks, dfsItems.sackSize);

							// 				for (int t = 0; t < numSacks; t++) {
							// 					// for loop for going through every item in a sack from sCart
							// 					for (int w = 0; w < newCart.sacks.get(t).contents.size(); w++) {
							// 						// fills in new cart with data from sCart
							// 						newCartArc.addItem(t, newCart.sacks.get(t).contents.get(w));
							// 					}
							// 				}

							// 				if (newCartArc.canAdd(q, newCartArc.unpackedItems.get(u))) {

							// 					newCartArc.addItem(newCartArc.sacks.get(q),
							// 							newCartArc.unpackedItems.get(u));

							// 					// Checks to see of all other items can still go into at lease one sack
							// 					int itemCanStillSack = 0;
							// 					for (int uu = 0; uu < newCartArc.unpackedItems.size(); uu++) {

							// 						for (int tt = 0; tt < numSacks; tt++) {

							// 							if (newCartArc.canAdd(newCartArc.sacks.get(tt),
							// 									newCartArc.unpackedItems.get(uu))) {

							// 								itemCanStillSack++;
							// 								break;

							// 							}
							// 						}
							// 					}
							// 					if (itemCanStillSack == newCartArc.unpackedItems.size()) {
							// 						itemArcCheck++;
							// 						itemArcPass = true;
							// 					}

							// 				}

							// 				if(itemArcPass){
							// 					itemArcPass = false;
							// 					break;
							// 				}

							// 			}

							// 		}

							// 	}

							// 	// if all items are arc consistant
							// 	if (itemArcCheck == newCart.unpackedItems.size()) {
							// 		// Adds to stack
							// 		MrvLcvStack.push(newCart);
							// 	}

							// }

							if (arcConsistancy) {

								// in the end, this item's value must match the number
								// of unpacked items in order to be arc consistant
								int itemArcCheck = 0;
								boolean itemArcPass = false;

								for (int u = 0; u < newCart.unpackedItems.size(); u++) {

									if (!itemArcPass) {

										for (int q = 0; q < numSacks; q++) {

											if (newCart.canAdd(q, newCart.unpackedItems.get(u))){
												itemArcCheck++;
												break;
											}

										}

									}

								}

								// if all items are arc consistant
								if (itemArcCheck == newCart.unpackedItems.size()) {
									// Adds to stack
									MrvLcvStack.push(newCart);
								}

							}
							
							
							
							else {

								// Adds to stack
								MrvLcvStack.push(newCart);
								// System.out.println("Cart pushed");

							}

						}
					}
				}
				MRVitemsInner.remove();
			}

		}

	}

	void DFS() {

		long startTime = System.nanoTime();

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
		int solutions = 0;
		while (!dfsStack.isEmpty()) {
			Cart sCart = dfsStack.pop(); // getting top cart
			for (int i = 0; i < dfsCart.sacks.size(); i++) {
				Cart newCart = new Cart(items, numSacks, dfsItems.sackSize);
				// solution found get out
				if (sCart.solution()) {
					solved = true;
					sCart.printGroceries();
					long endTime = System.nanoTime();
					System.out.println((endTime - startTime) / 1000000);
					System.exit(0);
					solutions++;
					System.out.println(solutions);

				}

				// checks to make sure item will fit in sack number 'i'
				else if (sCart.canAdd(i, sCart.unpackedItems.get(sCart.unpackedItems.size() - 1))) {
					// for loop for going through every sack in sCart
					for (int z = 0; z < numSacks; z++) {
						// for loop for going through every item in a sack from sCart
						for (int x = 0; x < sCart.sacks.get(z).contents.size(); x++) {
							// fills in new cart with data from sCart
							newCart.addItem(z, sCart.sacks.get(z).contents.get(x));
						}
					}
					// Adds the new item
					newCart.addItem(i, sCart.unpackedItems.get(sCart.unpackedItems.size() - 1));
					// Adds Copied cart onto stack
					dfsStack.push(newCart);
				}
			}
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
