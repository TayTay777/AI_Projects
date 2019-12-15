import java.util.ArrayList;
import java.util.Random;

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

	boolean addItem(Sack sack, Item item) {

		boolean canMix = true;
		for (int i = 0; i < sack.contents.size(); i++) {
			if (sack.contents.get(i).checkCompatible(item) == false) {
				canMix = false;
				break;
			}
		}

		if (canMix) {
			if (item.itemSize > sack.openSpace) {
				return false;
			} else {
				sack.addItem(item);
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

	boolean canAdd(Sack sack, Item item) {

		boolean canMix = true;
		for (int i = 0; i < sack.contents.size(); i++) {
			if (sack.contents.get(i).checkCompatible(item) == false) {
				canMix = false;
				break;
			}
		}

		if (canMix) {
			if (item.itemSize > sack.openSpace) {
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

	boolean solutionLS() {

		for (int i = 0; i < sacks.size(); i++) {
			if (sacks.get(i).openSpace < 0) {
				return false;
			}
			for (int z = 0; z < sacks.get(i).contents.size(); z++) {
				for (int x = 0; x < sacks.get(i).contents.size(); x++) {
					if (sacks.get(i).contents.get(z) != sacks.get(i).contents.get(x)) {
						if (!sacks.get(i).contents.get(z).checkCompatible(sacks.get(i).contents.get(x))) {
							return false;
						}
					}
				}
			}
		}

		return true;
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
		// Item returnedItem = null;
		Random rand = new Random();
		int sackItem = rand.nextInt(sacks.get(sackNum).contents.size());
		Item returnedItem = sacks.get(sackNum).contents.get(sackItem);
		// boolean conflictTypeIncompatibleFound = false;
		//
		// if (sacks.get(sackNum).openSpace < 0) {
		// conflictType = 0;
		// }
		//
		// int totalConflicts = 0;
		// int totalSackConflicts = 0;
		// for (int i = 0; i < sacks.get(sackNum).contents.size(); i++) {
		// for (int z = 0; z < sacks.get(sackNum).contents.size(); z++) {
		// if
		// (!sacks.get(sackNum).contents.get(i).equals(sacks.get(sackNum).contents.get(z)))
		// {
		// if
		// (!sacks.get(sackNum).contents.get(i).checkCompatible(sacks.get(sackNum).contents.get(z)))
		// {
		// totalSackConflicts = totalSackConflicts + 1;
		// if (z == (sacks.get(sackNum).contents.size() - 1)) {
		// if (totalSackConflicts > totalConflicts) {
		// conflictType = 1;
		// returnedItem = sacks.get(sackNum).contents.get(z);
		// }
		// }
		// }
		// }
		// }
		// }
		//
		// if (conflictType == 0) {
		//
		// int largestSackSize = -999;
		//
		// for (int z = 0; z < sacks.get(sackNum).contents.size(); z++) {
		// if (sacks.get(sackNum).openSpace > largestSackSize) {
		// largestSackSize = sacks.get(sackNum).openSpace;
		// returnedItem = sacks.get(sackNum).contents.get(z);
		// }
		// }
		// }

		// removes item
		sacks.get(sackNum).removeItem(returnedItem);
		// adds item back to unpacked items
		unpackedItems.add(returnedItem);
		return returnedItem;
	}

	void addConflict(int sackNum, Item addedItem) {
		int openSpace = -999;
		int incompatibleOpenSpace = 0;
		int sackToAddForOpenSpace = 0;
		int incompatibleCount = 999;
		int bagIncompatibleCount;
		int sackToAddForIncompatible = 0;
		boolean incompatible = false;
		boolean emptySack = false;

		for (int i = 0; i < sacks.size(); i++) {
			if (i != sackNum) {
				if (sacks.get(i).openSpace == sacks.get(i).maxSize) {
					sackToAddForOpenSpace = i;
					emptySack = true;
				}
			}
		}

		if (!emptySack) {

			for (int i = 0; i < sacks.size(); i++) {
				if (i != sackNum) {

					if (sacks.get(sackNum).contents.size() == 0) {
						if ((sacks.get(i).openSpace - addedItem.itemSize) >= 0) {
							if ((sacks.get(i).openSpace - addedItem.itemSize) > openSpace) {
								openSpace = (sacks.get(i).openSpace - addedItem.itemSize);
								sackToAddForOpenSpace = i;
							}
						}
					}

					for (int z = 0; z < sacks.get(i).contents.size(); z++) {
						if (sacks.get(i).openSpace - addedItem.itemSize > openSpace) {
							openSpace = sacks.get(i).openSpace - addedItem.itemSize;
							sackToAddForOpenSpace = i;
						}
					}
				}
			}

			for (int i = 0; i < sacks.size(); i++) {
				bagIncompatibleCount = 0;
				if (i != sackNum) {
					for (int z = 0; z < sacks.get(i).contents.size(); z++) {
						if (!sacks.get(i).contents.get(z).checkCompatible(addedItem)) {
							bagIncompatibleCount = bagIncompatibleCount + 1;
							if (z == sacks.get(i).contents.size() - 1) {
								if (bagIncompatibleCount < incompatibleCount) {
									incompatibleCount = bagIncompatibleCount;
									sackToAddForIncompatible = i;
									incompatibleOpenSpace = sacks.get(i).openSpace - addedItem.itemSize;
									incompatible = true;
								} else if (bagIncompatibleCount == incompatibleCount) {
									if (incompatibleOpenSpace > sacks.get(i).openSpace - addedItem.itemSize) {
										incompatibleCount = bagIncompatibleCount;
										sackToAddForIncompatible = i;
										incompatibleOpenSpace = sacks.get(i).openSpace - addedItem.itemSize;
										incompatible = true;
									}
								}
							}
						}
					}
				}
			}

			// incompatible has priority
			if (incompatible) {
				// choose this sack index to add to
				if (sackToAddForOpenSpace == sackToAddForIncompatible) {
					addItemLS(sackToAddForIncompatible, addedItem);
				}

				// add to sack incompatible sack over too full sack
				else if (sackToAddForIncompatible != sackToAddForOpenSpace) {
					addItemLS(sackToAddForIncompatible, addedItem);
				}

			}
			// add to sack with highest openSpace
			else {
				addItemLS(sackToAddForOpenSpace, addedItem);
			}
		} else {
			addItemLS(sackToAddForOpenSpace, addedItem);
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

				} else
					System.out.print(sacks.get(i).contents.get(z).name + "\t");

			}
			if (sacks.get(i).contents.size() > 0) {
				System.out.println();
			}
		}
	}

	void calLCV(Item item, Sack sack) {

		boolean finished = false;
		Sack testSack = new Sack(sacks.get(0).maxSize);
		sack.LCV = 0;

		if (canAdd(sack, item))
			testSack.addItem(item);
		else {
			sack.LCV = 0;
			finished = true;
		}

		if (!finished) {

			for (int z = 0; z < unpackedItems.size(); z++) {

				if (item != unpackedItems.get(z)) {

					if (canAdd(testSack, unpackedItems.get(z))) {
						sack.LCV = sack.LCV + 1;
						// System.out.println("LCV incremented");
					}
				}

			}

		}
		// System.out.println("calLCV ended");
	}
}
