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

	// For copying carts
	public Cart(Cart cart) {
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
		} else
			return false;
	}

	void addItemLS(int sackNum, Item item) {
		sacks.get(sackNum).addItem(item);
		unpackedItems.remove(item);
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
		} else
			return false;
	}

	boolean solution() {
		if (unpackedItems.size() == 0) {
			return true;
		} else {

			return false;
		}
	}

	boolean isConflicted(int sackNum) {
		boolean conflicted = false;
		if (sacks.get(sackNum).openSpace < 0) {
			return true;
		}
		for (int i = 0; i < sacks.get(sackNum).contents.size(); i++) {
			for (int z = 0; z < sacks.get(sackNum).contents.size(); z++) {
				if (!sacks.get(sackNum).contents.get(i).equals(sacks.get(sackNum).contents.get(z))) {
					if (!sacks.get(sackNum).contents.get(i).checkCompatible(sacks.get(sackNum).contents.get(z))) {
						conflicted = true;
					}
				}

			}
		}
		return conflicted;
	}

	Item removeConflict(int sackNum) {
		int conflictType = 0;
		Item returnedItem = null;
		boolean conflictTypeIncompatibleFound = false;

		if (sacks.get(sackNum).openSpace < 0) {
			conflictType = 0;
		}
		for (int i = 0; i < sacks.get(sackNum).contents.size(); i++) {
			for (int z = 0; z < sacks.get(sackNum).contents.size(); z++) {
				if (!sacks.get(sackNum).contents.get(i).equals(sacks.get(sackNum).contents.get(z))) {
					if (!sacks.get(sackNum).contents.get(i).checkCompatible(sacks.get(sackNum).contents.get(z))) {
						conflictType = 1;
					}
				}
			}
		}
		if (conflictType == 0) {
			int largestSackSize = 0;
			for (int z = 0; z < sacks.get(sackNum).contents.size(); z++) {
				if (sacks.get(sackNum).contents.get(z).itemSize > largestSackSize) {
					largestSackSize = sacks.get(sackNum).contents.get(z).itemSize;
					returnedItem = sacks.get(sackNum).contents.get(z);
				}
			}

		} else {

			for (int z = 0; z < sacks.get(sackNum).contents.size(); z++) {
				if (conflictTypeIncompatibleFound){
					break;
				}
				for (int w = 0; w < sacks.get(sackNum).contents.size(); w++) {
					if (!sacks.get(sackNum).contents.get(z).equals(sacks.get(sackNum).contents.get(w))) {
						if (!sacks.get(sackNum).contents.get(z).checkCompatible(sacks.get(sackNum).contents.get(w))) {
							returnedItem = sacks.get(sackNum).contents.get(w);
							conflictTypeIncompatibleFound = true;
							break;
						}
					}
				}
			}
		}

		return returnedItem;
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

				} else
					System.out.print(sacks.get(i).contents.get(z).name + "\t");

			}
			if (sacks.get(i).contents.size() > 0) {
				System.out.println();
			}
		}
	}
}
