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

	public Cart ARC(Cart cart){
		//take in the items of unpackeditems 
		//take in their constraints 
		for( int i = 0 ; i < cart.unpackedItems.size(); i++){
			Item foo = cart.unpackedItems.get(i);
			for (int j=0; j < cart.unpackedItems.size(); j++){
				Item foo2 = cart.unpackedItems.get(j);
				//if the items are compaible 
				if(foo.checkCompatible(foo2)){
					//set smallest mrv to both
					if((foo.MRV+foo.itemSize) > (foo2.MRV+foo2.itemSize)){
						//foo2.isSet = true;
						foo2.MRV = foo.MRV;
					}
					else if((foo.MRV+foo.itemSize) < (foo2.MRV+foo2.itemSize)){
						//foo.isSet = true;
						foo.MRV = foo2.MRV;
					}
					
				}
				
			}
		}
		//System.out.println("hello");
		return cart;

	}

	public void MRV() {
		boolean solved = false;
		Items mrvItems = new Items(file);
		ArrayList<Item> items = mrvItems.createItems();
		int numSacks = mrvItems.numSacks;

		Cart mrvCart = new Cart(items, numSacks, mrvItems.sackSize);
		Stack<Cart> mrvStack = new Stack<Cart>();

		// create a priority queue that is able to compare the MRV values (g to l)
		PriorityQueue<Item> findMRV = new PriorityQueue<Item>(mrvCart.unpackedItems.size(), new itemComp());
		// create a seocndary priority queue is able to compare LVC values (l to g)
		PriorityQueue<Sack> findLCV = new PriorityQueue<Sack>(numSacks, new sackComp());

		for (int i = 0; i < mrvCart.sacks.size(); i++) {
			Cart startCart = new Cart(items, numSacks, mrvItems.sackSize);

			if (startCart.canAdd(i, items.get(items.size() - 1))) {
				mrvStack.push(startCart);
			}
		}
		int hi = 0;
		while (!mrvStack.isEmpty()) {
			//System.out.println(hi++);
			for (int x = 0; x < mrvCart.sacks.size(); x++) {

				Cart temp = mrvStack.peek(); // getting top cart
				Cart newCart = new Cart(items, numSacks, mrvItems.sackSize); // will modify

				if (temp.solution()) {
					solved = true;
					temp.printGroceries();
					System.exit(0);
				} else {
					// for all the items
					for (Item foo : temp.unpackedItems) {
						// check to see if item fits in sack
						for (int i = 0; i < temp.sacks.size(); i++) {
							// if item fits in sack increase its mrv size
							if (temp.canAdd((i),(foo))) {
								//if(!foo.isSet){
									foo.MRV++;
								//}
								
								//temp.sacks.get(i).LCV += 1; // this could potentially be causing problems
							
							}
						}
					}


					//if arc check
					if(arc){
					temp = ARC(temp);

					}

					
					for (Item foo : temp.unpackedItems) {
						findMRV.add(foo);
					}
					// grabs current largest item
					Item i = findMRV.poll();

					for(Sack foo: temp.sacks ){
						
						for(Item food: temp.unpackedItems){
							if(foo.canFit(food)){
								temp.sacks.get(foo.position).LCV++;
							}
						}
					}
					
					for (Sack foo : temp.sacks) {
					
						findLCV.add(foo);
					}
					// grabs current smallest restraint bag
					Sack k = findLCV.poll();


					// copying over current peeked cart from stack
					if (temp.addItem(k.position, i)) {
						// for loop for going through every sack in temp
						for (int z = 0; z < numSacks; z++) {
							// for loop for going through every item in a sack from temp
							for (int y = 0; y < temp.sacks.get(z).contents.size(); y++) {
								// fills in new cart with data from temp
								newCart.addItem(z, temp.sacks.get(z).contents.get(y));
							}
						}
					
						//reset all values MRV/LCV in newCart 
						for(int d = 0; d< newCart.unpackedItems.size(); d++) {
							newCart.unpackedItems.get(d).MRV = 0;
						}
						for(int s = 0; s< newCart.sacks.size(); s++) {
							newCart.sacks.get(s).LCV = 0;
						}
						mrvStack.push(newCart);
						findMRV.clear();
						findLCV.clear();

					}

					//most constraing value and least constraining bag
					//cannot be placed in with eachother
					//increase LCV by 1 to mix up the cart
					else{
						k.LCV++;
						findLCV.add(k);
					}
						
				}
			}
	
			mrvStack.pop();
		}

		if (!solved) {
			System.out.println("failure...");
			System.exit(0);
		}
	}


	void LS() {

		boolean solution = false;
		Items lsItems = new Items(file);
		ArrayList<Item> items = lsItems.createItems();
		int numSacks = lsItems.numSacks;
		Cart lsCart = new Cart(items, numSacks, lsItems.sackSize);
		int randRestart = 0;
		int hi=0;
		// initializes the cart by placing items into a random sack
		for (int i = 0; i < items.size(); i++) {
			Random rand = new Random();
			int randomSackNum = rand.nextInt(numSacks);
			lsCart.addItemLS(randomSackNum, lsCart.unpackedItems.remove(items.size() - (i + 1)));
		}


		if (lsCart.solutionLS()){
			System.out.println("Solution made!");
			lsCart.printGroceries();
			solution = true;
		}



		while (!solution) {
			//System.out.println(hi++);
			if (randRestart == 100) {
				lsCart = new Cart(items, numSacks, lsItems.sackSize);
				randRestart = 0;

				// initializes the cart by placing items into a random sack
				for (int i = 0; i < items.size(); i++) {
					Random rand = new Random();
					int randomSackNum = rand.nextInt(numSacks);
					lsCart.addItemLS(randomSackNum, lsCart.unpackedItems.remove(items.size() - (i + 1)));
				}


				if (lsCart.solutionLS()){
					//System.out.println("Solution made!");
					lsCart.printGroceries();
					solution = true;
				}
			}
			// Randomly picks a sack for a conflict
			Random rand = new Random();
			int randomSack = rand.nextInt(numSacks);
			Item conflictedItem;


			if (lsCart.isConflicted(randomSack)){
				// removes incompatible items
				conflictedItem = lsCart.removeConflict(randomSack);

				lsCart.addConflict(randomSack, conflictedItem);
			}

			// checks if cart is a solution
			if (lsCart.solutionLS()){
				//System.out.println("Solution made!\n");
				lsCart.printGroceries();
				solution = true;
			}
			
			randRestart++;
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
		int hi=0;
		while (!dfsStack.isEmpty()) {
			System.out.println(hi++);
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
