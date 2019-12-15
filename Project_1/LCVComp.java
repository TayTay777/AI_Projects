import java.util.*;

class LCVComp implements Comparator<Sack> {

    public int compare(Sack sackOne, Sack sackTwo) {

        // For ordering from least to greatest
        if ((sackOne.LCV) > (sackTwo.LCV))
            return 1;
        else if ((sackOne.LCV) < (sackTwo.LCV))
            return -1;
        return 0;
    }

}