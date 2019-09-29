import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Baggins {

    File file; // taking in contents of cart and restrictions
    private int numSacks; // number of bags available
    private int sackSize; // maximum bag size

    public Baggins(File file) {
        this.file = file;
    }

    /***
     * Depth first search to find solution to the bagging problem
     * 
     * @return "optimal" solution
     */

    private Cart DFS(Cart s) {
        // start state = all empty bags , all groceries
        // transition state = placing each item into a bag if it fits
        // goal state = all full bags, no more groceries
        // search goes down each node until solution is found

        // initialize the data store with s

        // while data store has stuff in it:

        // pull next solution s from data store

        // check it for being bad state, if so continue
        // check it for being a goal state, if yes return

        // if it isn’t
        // need to expand storage —
        // grab next unpacked item,
        // remove it from unpacked item list
        // for each bag, make a copy of the solution state, and pack the item in it ,
        // if no constraints are violated and then place it in data structure

    }

    void BFS() {

    }

    // TO DO: groceries class now needs to fit the
    // new classes below: item, items, and sack
    public class Cart {

        Vector<sack> sacks;
        ArrayList<item> unpackedItems;

        public Cart(ArrayList<item> items, int numSacks, int maxSackSize) {
            unpackedItems = new ArrayList<item>(items);
            sacks = new Vector<sack>(numSacks);
            for (int i = 0; i < numSacks; i++)
                sacks.add(new sack(maxSackSize));
        }


        //Tries to add an item to specified sack
        //if it cannot fit, returns false, else returns true
        boolean addItem(int sackNum, item item) {

            boolean canMix = false;
            for (int i = 0; i <  sacks.get(sackNum).contents.size(); i++){
                if (sacks.get(sackNum).contents.get(i).checkCompatible(item.name) == false){
                    break;
                }
                else canMix = true;
            }


            if (canMix){
                if (item.itemSize <= sacks.get(sackNum).openSpace) {
                    return false;
                } else
                    return true;
            }
            else return false;

        }

        // This will print one solution
        // the DFS or BFS will keep calling
        // this for every solution
        void printGroceries() {
            // TO DO: Make a toString from the
            // solution values
        }

    }

    public class item {

        String name;
        ArrayList<String> compatible = new ArrayList<String>();
        int itemSize;

        public item(String name, ArrayList<String> compatible, int itemSize) {
            this.name = name;
            this.compatible = compatible;
            this.itemSize = itemSize;
        }

        boolean checkCompatible(String item) {
            if (compatible.contains(item)) {
                return true;
            } else
                return false;
        }

    }

    /**
     * an Items class that parces through the given file and creates an array list
     * of multiple item objects that include their individual constraints, names,
     * and weights.
     * 
     * @return Items ArrayList
     */

    public class items {

        ArrayList<item> items = new ArrayList<item>();
        File file;
        Scanner fileScan;

        public items(File file) {
            this.file = file;

            // initialize the file
            fileScan = new Scanner(file);

        }

        // @return: items arraylist

        ArrayList<item> createItems() {

            // Gets all the groceries items for reference
            ArrayList<String> baseItems = new ArrayList<String>();
            // Scans past the # of bags and max bag size

            numSacks = fileScan.nextLine(); // setting number of sacks
            sackSize = fileScan.nextLine(); // setting size of each bag

            while (fileScan.hasNextLine()) {
                baseItems.add(fileScan.next());
                nextLine();

            }
            fileScan.reset(); // goes to top of file

            // Scans past the # of bags and max bag size
            fileScan.nextLine();
            fileScan.nextLine();

            // scans over every item line each while iteration
            while (fileScan.hasNextLine()) {

                String itemName;
                ArrayList<String> compatible = new ArrayList<String>();
                int itemSize;

                String itemLine = fileScan.NextLine();
                Scanner itemLineScan = new Scanner(itemLine);
                boolean plus = false;
                boolean hasCons = false;

                itemName = itemLineScan.next();
                itemSize = itemLineScan.nextInt();
                if (itemLineScan.hasnext()) {
                    hasCons = true;
                    if (itemLineScan.next().equals("+")) {
                        plus = true;
                    }
                }

                // dealing with constraints
                if (hasCons) {

                    if (plus) {
                        while (itemLineScan.hasNext()) {
                            compatible.add(itemLineScan.next()); // for each time it can be with add
                        }
                    }

                    else {
                        compatible.addAll(baseItems); // add all items into array
                        while (itemLineScan.hasNext()) {
                            compatible.remove(itemLineScan.next()); // for each item it cannot be with --remove
                        }
                    }

                }

                item newItem = new item(itemName, compatible, itemSize); // creating an item with all information
                items.add(newItem); // add new item into items array

            }
            return items; // returning the array of grocery items with their constrains and sizes
        }

    }

    public class sack {

        int maxSize;
        int openSpace;
        ArrayList<item> contents = new ArrayList<item>();

        // initializing bag
        public sack(int maxSize) {
            this.maxSize = maxSize;
            openSpace = maxSize;
        }

        // allows program to add item into bag
        void addItem(item item) {
            contents.add(item);
            openSpace = openSpace - item.itemSize;
        }

        // returns size of current bag
        public int getSize() {
            return sack.maxSize();
        }

        // allows program to initially check if the item
        // will fit in the bag
        boolean canFit(item item) {
            if ((openSpace - item.itemSize) < 0) {
                return false;
            } else {
                return true;
            }
        }

    }

}
