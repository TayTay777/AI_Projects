import java.util.ArrayList;
import java.util.Comparator;


public class Sack {

	int maxSize;
	int openSpace;
	int LCV;
	int position;
	
	ArrayList<Item> contents = new ArrayList<Item>();

		// initializing bag
		public Sack(int maxSize, int position) {
			this.maxSize = maxSize;
			openSpace = maxSize;
			this.LCV = 0;
			this.position = position;
			
		}

		// allows program to add item into bag
		void addItem(Item item) {
			contents.add(item);
			openSpace = openSpace - item.itemSize;
		}

		void removeItem(Item item){
			contents.remove(item);
			openSpace = openSpace + item.itemSize;
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
class sackComp implements Comparator<Sack>{
	
	 // Overriding compare()method of Comparator  
    // for descending order of LCV (l to g) 
	public int compare(Sack s1, Sack s2) {
	// 	if ((s1.LCV) > (s2.LCV))
	// 	return 1;
	// else if ((s1.LCV) < (s2.LCV))
	// 	return -1;
	// return 0;

		if ((s1.openSpace +s1.LCV) < (s2.openSpace +s2.LCV))
			return 1;
		else if ((s1.openSpace +s1.LCV) > (s2.openSpace +s2.LCV))
			return -1;
		return 0;
	}

}