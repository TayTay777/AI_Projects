import java.util.ArrayList;
import java.util.Comparator;


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
		int MRV;

		public Item(String name, ArrayList<String> compatible, int itemSize) {
			this.name = name;
			this.compatible = compatible;
			this.itemSize = itemSize; //domainSize
			this.MRV =0;
		}
		
		 
		boolean checkCompatible(Item item) {
			if (compatible.contains(item.name) && item.compatible.contains(name)) {
				return true;
			} else
				return false;
		}

}
class itemComp implements Comparator<Item>{
	@Override
	public int compare(Item i1, Item i2) {
		if ((i1.itemSize +i1.MRV) < (i2.itemSize+i2.MRV))
			return 1;
		else if ((i1.itemSize +i1.MRV) > (i2.itemSize+i2.MRV))
			return -1;
		return 0;
	}
}




	

	