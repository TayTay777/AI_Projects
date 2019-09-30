import java.util.ArrayList;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Baggins {


	File file; // taking in contents of cart and restrictions

	public Baggins(File file) {
		this.file = file;
	}

	void DFS() {
		boolean solved = false;
		Items dfsItems = new Items(file);
		ArrayList<Item> items = dfsItems.createItems();
		int numSacks = dfsItems.numSacks;


		Cart dfsCart = new Cart(items, numSacks, dfsItems.sackSize);
        Stack<Cart> dfsStack = new Stack<Cart>();
        
        for (int i = 0; i < dfsCart.sacks.size(); i++){
			Cart startCart = new Cart(items, numSacks, dfsItems.sackSize);

			if (startCart.addItem(i, items.get(items.size()-1))){
				dfsStack.push(startCart);
			}
		}

        while(!dfsStack.isEmpty()){
            for (int i = 0; i < dfsCart.sacks.size(); i++){
                Cart sCart = dfsStack.peek(); //getting top cart
			    Cart newCart = new Cart(items, numSacks, dfsItems.sackSize);
                //solution found get out
                if (sCart.solution()){
					solved = true;
                    sCart.printGroceries();
                    System.exit(0);

                }

                //checks to make sure item will fit in sack number 'i'
			    if (sCart.addItem(i, sCart.unpackedItems.get(sCart.unpackedItems.size()-1))){
                    //for loop for going through every sack in sCart
				    for (int z = 0; z < numSacks; z++) {
					    //for loop for going through every item in a sack from sCart
					    for (int x = 0; x < sCart.sacks.get(z).contents.size(); x++) {
						    //fills in new cart with data from sCart
						    newCart.addItem(z,sCart.sacks.get(z).contents.get(x));
						}
					}
			    //Adds Copied cart onto stack
			    dfsStack.push(newCart);
                }
            }
            
          dfsStack.pop();
        }
		if(!solved){
			System.out.println("failure");
		}
	}

	void BFS() {


		boolean solved = false;
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
					solved = true;
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
		if(!solved){
			System.out.println("failure");
		}
	}
	
}
