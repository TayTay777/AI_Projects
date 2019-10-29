import java.util.*;

class MRVComp implements Comparator<Item> {

    public int compare(Item itemOne, Item itemTwo) {

        // For ordering from least to greatest
        if ((itemOne.MRV) > (itemTwo.MRV))
            return 1;
        else if ((itemOne.MRV) < (itemTwo.MRV))
            return -1;
        return 0;
    }

}