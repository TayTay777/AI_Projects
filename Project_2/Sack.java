import java.util.ArrayList;


public class Sack {

		int maxSize;
		int openSpace;
		ArrayList<Item> contents = new ArrayList<Item>();

		// initializing bag
		public Sack(int maxSize) {
			this.maxSize = maxSize;
			openSpace = maxSize;
		}

		// allows program to add item into bag
		void addItem(Item item) {
			contents.add(item);
			openSpace = openSpace - item.itemSize;
		}

		// returns size of current bag
		public int getSize() {
			return maxSize;
		}

		// allows program to initially check if the item
		// will fit in the bag
		boolean canFit(Item item) {
			if ((openSpace - item.itemSize) < 0) {
				return false;
			} else {
				return true;
			}
		}

	}