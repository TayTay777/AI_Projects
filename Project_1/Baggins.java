import java.util.ArrayList;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;


public class Baggins {


	File file; // taking in contents of cart and restrictions

	public Baggins(File file) {
		this.file = file;
	}

	// public interface storage {
	//     public Cart get();

	//     public void put(Cart s);

	//     public boolean isEmpty();
	// }

	// /**
	//  * implementation of data storage via stack
	//  */

	// public class stackStorage implements storage {
	//     Stack<Cart> stack = new Stack<Cart>();

	//     public Cart get() {
	//         stack.pop();
	//     }

	//     public void put(Cart s) {
	//         stack.push(s);
	//     }

	//     public boolean isEmpty() {
	//         return stack.isEmpty();
	//     }
	// }

	// /**
	//  * implementation of data storage via queue
	//  */
	// public class queueStoage implements storage {
	//     Queue<Cart> queue = new Queue<Cart>();

	//     public Cart get() {
	//         queue.dequeue();
	//     }

	//     public void put(Cart s) {
	//         queue.enqueue(s);
	//     }

	//     public boolean isEmpty() {
	//         return queue.isEmpty();
	//     }
	// }

	// /***
	//  * Depth first search to find solution to the bagging problem
	//  * 
	//  * @return "optimal" solution
	//  */

	// private Cart DFS(Cart s) {

	//     // initialize the data store with s
	//     storage dfs;
	//     dfs.add(s);

	//     while (!dfs.isEmpty()) {

	//     }

	//     // while data store has stuff in it:

	//     // pull next solution s from data store

	//     // check it for being bad state, if so continue
	//     // check it for being a goal state, if yes return

	//     // if it isn’t
	//     // need to expand storage —
	//     // grab next unpacked item,
	//     // remove it from unpacked item list
	//     // for each bag, make a copy of the solution state, and pack the item in it ,
	//     // if no constraints are violated and then place it in data structure

	// }

	void BFS() {



		Items bfsItems = new Items(file);
		ArrayList<Item> items = bfsItems.createItems();
		int numSacks = bfsItems.numSacks;


		Cart bfsCart = new Cart(items, numSacks, bfsItems.sackSize);

		Queue<Cart> bfsQueue = new LinkedList<>();


		//Start the queue
		for (int i = 0; i < bfsCart.sacks.size(); i++){
			Cart startCart = new Cart(items, numSacks, bfsItems.sackSize);

			//items are removed from back
			if (startCart.addItem(i, items.get(items.size()-1))){
				bfsQueue.add(startCart);
			}
		}


		while(!bfsQueue.isEmpty()){
			for (int i = 0; i < bfsCart.sacks.size(); i++){
				Cart queCart = bfsQueue.peek();
				Cart newCart = new Cart(items, numSacks, bfsItems.sackSize);

				if (queCart.solution()){
					queCart.printGroceries();
					break;
				}

				//checks to make sure item will fit in sack number 'i'
				if (queCart.addItem(i, queCart.unpackedItems.get(queCart.unpackedItems.size()-1))){
					//for loop for going through every sack in queCart
					for (int z = 0; z < numSacks; z++) {
						//for loop for going through every item in a sack from queCart
						for (int x = 0; x < queCart.sacks.get(z).contents.size(); x++) {
							//fills in new cart with data from queCart
							newCart.addItem(z,queCart.sacks.get(z).contents.get(x));
						}
					}
					//Adds Copied cart into queue
					bfsQueue.add(newCart);
				}
			}
			bfsQueue.remove();
		}
	}
}
