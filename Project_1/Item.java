import java.util.ArrayList;

/**
 * item class initializes each item with 
 * with a string name arraylist of compatible item objects
 * and how much the item weighs
 * 
 * @author Taylor Roberts
 * @author Hailee Kiesecker
 */
public class Item {

		String name;
		ArrayList<String> compatible = new ArrayList<String>();
		int itemSize;

		public Item(String name, ArrayList<String> compatible, int itemSize) {
			this.name = name;
			this.compatible = compatible;
			this.itemSize = itemSize;
		}

		boolean checkCompatible(Item item) {
			if (compatible.contains(item.name) && item.compatible.contains(name)) {
				return true;
			} else
				return false;
		}

	}


	