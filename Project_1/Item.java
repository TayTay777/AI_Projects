import java.util.ArrayList;


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


	