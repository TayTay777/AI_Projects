import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;

import sun.misc.Queue;

public class Baggins {

    File file; // taking in contents of cart and restrictions


    public Baggins(File file) {
        this.file = file;
    }



    public interface storage{
        public Cart get();
        public void put(Cart s);
        public boolean isEmpty();
    }
    /**
     * implementation of data storage via stack
     */



    public class stackStorage implements storage{
        Stack<Cart> stack = new Stack<Cart>();
        
        public Cart get(){
            stack.pop();
        }
        public void put(Cart s){
            stack.push(s);
        }
        public boolean isEmpty(){
            return stack.isEmpty();
        }
    }



    /**
     * implementation of data storage via queue
     */
    public class queueStoage implements storage{
        Queue<Cart> queue = new Queue<Cart>();
        
        public Cart get(){
            queue.dequeue();
        }
        public void put(Cart s){
            queue.enqueue(s);
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    /***
     * Depth first search to find solution to the bagging problem
     * 
     * @return "optimal" solution
     */

    private Cart DFS(Cart s) {
     

        // initialize the data store with s
        storage dfs;
        dfs.add(s);

        while(!dfs.isEmpty()){
            
        }

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



        Items bfsItems = new Items(file);

        int numSacks = bfsItems.numSacks;
        ArrayList<Item> items = bfsItems.createItems();


        Cart bfsCart = new Cart(items, numSacks, bfsItems.sackSize);


        //only methods needed 
        //addItem(sackNum, item)
        //solution(cart)




    }

    // TO DO: groceries class now needs to fit the
    // new classes below: item, items, and sack
    public class Cart {

        Vector<Sack> sacks;
        ArrayList<Item> unpackedItems;

        public Cart(ArrayList<Item> items, int numSacks, int maxSackSize) {
            unpackedItems = new ArrayList<Item>(items);
            sacks = new Vector<Sack>(numSacks);
            for (int i = 0; i < numSacks; i++)
                sacks.add(new Sack(maxSackSize));
        }


        //Tries to add an item to specified sack
        //if it cannot fit, returns false, else returns true
        boolean addItem(int sackNum, Item item) {

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
                } 
                else {
                    sacks.get(sackNum).addItem(item);
                    unpackedItems.remove(item);
                }
                    return true;
            }
            else return false;
        }



        boolean solution(Cart cart){
            if (unpackedItems.size() == 0){
                return true;
            } else return false;
        }



        // This will print one solution
        // the DFS or BFS will keep calling
        // this for every solution
        void printGroceries() {
            // TO DO: Make a toString from the
            // solution values
        }

    }

    public class Item {

        String name;
        ArrayList<String> compatible = new ArrayList<String>();
        int itemSize;

        public Item(String name, ArrayList<String> compatible, int itemSize) {
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

    public class Items {

        ArrayList<Item> items = new ArrayList<Item>();
        File file;
        Scanner fileScan;
        int numSacks;
        int sackSize;

        public Items(File file) {
            this.file = file;

            // initialize the file
            try {
                fileScan = new Scanner(file);
            } catch (Exception e) {
                //TODO: handle exception
            }
            

        }

        // @return: items arraylist

        ArrayList<Item> createItems() {

            // Gets all the groceries items for reference
            ArrayList<String> baseItems = new ArrayList<String>();
            // Scans past the # of bags and max bag size

            numSacks = fileScan.nextInt(); // setting number of sacks
            fileScan.nextLine();
            sackSize = fileScan.nextInt(); // setting size of each bag
            fileScan.nextLine();


            //creates an item list of Strings
            while (fileScan.hasNextLine()) {
                baseItems.add(fileScan.next());
                fileScan.nextLine();

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

                String itemLine = fileScan.nextLine();
                Scanner itemLineScan = new Scanner(itemLine);
                boolean plus = false;
                boolean hasCons = false;

                itemName = itemLineScan.next();
                itemSize = itemLineScan.nextInt();
                if (itemLineScan.hasNext()) {
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

}
