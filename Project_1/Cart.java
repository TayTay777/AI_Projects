import java.util.ArrayList;
/**
 * creation and functionality of a cart
 * 
 * @author Taylor Roberts
 * @author Hailee Kiesecker
 */

public class Cart {

		ArrayList<Sack> sacks;
		ArrayList<Item> unpackedItems;

		
        //For copying carts
        public Cart(Cart cart){
            sacks = new ArrayList<Sack>(cart.sacks);
            unpackedItems = new ArrayList<Item>(cart.unpackedItems);
        }
		
		
		public Cart(ArrayList<Item> items, int numSacks, int maxSackSize) {
			unpackedItems = new ArrayList<Item>(items);
			sacks = new ArrayList<Sack>(numSacks);
			for (int i = 0; i < numSacks; i++)
				sacks.add(new Sack(maxSackSize));
		}

		// Tries to add an item to specified sack
		// if it cannot fit, returns false, else returns true
		boolean addItem(int sackNum, Item item) {

            boolean canMix = true;
            for (int i = 0; i < sacks.get(sackNum).contents.size(); i++) {
                if (sacks.get(sackNum).contents.get(i).checkCompatible(item) == false) {
                    canMix = false;
                    break;
                }
            }

            if (canMix) {
                if (item.itemSize > sacks.get(sackNum).openSpace) {
                    return false;
                } else {
                    sacks.get(sackNum).addItem(item);
                    unpackedItems.remove(item);
                    return true;
                }
            } 
            else return false;
		}
		
		
		boolean canAdd(int sackNum, Item item) {

            boolean canMix = true;
            for (int i = 0; i < sacks.get(sackNum).contents.size(); i++) {
                if (sacks.get(sackNum).contents.get(i).checkCompatible(item) == false) {
                    canMix = false;
                    break;
                }
            }

            if (canMix) {
                if (item.itemSize > sacks.get(sackNum).openSpace) {
                    return false;
                } else {
                    return true;
                }
            } 
            else return false;
		}

		
		boolean solution() {
			if (unpackedItems.size() == 0) {
				return true;
			} else{
			
				return false;
			}
		}

		// This will print one solution
		// the DFS or BFS will keep calling
		// this for every solution
		void printGroceries() {
			System.out.println("success");
			
			for (int i = 0; i < sacks.size(); i++) {
				for (int z = 0; z < sacks.get(i).contents.size(); z++) {
					if (z == sacks.get(i).contents.size() - 1) {
						System.out.print(sacks.get(i).contents.get(z).name);
						
					} else System.out.print(sacks.get(i).contents.get(z).name + "\t");
					
				}
				if (sacks.get(i).contents.size() > 0) {
					System.out.println();
				}
			}
		}
	}
